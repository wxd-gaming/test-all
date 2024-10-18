package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-09 19:08
 **/
public class LuaMain {


    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 5; i++) {
            String baseDir = "G:\\712-work\\tszz\\config\\天使之战_v1.0.0.0\\meta\\userapi\\lua\\";
            if (!Files.exists(Paths.get(baseDir))) {
                baseDir = "/mnt/g/712-work/tszz/config/天使之战_v1.0.0.0/meta/userapi/lua";
            }
            if (!Files.exists(Paths.get(baseDir))) {
                baseDir = "lua";
            }
            LuaEventBus luaEventBus = LuaEventBus.buildFromDirs(baseDir);
            LuaRuntime luaRuntime = luaEventBus.contextModule("root");
            s(luaRuntime);
            Thread.sleep(10 * 1000);
            luaRuntime.getContexts().values().forEach(LuaContext::gc);
            System.out.println("lua gc");
            Thread.sleep(20 * 1000);
            luaRuntime.close();
            freeMemory();
            for (int j = 0; j < 3; j++) {
                System.runFinalization();
                System.gc();
                Thread.sleep(1000);
            }
            freeMemory();
            Thread.sleep(60 * 1000);
        }
    }

    public static void s(LuaRuntime luaRuntime) throws IOException {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            Thread thread = new Thread(() -> {
                LuaContext context = luaRuntime.context();
                // context.pcall("showmemory", context.toString());
                for (int j = 0; j < 1000; j++) {
                    context.pcall("cache_memory", 1);
                }
            }, "" + (i + 1));
            thread.start();
            list.add(thread);
        }
        list.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException ignore) {}
        });
        freeMemory();
    }


    public static final DecimalFormat df2 = new DecimalFormat("0.00%");
    public static String Xmx = "";


    /** 获取当前空闲内存 */
    public static void freeMemory() {
        StringBuilder stringBuilder = new StringBuilder();
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
            stringBuilder.append("占用：");
            byteFormat.addFlow(totalMemory);
            final String tx = byteFormat.toString(ByteFormat.FormatInfo.MB);
            stringBuilder.append(tx).append(", ");
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
        System.out.println(stringBuilder.toString());
    }

}
