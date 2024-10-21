package luac;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import luac.impl.Lua54Impl;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import party.iroiro.luajava.Consts;
import party.iroiro.luajava.JuaAPI;
import party.iroiro.luajava.Lua;
import party.iroiro.luajava.value.LuaValue;

import java.io.Closeable;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * lua 当前上下文
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-21 16:57
 */
@Slf4j
@Getter
public class LuaContext implements Closeable, AutoCloseable {

    boolean closed = false;
    final String name;
    final Lua L;
    HashMap<String, LuaValue> funcCache = new HashMap<>();

    public LuaContext(LuaRuntime luacRuntime) {
        L = new Lua54Impl();
        this.name = luacRuntime.getName() + " - " + Thread.currentThread().getName();
        L.openLibraries();

        String implLua = "/impl.lua";
        try (InputStream resourceAsStream = LuaContext.class.getResourceAsStream(implLua)) {
            byte[] byteArray = IOUtils.toByteArray(resourceAsStream);
            load(implLua, byteArray);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        for (Map.Entry<String, Object> entry : luacRuntime.getGlobals().entrySet()) {
            L.set(entry.getKey(), entry.getValue());
        }

        List<ImmutablePair<Path, byte[]>> error = new ArrayList<>();
        for (ImmutablePair<Path, byte[]> immutablePair : luacRuntime.getPathList()) {
            try {
                load(immutablePair.left, immutablePair.right);
            } catch (Exception e) {
                error.add(immutablePair);
            }
        }
        if (!error.isEmpty()) {
            for (ImmutablePair<Path, byte[]> immutablePair : error) {
                load(immutablePair.left, immutablePair.right);
            }
        }
    }

    public String load(Path filePath, byte[] bytes) {
        String fileName = filePath.getFileName().toString();
        return load(fileName, bytes);
    }

    public String load(String fileName, byte[] bytes) {
        log.debug("load lua {}", fileName);
        Buffer flip = JuaAPI.allocateDirect(bytes.length).put(bytes).flip();
        L.run(flip, fileName);
        return fileName;
    }

    public boolean has(String name) {
        return findLuaValue(name) != null;
    }

    public LuaValue findLuaValue(String name) {
        return funcCache.computeIfAbsent(name, f -> {
            LuaValue value = L.get(name);
            return value.type() == Lua.LuaType.NIL || value.type() == Lua.LuaType.NONE ? null : value;
        });
    }

    public Object pcall(LuaValue luaValue, Object... args) {
        int oldTop = L.getTop();
        luaValue.push(L);
        for (Object o : args) {
            LuaUtils.push(L, o);
        }
        try {
            L.pCall(args.length, Consts.LUA_MULTRET);
            int returnCount = L.getTop() - oldTop;
            if (returnCount == 0) {
                return null;
            }
            LuaValue[] call = new LuaValue[returnCount];
            for (int i = 0; i < returnCount; i++) {
                call[returnCount - i - 1] = L.get();
            }
            LuaValue returnValue = call[0];
            return LuaUtils.luaValue2Object(returnValue);
        } catch (Throwable e) {
            RuntimeException runtimeException = new RuntimeException(e.getMessage());
            runtimeException.setStackTrace(e.getStackTrace());
            throw runtimeException;
        } finally {
            L.setTop(oldTop);
        }
    }

    public void gc() {
        synchronized (this) {
            if (closed) return;
            try {
                this.L.gc();
            } catch (Exception e) {
                log.error("{} - cleanup error {}", this.toString(), e.toString());
            }
        }
    }

    @Override public void close() {
        synchronized (this) {
            if (closed) return;
            closed = true;
            gc();
            funcCache = new HashMap<>();
            L.close();
        }
    }

}
