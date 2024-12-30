package code;

import lombok.extern.slf4j.Slf4j;
import luajava.ILuaContext;
import luajava.LuaService;
import luajava.LuaType;
import luajava.bean.LuaActor;
import luajava.bean.LuaMap;
import luajava.luac.LuaFunction;
import party.iroiro.luajava.Lua;

import java.util.HashMap;

/**
 * 测试
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-21 19:21
 **/
@Slf4j
public class LuaTest2 {


    public static void main(String[] args) throws Exception {
        LuaService luaService = LuaService.of(LuaType.LUA54, true, "lua");
        HashMap<String, Object> value = new HashMap<>();
        luaService.getRuntime().getGlobals().put("getdata", new LuaFunction() {
            @Override public Object doAction(Lua L, Object[] args) {
                return value.get(String.valueOf(args[0]));
            }
        });

        luaService.getRuntime().getGlobals().put("setdata", new LuaFunction() {
            @Override public Object doAction(Lua L, Object[] args) {
                return value.put(String.valueOf(args[0]), args[1]);
            }
        });

        ILuaContext context = luaService.getRuntime().context();
        context.call(true, "testActor", new LuaActor(7788L, "7788L"));
        // while (true){
        //     Thread.sleep(3000);
        context.call(true, "forTable0");
        // }
        context.call(true, "onInit");
        context.call(true, "onLogin", new LuaActor(7788L, "7788L"));
        context.call(true, "onEnterMap", new LuaMap(9527, 9527), new LuaActor(7788L, "7788L"));

        // luaService.getRuntime().call("printData");
        // luaService.getRuntime().call("showmemory", Thread.currentThread().getName());
        // luaService.getRuntime().call("t3", Long.MAX_VALUE);
        // luaService.getRuntime().call("cache_memory");
        // luaService.getRuntime().call("printData");

        // luaService.getRuntime().call("showmemory", Thread.currentThread().getName());
        Thread.sleep(500);
    }


}
