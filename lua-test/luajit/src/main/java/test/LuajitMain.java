package test;

import party.iroiro.luajava.Lua;
import party.iroiro.luajava.luajit.LuaJit;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class LuajitMain {

    public static void main(String[] args) throws Exception {
        /* TODO 测试下来还是5.2.x */
        Lua lua = new LuaJit();
        lua.openLibraries();
        long timeMillis = System.currentTimeMillis();

        lua.run("local num = 99999999999999999LL\n" +
                "    print(num)");
        lua.run("local i=" + timeMillis + "\n" +
                "print(type(i) ..\" - \".. i)");


        String file = "luajit/src/main/lua/test.lua";
        String string = new String(Files.readAllBytes(Paths.get(file)), StandardCharsets.UTF_8);
        /*TODO 可以直接运行文件 */
        // lua.run(string);
        /*TODO 加载后是找不到内容的*/
        // byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        // ByteBuffer byteBuffer = ByteBuffer.allocateDirect(bytes.length);
        // byteBuffer.put(bytes);
        // lua.load(byteBuffer, "test");
        // System.out.println(Arrays.toString(lua.eval("t2()")));
        // System.out.println(lua.toInteger(1));
        // System.out.println(lua.get("t2()"));
    }
}