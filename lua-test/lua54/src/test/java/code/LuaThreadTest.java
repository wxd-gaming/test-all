package code;

import org.junit.Test;
import test.Lua54_Sub;
import test.LuaContext;
import test.LuaJit_Sub;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * 测试
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-09 19:08
 **/
public class LuaThreadTest {

    Supplier<LuaContext> newLua;

    @Test
    public void testLua54() throws Exception {
        newLua = () -> new LuaContext(new Lua54_Sub(), Collections.emptyMap(), Paths.get("../lua"));
        testLua();
    }

    @Test
    public void testLuaJit() throws Exception {
        newLua = () -> new LuaContext(new LuaJit_Sub(), Collections.emptyMap(), Paths.get("../lua"));
        testLua();
    }

    List<LuaContext> list = new CopyOnWriteArrayList<>();

    LuaContext create() {
        LuaContext lua = newLua.get();
        list.add(lua);
        return lua;
    }

    public void testLua() throws Exception {


        for (int i = 0; i < 16; i++) {
            new Thread(() -> {
                try (LuaContext currentThread = create()) {
                    while (true) {
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        synchronized (currentThread) {
                            try {
                                // if (ThreadLocalRandom.current().nextInt(1, 10000) < 800)
                                //     currentThread.pcall("cleancache");
                                currentThread.pcall("cache_memory");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }, "run-" + i).start();
        }

        new Thread(() -> {
            int count = 0;
            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                String string = Thread.currentThread().getName() + " - " + (++count);
                long nanoTime = System.currentTimeMillis();
                long memory = 0;
                for (LuaContext lua : list) {
                    synchronized (lua) {
                        try {
                            Object obj = lua.pcall("memory0");
                            memory = ((Number) obj).longValue();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.printf("%s - %s 个lua虚拟机 总内存： %d mb - 统计耗时 %d ms %n", string, list.size(), memory / 1024, System.currentTimeMillis() - nanoTime);
            }
        }, "check").start();

        Thread.sleep(300 * 1000);

    }

}
