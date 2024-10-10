package test;

import com.alibaba.fastjson.JSONObject;

/**
 * 测试
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-09 19:08
 **/
public class Lua54Main {

    public static void main(String[] args) throws Exception {
        LuaEventBus luaEventBus = LuaEventBus.buildFromDirs("lua");
        long start = System.currentTimeMillis();
        luaEventBus.pcall("testNow", new JSONObject().fluentPut("now", start).fluentPut("nowsec", (int) (start / 1000)));
        luaEventBus.pcall("debugT3");
    }

    public static void test(LuaContext context) {

    }

}
