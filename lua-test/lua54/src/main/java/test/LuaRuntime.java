package test;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * lua 装载器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-22 16:10
 */
@Slf4j
@Getter
public class LuaRuntime implements Closeable {

    final String name;
    final Path[] paths;
    final ConcurrentHashMap<String, Object> globals = new ConcurrentHashMap<>();
    ConcurrentHashMap<Thread, LuaContext> contexts = new ConcurrentHashMap<>();


    public LuaRuntime(String name, Path[] paths) {
        this.name = name;
        this.paths = paths;
    }

    public LuaContext newContext() {
        return new LuaContext(globals, paths);
    }

    public LuaContext context() {
        LuaContext luaContext = contexts.get(Thread.currentThread());
        if (luaContext == null || luaContext.isClosed()) {
            luaContext = newContext();
            contexts.put(Thread.currentThread(), luaContext);
        }
        return luaContext;
    }

    /**
     * 单位KB
     *
     * @return
     * @author: wxd-gaming(無心道, 15388152619)
     * @version: 2024-10-18 17:40
     */
    public long memory() {
        AtomicLong memory = new AtomicLong();
        contexts.values().forEach(luacContext -> {
            synchronized (luacContext) {
                Object pcall = luacContext.pcall("memory0");
                if (pcall instanceof Number) {
                    memory.addAndGet(((Number) pcall).longValue());
                }
            }
        });
        return memory.get();
    }

    /** 关闭资源 */
    @Override public void close() {
        contexts.values().forEach(LuaContext::close);
        contexts = null;
    }
}
