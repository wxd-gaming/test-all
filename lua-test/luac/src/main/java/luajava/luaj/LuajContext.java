package luajava.luaj;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import luajava.LuaRuntime;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.nio.charset.StandardCharsets;
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
public class LuajContext implements luajava.ILuaContext {

    boolean closed = false;
    final String name;
    final Globals L;
    HashMap<String, LuaValue> funcCache = new HashMap<>();

    public LuajContext(LuaRuntime luacRuntime) {
        L = JsePlatform.debugGlobals();
        this.name = luacRuntime.getName() + " - " + Thread.currentThread().getName();

        for (ImmutablePair<Path, byte[]> immutablePair : luacRuntime.getExtendList()) {
            load(immutablePair.left, immutablePair.right);
        }

        for (Map.Entry<String, Object> entry : luacRuntime.getGlobals().entrySet()) {
            L.set(entry.getKey(), CoerceJavaToLua.coerce(entry));
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
        try {
            String code = new String(bytes, StandardCharsets.UTF_8);
            LuaValue chunk = L.load(code, fileName);
            chunk.call();
            log.info("lua脚本加载完毕，path:{}", fileName);
        } catch (Throwable e) {
            log.error("lua脚本文件加载失败，path:{}，msg:{}", fileName, e.getMessage());
        }
        return fileName;
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