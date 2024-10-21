package code;

import lombok.extern.slf4j.Slf4j;
import luac.LuaService;
import luac.LuacType;
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

    public void testLUA54() {
        luaService = LuaService.of(LuacType.LUA54, false, true, "../lua");
    }

    @Test
    public void t00() {
        log.info("ddd");
        testLUA54();
        luaService.getRuntime().call("showmemory", Thread.currentThread().getName());
    }


}
