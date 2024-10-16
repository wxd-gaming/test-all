package test;

import party.iroiro.luajava.JuaAPI;
import party.iroiro.luajava.Lua;
import party.iroiro.luajava.luajit.LuaJit;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LuajitMain {

    public static void main(String[] args) throws Exception {
        /* TODO 测试下来还是5.1 */
        Lua lua = new LuaJit();
        lua.openLibraries();
        long timeMillis = System.currentTimeMillis();

        // lua.run("local num = 99999999999999999LL\n" +
        //         "    print(num)");
        // lua.run("local i=" + timeMillis + "\n" +
        //         "print(type(i) ..\" - \".. i)");

        String file = "lua";
        Files.walk(Paths.get(file), 99)
                .filter(Files::isRegularFile)
                .forEach(path -> {
                    byte[] bytes = null;
                    try {
                        bytes = Files.readAllBytes(path);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(path.toString());
                    Buffer flip = JuaAPI.allocateDirect(bytes.length).put(bytes).flip();
                    /*TODO 可以直接运行文件 */
                    lua.run(flip, path.getFileName().toString());
                });

        // lua.get("testlong").call(String.valueOf(Long.MAX_VALUE) + "ULL");
        // lua.get("debugt3").call(System.currentTimeMillis(), String.valueOf(Long.MAX_VALUE) + "LL", "test");

        for (int j = 0; j < 1000; j++) {
            lua.get("cache_memory").call(1);
        }
        lua.get("cleanup").call("12");
    }
}