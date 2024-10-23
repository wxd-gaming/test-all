package luajava.luac;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import luajava.LuaRuntime;
import luajava.LuaType;
import luajava.luac.impl.Lua54Impl;
import luajava.luac.impl.LuaJitImpl;
import org.apache.commons.lang3.tuple.ImmutablePair;
import party.iroiro.luajava.Consts;
import party.iroiro.luajava.JuaAPI;
import party.iroiro.luajava.Lua;
import party.iroiro.luajava.value.LuaValue;

import java.nio.Buffer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * lua 当前上下文
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-21 16:57
 */
@Slf4j
@Getter
public class LuacContext implements luajava.ILuaContext {

    boolean closed = false;
    final String name;
    final Lua L;
    HashMap<String, LuaValue> funcCache = new HashMap<>();

    public LuacContext(LuaRuntime luacRuntime) {
        if (luacRuntime.getLuaType() == LuaType.LUAJit) {
            L = new LuaJitImpl();
        } else {
            L = new Lua54Impl();
        }

        this.name = luacRuntime.getName() + " - " + Thread.currentThread().getName();
        L.openLibraries();

        for (ImmutablePair<Path, byte[]> immutablePair : luacRuntime.getExtendList()) {
            load(immutablePair.left, immutablePair.right);
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

    @Override public String load(Path filePath, byte[] bytes) {
        String fileName = filePath.getFileName().toString();
        return load(fileName, bytes);
    }

    @Override public String load(String fileName, byte[] bytes) {
        log.debug("load lua {}", fileName);
        Buffer flip = JuaAPI.allocateDirect(bytes.length).put(bytes).flip();
        L.run(flip, fileName);
        return fileName;
    }

    @Override public boolean has(String name) {
        return findLuaValue(name) != null;
    }

    public LuaValue findLuaValue(String name) {
        return funcCache.computeIfAbsent(name, f -> {
            LuaValue value = L.get(name);
            return value.type() == Lua.LuaType.NIL || value.type() == Lua.LuaType.NONE ? null : value;
        });
    }

    @Override public Object call(boolean xpcall, String key, Object... args) {
        synchronized (this) {
            return call0(xpcall, key, args);
        }
    }

    Object call0(boolean xpcall, String key, Object... args) {
        LuaValue luaValue = findLuaValue(key);
        if (luaValue == null) {
            return null;
        }
        if (xpcall) {
            LuaValue dispatchLua = findLuaValue("dispatch");
            if (dispatchLua == null) throw new RuntimeException("lua function dispatch not found");
            Object[] args2 = new Object[args.length + 1];
            System.arraycopy(args, 0, args2, 1, args.length);
            args2[0] = key;
            return pcall0(dispatchLua, args2);
        } else {
            return pcall0(luaValue, args);
        }
    }

    Object pcall0(LuaValue luaValue, Object... args) {
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

    @Override public void memory(AtomicLong memory) {
        synchronized (this) {
            LuaValue luaValue = findLuaValue("memory0");
            Object pcall = pcall0(luaValue);
            memory.addAndGet(((Number) pcall).longValue());
        }
    }

    @Override public void gc() {
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
