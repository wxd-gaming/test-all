package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LoadState;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.compiler.LuaC;
import org.luaj.vm2.lib.*;
import org.luaj.vm2.lib.jse.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-22 20:39
 **/
public class LuajMain {

    public static void main(String[] args) throws Exception {
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

        Files.walk(Paths.get("lua"), 99)
                .filter(p -> {
                    String string = p.toString();
                    return string.endsWith(".lua") || string.endsWith(".LUA");
                })
                .filter(Files::isRegularFile)
                .forEach(path -> {
                    String filePath = path.toString();
                    try {
                        globals.loadfile(filePath).call();
                        System.out.println("load lua file: " + filePath.toString());
                    } catch (Exception e) {
                        e.printStackTrace(System.err);
                    }
                });

        /*TODO 可以直接运行文件 也是加载和编译文件 */
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

        LuaValue fun = globals.get("gameDebugT2");

        Object key = 19126499763390465L;
        Object[] vs = {1, "2"};
        List<Map> list = new ArrayList<>();
        list.add(new JSONObject().fluentPut("fid", 19126499763390464L).fluentPut("sid", 19126499763390465L));

        LuaValue[] luaValues = LuajUtils.vsArray(key, vs, list);

        Varargs invoke = fun.invoke(luaValues);

        LuaValue luaValue = invoke.arg1();
        Object o = LuajUtils.luaValue2Object(luaValue);
        System.out.println(JSON.toJSONString(o));

    }

}
