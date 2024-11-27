package luajava;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
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

    LuaRuntime luaRuntime;

    public static LuaService of(LuaType luaType, boolean xpcall, String paths) {
        LuaService luaService = new LuaService(luaType, xpcall, paths);
        luaService.init();
        return luaService;
    }

    LuaType luaType;
    boolean xpcall;
    String paths;

    protected LuaService(LuaType luaType, boolean xpcall, String paths) {
        this.luaType = luaType;
        this.xpcall = xpcall;
        this.paths = paths;
    }

    public void init() {

        LuaRuntime _luaRuntime = new LuaRuntime(luaType, "root", xpcall, paths);
        LuaRuntime old = luaRuntime;
        luaRuntime = _luaRuntime;

        if (old != null) {
            new Thread(() -> {
                try {
                    Thread.sleep(30_000);
                    old.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }

    public LuaRuntime getRuntime() {
        return luaRuntime;
    }

    public long memory() {
        AtomicLong atomicLong = new AtomicLong();
        luaRuntime.memory(atomicLong);
        return atomicLong.get();
    }

    public long size() {
        return luaRuntime.size();
    }

    @Override public void close() {
        luaRuntime.close();
    }

}
