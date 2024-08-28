package test;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import party.iroiro.luajava.JFunction;
import party.iroiro.luajava.Lua;
import party.iroiro.luajava.lua54.Lua54;
import party.iroiro.luajava.value.LuaValue;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public void loadDir(String dir) throws Exception {
        Files.walk(Paths.get(dir), 99)
                .filter(p -> {
                    String string = p.toString();
                    return string.endsWith(".lua") || string.endsWith(".LUA");
                })
                .filter(path -> {
                    return Files.isRegularFile(path);
                })
                .forEach(this::loadFile);
    }

    /** 加载一个lua文件 */
    public void loadFile(String filePath) {
        loadFile(Paths.get(filePath));
    }

    /** 加载一个lua文件 */
    public void loadFile(Path filePath) {
        try {
            byte[] bytes = Files.readAllBytes(filePath);
            Buffer flip = ByteBuffer.allocateDirect(bytes.length).put(bytes).flip();
            lua54.run(flip, filePath.getFileName().toString());
            System.out.println("load lua file: " + filePath.toString());
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
        lua54.close();
    }
}
