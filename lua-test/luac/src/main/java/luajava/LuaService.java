package luajava;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * lua 服务
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-21 16:11
 */
@Slf4j
@Getter
public class LuaService implements AutoCloseable, Closeable {

    private HashMap<String, LuaRuntime> runtimeHashMap = new HashMap<>();

    public static LuaService of(LuaType luaType, boolean useModule, boolean xpcall, String paths) {
        LuaService luaService = new LuaService(luaType, useModule, xpcall, paths);
        luaService.init();
        return luaService;
    }

    LuaType luaType;
    boolean xpcall;
    String paths;

    protected LuaService(LuaType luaType, boolean useModule, boolean xpcall, String paths) {
        this.luaType = luaType;
        this.xpcall = xpcall;
        this.paths = paths;
    }

    public void init() {
        HashMap<String, LuaRuntime> tmpRuntimeHashMap = new HashMap<>();

        LuaRuntime luaRuntime = new LuaRuntime(luaType, "root", xpcall, paths);
        tmpRuntimeHashMap.put(luaRuntime.getName(), luaRuntime);
        HashMap<String, LuaRuntime> tmp = runtimeHashMap;
        runtimeHashMap = tmpRuntimeHashMap;
        if (tmp != null && !tmp.isEmpty()) {
            new Thread(() -> {
                try {
                    Thread.sleep(30_000);
                    tmp.values().forEach(LuaRuntime::close);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }

    public LuaRuntime getRuntime() {
        return runtimeHashMap.get("root");
    }

    public LuaRuntime getRuntime(String name) {
        return runtimeHashMap.get(name);
    }

    public long memory() {
        AtomicLong atomicLong = new AtomicLong();
        runtimeHashMap.values().forEach(v -> v.memory(atomicLong));
        return atomicLong.get();
    }

    public long size() {
        return runtimeHashMap.values().stream().mapToLong(LuaRuntime::size).sum();
    }

    @Override public void close() {
        for (LuaRuntime runtime : runtimeHashMap.values()) {
            runtime.close();
        }
    }

}
