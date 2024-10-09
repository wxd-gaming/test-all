package test;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import party.iroiro.luajava.JFunction;
import party.iroiro.luajava.Lua;
import party.iroiro.luajava.value.LuaTableValue;
import party.iroiro.luajava.value.LuaValue;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * lua 装载器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-22 16:10
 */
@Slf4j
@Getter
public class LuaRuntime implements Closeable {

    final Lua712_54 lua54;
    final ThreadLocal<Lua712_54> threadLocal;

    public LuaRuntime() {
        lua54 = new Lua712_54();
        lua54.openLibraries();
        threadLocal = ThreadLocal.withInitial(lua54::newThread);
    }

    /** 加载一个lua文件 */
    public void loadDir(Path dir) {
        ArrayList<String> modules = new ArrayList<>();
        ArrayList<Path> errorPaths = new ArrayList<>();
        try {
            Files.walk(dir, 99)
                    .filter(p -> {
                        String string = p.toString();
                        return string.endsWith(".lua") || string.endsWith(".LUA");
                    })
                    .filter(Files::isRegularFile)
                    .sorted(Comparator.comparing(o -> o.toString().toLowerCase()))
                    .forEach(filePath -> {
                        try {
                            String module = loadfile(filePath);
                            modules.add(module);
                        } catch (Exception e) {
                            errorPaths.add(filePath);
                        }
                    });
            if (!errorPaths.isEmpty()) {
                for (Path errorPath : errorPaths) {
                    log.warn("lua file load error: {}", errorPath);
                    loadfile(errorPath);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("{} {}", dir, modules);
    }

    /** 加载一个lua文件 */
    public String loadfile(String filePath) {
        return loadfile(Paths.get(filePath));
    }

    /** 加载一个lua文件 */
    public String loadfile(Path filePath) {
        try {
            byte[] bytes = Files.readAllBytes(filePath);
            Buffer flip = ByteBuffer.allocateDirect(bytes.length).put(bytes).flip();
            String chunkName = filePath.getFileName().toString();
            lua54.run(flip, chunkName);
            System.out.println("load lua file: " + chunkName);
            return chunkName;
        } catch (Exception e) {
            throw new RuntimeException(filePath.toString(), e);
        }
    }

    /** 把一个方法转化成函数传递给lua */
    public void pushJFunction(Object bean, Method method) {
        pushJFunction(bean, method.getName(), method);
    }

    /** 把一个方法转化成函数传递给lua */
    public void pushJFunction(final Object bean, String key, Method method) {
        JFunction jFunction = (L) -> {
            try {
                /* 构建返回值 */
                Object[] _args = new Object[L.getTop()];
                for (int i = 0; i < _args.length; i++) {
                    LuaValue luaValue1 = L.get();
                    Object javaObject = luaValue1.toJavaObject();
                    _args[_args.length - i - 1] = javaObject;
                }
                method.invoke(bean, _args);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            return 0;
        };

        pushObj(key, jFunction);
    }

    /** 把一个方法转化成函数传递给lua */
    public void pushObj(String key, JFunction jFunction) {
        lua54.set(key, jFunction);
    }

    /** 设置全局变量 */
    public void pushObj(String key, Object value) {
        lua54.set(key, value);
    }

    /** 查找lua方法 */
    public LuaValue find(String method) {
        return threadLocal.get().get(method);
    }

    /** 调用lua */
    public LuaValue call(String method, Object... params) {
        LuaValue[] call = find(method).call(params);
        if (call.length == 0) {
            return null;
        }
        return call[0];
    }

    /** 关闭资源 */
    @Override public void close() throws IOException {
        try {
            lua54.close();
        } catch (Exception ignore) {}
    }

    public static Object luaValue2Object(LuaValue luaValue) {
        if (luaValue.type() == Lua.LuaType.NUMBER) {
            long integer = luaValue.toInteger();
            if (integer == (int) integer) {
                return (int) integer;
            }
            double number = luaValue.toNumber();
            if (integer == number) {
                return integer;
            }
            return number;

        } else if (luaValue.type() == Lua.LuaType.TABLE) {
            LuaTableValue luaTableValue = (LuaTableValue) luaValue;
            Map<Object, Object> map = new HashMap<>();
            for (Map.Entry<LuaValue, LuaValue> entry : luaTableValue.entrySet()) {
                map.put(luaValue2Object(entry.getKey()), luaValue2Object(entry.getValue()));
            }
            return map;
        } else if (luaValue.type() == Lua.LuaType.NONE || luaValue.type() == Lua.LuaType.NIL) {
            return null;
        }
        return luaValue.toJavaObject();
    }

    public static int push(Lua L, LuaValue fun, Object... objects) {
        if (fun != null) {
            fun.push(L);
        }
        for (Object object : objects) {
            if (object != null && object.getClass().isArray()) {
                L.pushJavaArray(object);
            } else {
                L.push(object, Lua.Conversion.FULL);
            }
        }
        return objects.length;
    }

}
