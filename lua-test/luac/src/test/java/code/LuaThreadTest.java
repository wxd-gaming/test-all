package code;

import luajava.LuaRuntime;
import luajava.LuaService;
import luajava.LuaType;
import org.junit.Test;

import java.text.DecimalFormat;

/**
 * 测试
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-09 19:08
 **/
public class LuaThreadTest {

    LuaService luaService;

    public void createLUAJ() {
        luaService = LuaService.of(LuaType.LUAJ, false, true, "../lua");
    }

    public void createLUA54() {
        luaService = LuaService.of(LuaType.LUA54, false, true, "../lua");
    }

    public void createLUAJit() {
        luaService = LuaService.of(LuaType.LUAJit, false, true, "../lua");
    }

    @Test
    public void testLuaj() throws Exception {
        createLUAJ();
        testLua();
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
                StringBuilder stringBuilder = new StringBuilder();
                freeMemory(stringBuilder);
                System.out.printf("%s\n%s\n%s 个lua虚拟机 总内存： %d mb - 统计耗时 %d ms %n",
                        string,
                        stringBuilder.toString(),
                        sum, memory / 1024,
                        System.currentTimeMillis() - nanoTime
                );
            }
        }, "check").start();

        Thread.sleep(30 * 1000);

    }

    public static final DecimalFormat df2 = new DecimalFormat("0.00%");
    public static String Xmx = "";

    /** 获取当前空闲内存 */
    public static long freeMemory(StringBuilder stringBuilder) {

        ByteFormat byteFormat = new ByteFormat();
        final Runtime runtime = Runtime.getRuntime();
        final long maxMemory = runtime.maxMemory();
        final long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        freeMemory = (maxMemory - totalMemory + freeMemory);
        long usedMemory = maxMemory - freeMemory;
        float warn = 1f * usedMemory / maxMemory;
        if (stringBuilder != null) {
            stringBuilder.append("内存空间 -> ");
            stringBuilder.append("总量：");
            byteFormat.addFlow(maxMemory);
            final String mx = byteFormat.toString(ByteFormat.FormatInfo.MB);
            stringBuilder.append(mx).append(", ");
            byteFormat.clear();
            stringBuilder.append("空闲：");
            byteFormat.addFlow(freeMemory);
            byteFormat.toString(ByteFormat.FormatInfo.MB, stringBuilder);
            stringBuilder.append(", ");
            byteFormat.clear();
            stringBuilder.append("使用：");
            byteFormat.addFlow(usedMemory);
            final String use = byteFormat.toString(ByteFormat.FormatInfo.MB);
            stringBuilder.append(use).append(", ");
            byteFormat.clear();

            stringBuilder.append("占比：").append(df2.format(warn));

            Xmx = "分配=" + mx + ", 使用=" + use;
        }
        return freeMemory;
    }

}
