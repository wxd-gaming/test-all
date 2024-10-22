package code;

import luajava.LuaRuntime;
import luajava.LuaService;
import luajava.LuaType;
import org.junit.Test;

/**
 * 测试
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-09 19:08
 **/
public class LuaThreadTest {

    LuaService luaService;

    public void createLUA54() {
        luaService = LuaService.of(LuaType.LUA54, false, true, "../lua");
    }

    public void createLUAJit() {
        luaService = LuaService.of(LuaType.LUAJit, false, true, "../lua");
    }


    @Test
    public void testLua54() throws Exception {
        createLUA54();
        testLua();
    }

    @Test
    public void testLuaJit() throws Exception {
        createLUAJit();
        testLua();
    }


    public void testLua() throws Exception {


        for (int i = 0; i < 16; i++) {
            new Thread(() -> {
                LuaRuntime runtime = luaService.getRuntime();
                while (true) {
                    try {
                        Thread.sleep(300);
                        runtime.call("cache_memory");
                    } catch (Exception e) {
                        e.printStackTrace(System.err);
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
                long memory = luaService.memory();
                long sum = luaService.size();
                System.out.printf("%s - %s 个lua虚拟机 总内存： %d mb - 统计耗时 %d ms %n", string, sum, memory / 1024, System.currentTimeMillis() - nanoTime);
            }
        }, "check").start();

        Thread.sleep(30 * 1000);

    }

}
