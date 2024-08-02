package code;

import org.junit.Before;
import org.junit.Test;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import wxdgaming.boot.LuaBus;

import java.util.function.BiConsumer;

public class ParamsTest {

    LuaBus luaBus;

    @Before
    public void before() {
        luaBus = LuaBus.buildFromDirs("src/lua");
    }

    @Test
    public void t1() {
        luaBus.forExec("paramsTest", new BiConsumer<String, LuaValue>() {
            @Override public void accept(String s, LuaValue luaValue) {
                System.out.println(luaValue);
            }
        }, 1, "p2", new ParamsTest(), "p4");
    }

}
