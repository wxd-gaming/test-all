package test;

import java.util.Optional;

/**
 * 测试
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-09 19:08
 **/
public class Lua54Main {

    public static void main(String[] args) throws Exception {
        LuaEventBus luaEventBus = LuaEventBus.buildFromDirs("lua");
        // luaEventBus.getLuaRuntimeMap().values()
        //         .forEach(luaRuntime -> {
        //             LuaContext context = luaRuntime.context();
        //             context.getL().run("local num = 99999999999999999\n" +
        //                                "    print(num)");
        //         });

        long start = System.currentTimeMillis();
        System.out.println(start / 1000);
        // luaEventBus.pcall("debugt3", new JSONObject().fluentPut("now", start).fluentPut("nowsec", (int) (start / 1000)));
        Object pcall = luaEventBus.contextModule("1")
                .context()
                .pcall(
                        "dispatch",
                        "debugt3",
                        1
                );
        // System.out.println(pcall);
    }

    public static void test(LuaContext context) {

    }

}
