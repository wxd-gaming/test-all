package luajava.luaj;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import luajava.LuaRuntime;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
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
public class LuajContext implements luajava.ILuaContext {

    boolean closed = false;
    final String name;
    final Globals L;
    HashMap<String, LuaValue> funcCache = new HashMap<>();

    public LuajContext(LuaRuntime luacRuntime) {
        L = JsePlatform.debugGlobals();
        this.name = luacRuntime.getName() + " - " + Thread.currentThread().getName();

        load(luacRuntime.getLuaFileCache().getExtendList(), 5);

        for (Map.Entry<String, Object> entry : luacRuntime.getGlobals().entrySet()) {
            L.set(entry.getKey(), CoerceJavaToLua.coerce(entry));
        }

        load(luacRuntime.getLuaFileCache().getPathList(), 5);
    }

    @Override public void loadFile4Bytes(String fileName, byte[] bytes, int fortune) {
        log.debug("load lua {}", fileName);
        String code = new String(bytes, StandardCharsets.UTF_8);
        LuaValue chunk = L.load(code, fileName);
        chunk.call();
        log.debug("file byte load lua {}", fileName);
    }

    @Override public boolean has(String name) {
        return findLuaValue(name) != null;
    }

    public LuaValue findLuaValue(String name) {
        return funcCache.computeIfAbsent(name, f -> {
            LuaValue value = L.get(name);
            return isLuaFunc(value) ? value : null;
        });
    }

    private static boolean isLuaFunc(LuaValue event) {
        if (event == null) {
            return false;
        }
        if (event instanceof LuajFunction) {
            return false;
        }
        if (event instanceof LuaFunction) {
            return true;
        }
        return false;
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
        LuaValue[] funcArgs = new LuaValue[args.length];
        for (int i = 0; i < args.length; i++) {
            LuaValue evenArg = LuajUtils.valueOf(args[i]);
            funcArgs[i] = evenArg;
        }
        try {
            Varargs result = luaValue.invoke(funcArgs);
            // 默认给第一个返回值
            LuaValue ret = result.arg(1);
            return LuajUtils.luaValue2Object(ret);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return null;
    }

    @Override public void memory(AtomicLong memory) {
        synchronized (this) {
            LuaValue luaValue = findLuaValue("memory0");
            Object pcall = pcall0(luaValue);
            memory.addAndGet(((Number) pcall).longValue());
        }
    }

    @Override public void gc() {
    }

    @Override public void close() {
        synchronized (this) {
            if (closed) return;
            closed = true;
            funcCache = new HashMap<>();
        }
    }

}
