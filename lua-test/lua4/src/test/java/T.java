// import java.util.ArrayList;
// import java.util.List;
// import java.util.logging.Logger;
//
// /** 调用Lua脚本 * @author chamcon * @date 2013-11-07 15:58:48 */
// public class LuaUtil {
//     public static List<String> PUBLIC_LUA_FILES = new ArrayList<String>();
//     public static Logger logger = Logger.getLogger(LuaUtil.class);
//
//     static {
//         // 配置公共函数lua文件
//         PUBLIC_LUA_FILES.add("");
//     }
//
//     /** 执行Lua脚本 */
//     public static String execLuaFunc(String lua, String funcName, String value) {
//         // 依赖库文件要放到java.library.path下
//         logger.debug("java.library.path:" + System.getProperty("java.library.path"));
//         LuaState L = LuaStateFactory.newLuaState();        // 加载lua标准库,否则一些lua基本函数无法使用
//         L.openLibs();        // 加载公共函数文件
//         if (!PUBLIC_LUA_FILES.isEmpty()) {
//             for (int i = 0; i < PUBLIC_LUA_FILES.size(); i++) {
//                 L.LdoFile(PUBLIC_LUA_FILES.get(i));
//             }
//         }
//         // 加载函数串
//         L.LdoString(lua);
//         // 调用函数名
//         L.getField(LuaState.LUA_GLOBALSINDEX, funcName);
//         // 设置参数
//         L.pushString(value);
//         // 调用
//         L.call(1, 1);
//         // 设置返回对象
//         L.setField(LuaState.LUA_GLOBALSINDEX, "RESULT");
//         LuaObject lobj = L.getLuaObject("RESULT");
//         // 获取返回值		String res = lobj.getString();		L.close();		if(res == null) return "";		return res;	}}
