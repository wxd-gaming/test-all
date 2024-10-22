package luajava;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import luajava.luac.LuaContext;
import org.apache.commons.lang3.tuple.ImmutablePair;

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

    final LuaType luaType;
    final boolean xpcall;
    final String name;
    /** 系统扩展 */
    final List<ImmutablePair<Path, byte[]>> extendList;
    final List<ImmutablePair<Path, byte[]>> pathList;
    final ConcurrentHashMap<String, Object> globals = new ConcurrentHashMap<>();
    volatile ConcurrentHashMap<Thread, ILuaContext> contexts = new ConcurrentHashMap<>();

    public LuaRuntime(LuaType luaType, String name, boolean xpcall, Path[] paths) {
        this.luaType = luaType;
        this.xpcall = xpcall;
        this.name = name;

        extendList = Utils.resourcesList("lua-extend", new String[]{"lua", "LUA"});

        this.pathList = Arrays.stream(paths)
                .filter(Files::exists)
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

    public ILuaContext newContext() {
        return new LuaContext(this);
    }

    public ILuaContext context() {
        ILuaContext luaContext = contexts.get(Thread.currentThread());
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
        memory(memory);
        return memory.get();
    }

    public void memory(AtomicLong memory) {
        contexts.values().forEach(luacContext -> memory0(memory, luacContext));
    }

    void memory0(AtomicLong memory, ILuaContext context) {
        synchronized (context) {
            context.memory(memory);
        }
    }

    public Object call(String key, Object... args) {
        return context().call(xpcall, key, args);
    }

    public long size() {
        int size = contexts.size();
        return size;
    }

    /** 关闭资源 */
    @Override public void close() {
        if (contexts == null) return;
        contexts.values().forEach(ILuaContext::close);
        contexts = null;
    }


}