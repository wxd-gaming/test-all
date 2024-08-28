package test;

import com.alibaba.fastjson.JSON;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LoadState;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.compiler.LuaC;
import org.luaj.vm2.lib.*;
import org.luaj.vm2.lib.jse.*;

import java.util.Arrays;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-22 20:39
 **/
public class LuajMain {

    public static void main(String[] args) {
        /* TODO 测试下来还是5.2.x */
        Globals globals = new Globals();
        globals.load(new JseBaseLib());
        globals.load(new PackageLib());
        globals.load(new Bit32Lib());
        globals.load(new TableLib());
        globals.load(new StringLib());
        globals.load(new CoroutineLib());
        globals.load(new JseMathLib());
        globals.load(new JseIoLib());
        globals.load(new JseOsLib());
        globals.load(new LuajavaLib());
        /* TODO 异常的时候可以打印lua调用堆栈，但是如果加入了  LuaJC.install(globals); 将无效 */
        globals.load(new DebugLib());
        LoadState.install(globals);
        LuaC.install(globals);
        /* TODO 如果加入了 globals.load(new DebugLib()); 将无效 */
        // LuaJC.install(globals);

        /*TODO 可以直接运行文件 也是加载和编译文件 */
        globals.loadfile("luaj/src/main/lua/test1.lua").call();
        globals.loadfile("luaj/src/main/lua/test2.lua").call();
        /*注册函数*/
        globals.set("javaT3", new VarArgFunction() {

            @Override public Varargs onInvoke(Varargs args) {
                /*参数的数量*/
                int narg = args.narg();
                Object[] objects = new Object[narg];
                for (int i = 1; i <= narg; i++) {
                    LuaValue arg = args.arg(i);
                    objects[i - 1] = String.valueOf(arg);
                }
                System.out.println(narg + " - " + Arrays.toString(objects));
                return LuaValue.valueOf("javaT3");
            }
        });

        LuaValue fun = globals.get("t2");
        LuaValue[] funcArgs = new LuaValue[2];
        funcArgs[0] = LuajUtils.valueOf("3-");
        funcArgs[1] = LuajUtils.valueOf(new Object[]{1, "2"});
        Varargs invoke = fun.invoke(funcArgs);
        LuaValue luaValue = invoke.arg1();
        Object o = LuajUtils.luaValue2Object(luaValue);
        System.out.println(JSON.toJSONString(o));

    }

}
