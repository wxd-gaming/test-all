package luac;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import party.iroiro.luajava.value.LuaValue;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * lua 装载器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-22 16:10
 */
@Slf4j
@Getter
public class LuaRuntime implements Closeable {

    final LuacType luacType;
    final boolean xpcall;
    final String name;
    final List<ImmutablePair<Path, byte[]>> pathList;
    final ConcurrentHashMap<String, Object> globals = new ConcurrentHashMap<>();
    volatile ConcurrentHashMap<Thread, LuaContext> contexts = new ConcurrentHashMap<>();


    public LuaRuntime(LuacType luacType, String name, boolean xpcall, Path[] paths) {
        this.luacType = luacType;
        this.xpcall = xpcall;
        this.name = name;
        this.pathList = Arrays.stream(paths)
                .flatMap(path -> {
                    try {
                        return Files.walk(path, 99).filter(Files::isRegularFile);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .sorted((o1, o2) -> o1.toString().compareToIgnoreCase(o2.toString()))
                .map(path -> {
                    try {
                        byte[] bytes = Files.readAllBytes(path);
                        return ImmutablePair.of(path, bytes);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    public LuaContext newContext() {
        return new LuaContext(this);
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
                LuaValue memory0 = luacContext.findLuaValue("memory0");
                Object pcall = luacContext.pcall(memory0);
                memory.addAndGet(((Number) pcall).longValue());
            }
        });
        return memory.get();
    }

    public Object call(String key, Object... args) {
        LuaContext context = context();
        LuaValue luaValue = context.findLuaValue(key);
        if (luaValue == null) {
            return null;
        }
        if (xpcall) {
            LuaValue dispatchLua = context.findLuaValue("dispatch");
            if (dispatchLua == null) throw new RuntimeException("lua function dispatch not found");
            Object[] args2 = new Object[args.length + 1];
            System.arraycopy(args, 0, args2, 1, args.length);
            args2[0] = key;
            return context.pcall(dispatchLua, args2);
        } else {
            return context.pcall(luaValue, args);
        }
    }

    /** 关闭资源 */
    @Override public void close() {
        if (contexts == null) return;
        contexts.values().forEach(LuaContext::close);
        contexts = null;
    }


}
