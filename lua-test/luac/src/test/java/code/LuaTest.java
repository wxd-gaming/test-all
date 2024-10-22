package code;

import lombok.extern.slf4j.Slf4j;
import luajava.LuaService;
import luajava.LuaType;
import org.junit.After;
import org.junit.Test;

/**
 * 测试
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-21 19:21
 **/
@Slf4j
public class LuaTest {

    LuaService luaService;

    public void createLUA54() {
        luaService = LuaService.of(LuaType.LUA54, false, true, "../lua");
    }

    public void createLUAJit() {
        luaService = LuaService.of(LuaType.LUAJit, false, true, "../lua");
    }

    @Test
    public void tLUAJit() {
        createLUAJit();
    }

    @Test
    public void tLUA54() {
        createLUA54();
    }

    @After
    public void after() {
        luaService.getRuntime().call("showmemory", Thread.currentThread().getName());
        luaService.getRuntime().call("t3", Long.MAX_VALUE);
        luaService.getRuntime().call("cache_memory");
        luaService.getRuntime().call("showmemory", Thread.currentThread().getName());
    }

}
