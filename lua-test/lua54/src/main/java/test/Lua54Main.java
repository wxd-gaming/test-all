package test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import party.iroiro.luajava.Consts;
import party.iroiro.luajava.LuaException;
import party.iroiro.luajava.value.LuaValue;

import java.lang.reflect.Method;

@Slf4j
public class Lua54Main {


    /* TODO 测试下来还是5.4.x 真正的long 支持 */
    public static void main(String[] args) throws Exception {
        LuaRuntime luaRuntime = new LuaRuntime();

        /*TODO 可以直接运行文件 也是加载和编译文件 */
        luaRuntime.loadDir("lua54/src/main/lua");

        Method javaT3 = Lua54Main.class.getMethod("javaT3", String.class);

        luaRuntime.pushJFunction(null, javaT3);

        // /*获取当前方法*/
        // ArrayList<CompletableFuture<Void>> list = new ArrayList<>();
        // for (int i = 0; i < 2; i++) {
        //     int fi = i;
        //     CompletableFuture<Void> t3 = CompletableFuture.runAsync(() -> {
        //
        //         try {
        //
        //             long nanoTime = System.nanoTime();
        //             LuaValue tests = luaRuntime.call("t3", "test-" + fi);
        //             System.out.println("调用返回结果"
        //                     + " - " + Thread.currentThread().getName()
        //                     + " - " + fi
        //                     + " - " + String.valueOf(tests)
        //                     + " 耗时：" + ((System.nanoTime() - nanoTime) / 10000 / 100f) + " ms"
        //             );
        //
        //         } catch (Throwable throwable) {
        //             System.out.println(Thread.currentThread().getName() + " - " + throwable.toString());
        //         }
        //
        //     });
        //     list.add(t3);
        // }
        // CompletableFuture.allOf(list.toArray(new CompletableFuture[0])).get();

        Lua712_54 lua = luaRuntime.getThreadLocal().get();

        LuaValue[] call = null;
        try {

            LuaValue luaValue = lua.get("t2");
            luaValue.push(lua);
            lua.push("3-");
            Object[] objects = {1, "2"};
            lua.pushJavaArray(objects);
            lua.pCall(2, Consts.LUA_MULTRET, 0);
            Object javaObject = JavaFunction.luaValue2Object(lua.get());
            System.out.println(JSON.toJSONString(javaObject));
        } catch (Exception e) {
            /* TODO 无法获取处理lua堆栈，只能通过 print(debug.traceback()) */
            LuaException luaException = (LuaException) e;
            luaException.printStackTrace();
        }
        luaRuntime.getLua54().close();
    }

    public static void javaT3(String str) {
        System.out.println("java 接收 lua 传递参数 - " + str);
    }

}