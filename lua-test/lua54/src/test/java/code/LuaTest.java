package code;

import org.junit.Test;
import party.iroiro.luajava.JuaAPI;
import party.iroiro.luajava.Lua;
import party.iroiro.luajava.lua54.Lua54;
import party.iroiro.luajava.luajit.LuaJit;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 测试
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-09 19:08
 **/
public class LuaTest {


    @Test
    public void testLua54() throws Exception {
        Lua lua = new Lua54();
        testLua(lua);
    }

    @Test
    public void testLuaJit() throws Exception {
        Lua lua = new LuaJit();
        testLua(lua);
    }

    public void testLua(Lua lua) throws Exception {
        lua.openLibraries();
        long timeMillis = System.currentTimeMillis();

        // lua.run("local num = 99999999999999999LL\n" +
        //         "    print(num)");
        // lua.run("local i=" + timeMillis + "\n" +
        //         "print(type(i) ..\" - \".. i)");

        String file = "../lua";
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

        lua.get("testlong").call(Long.MAX_VALUE);
        lua.get("debugt3").call(System.currentTimeMillis(), Long.MAX_VALUE, "test");

        // for (int j = 0; j < 1000; j++) {
        //     lua.get("cache_memory").call(1);
        // }
        // lua.get("cleanup").call("12");
    }

}
