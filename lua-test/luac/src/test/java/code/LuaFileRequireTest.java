package code;

import luajava.LuaFileCache;
import luajava.LuaFileRequire;
import luajava.luac.impl.Lua54Impl;

/**
 * 测试
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-21 19:21
 **/
public class LuaFileRequireTest {

    public static void main(String[] args) {
        String dir = "G:\\712-work\\712OutVersion\\tszz\\config\\天使之战_v1.0.0.0\\meta\\userapi\\lua\\";
        LuaFileCache luaFileCache = new LuaFileCache(dir);
        LuaFileRequire luaFileRequire = new LuaFileRequire(luaFileCache);

        Lua54Impl L = new Lua54Impl();
        L.openLibraries();
        // 设置 Lua 文件的搜索路径
        L.getGlobal("package");
        L.getField(-1, "path");
        String currentPath = L.toString(-1);
        String newPath = luaFileRequire.getLuaPath() + ";" + currentPath;
        L.pop(1); // 移除当前路径
        L.push(newPath);
        L.setField(-2, "path");
        L.set("paths", luaFileRequire.getLuaPath());
        System.out.println(luaFileRequire.getLuaPath());
        // 加载并执行 Lua 脚本
        for (String arg : luaFileRequire.getModules()) {
            try {
                System.out.println(arg);
                L.run("require('" + arg + "')");
                // L.run("require('TestData')");
                // L.run("require('ClassTest')");
                // L.run("require('test1')");
            } catch (Exception e) {
                System.out.println("Failed to load or execute the script: " + e.getMessage());
            }
        }
        L.get("test33").call(1, 1);
        L.get("ddd").call(1, 1);

        // 关闭 Lua 状态机
        L.close();
    }

}
