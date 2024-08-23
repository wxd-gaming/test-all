package test;

import io.github.taoguan.luaj.compiler.LuaC;
import io.github.taoguan.luaj.lib.*;
import io.github.taoguan.luaj.lib.jse.*;
import io.github.taoguan.luaj.luajc.LuaJC;

public class TaoguanLuaMain {

    public static void main(String[] args) {
        io.github.taoguan.luaj.Globals globals = new io.github.taoguan.luaj.Globals();
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
        io.github.taoguan.luaj.LoadState.install(globals);
        LuaC.install(globals);
        LuaJC.install(globals);
        globals.load(new DebugLib());
        String file = "lua4/src/main/lua/test.lua";
        globals.loadfile(file).call();
        /*TODO 基于5.2的实现 支持 位运算 << >> 但是不支持 long*/
        System.out.println(Long.MAX_VALUE);
        globals.get("t2").call();
    }

}