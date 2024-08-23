package test;

import party.iroiro.luajava.Lua;
import party.iroiro.luajava.lua51.Lua51;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Lua51Main {

    public static void main(String[] args) throws Exception {
        Lua lua = new Lua51();
        lua.openLibraries();
        long timeMillis = System.currentTimeMillis();
        lua.run("local num = 99999999999999999\n" +
                "    print(num)");
        lua.run("local i=" + timeMillis + "\n" +
                "print(type(i) ..\" - \".. i)");


        String file = "lua51/src/main/lua/test.lua";
        String string = new String(Files.readAllBytes(Paths.get(file)), StandardCharsets.UTF_8);
        /*TODO 可以直接运行文件，但是无法指定函数*/
        lua.run(string);

        /*TODO 加载后是找不到内容的*/
        // byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        // ByteBuffer byteBuffer = ByteBuffer.allocateDirect(bytes.length);
        // byteBuffer.put(bytes);
        // lua.load(byteBuffer, "test");
        System.out.println(Arrays.toString(lua.eval("t2()")));
        // System.out.println(lua.get("t2()"));
    }
}