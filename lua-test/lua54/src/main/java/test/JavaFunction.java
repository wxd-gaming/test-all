package test;

import party.iroiro.luajava.JFunction;
import party.iroiro.luajava.Lua;
import party.iroiro.luajava.value.LuaTableValue;
import party.iroiro.luajava.value.LuaValue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * JFun
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-26 11:01
 */
public interface JavaFunction extends JFunction {
    public static Object luaValue2Object(LuaValue luaValue) {
        if (luaValue.type() == Lua.LuaType.NUMBER) {
            long integer = luaValue.toInteger();
            if (integer == (int) integer) {
                return (int) integer;
            }
            double number = luaValue.toNumber();
            if (integer == number) {
                return integer;
            }
            return number;

        } else if (luaValue.type() == Lua.LuaType.TABLE) {
            Object javaObject = luaValue.toJavaObject();
            LuaTableValue luaTableValue = (LuaTableValue) luaValue;
            Map<Object, Object> map = new HashMap<>();
            for (Map.Entry<LuaValue, LuaValue> entry : luaTableValue.entrySet()) {
                map.put(luaValue2Object(entry.getKey()), luaValue2Object(entry.getValue()));
            }
            return map;
        } else if (luaValue.type() == Lua.LuaType.NONE || luaValue.type() == Lua.LuaType.NIL) {
            return null;
        }
        return luaValue.toJavaObject();
    }

    @Override default int __call(Lua L) {
        try {
            int oldTop = L.getTop();
            Object[] _args = new Object[oldTop];
            for (int i = 0; i < _args.length; i++) {
                LuaValue luaValue1 = L.get();
                Object javaObject = luaValue2Object(luaValue1);
                _args[_args.length - i - 1] = javaObject;
            }
            L.setTop(oldTop);
            Object results = doAction(L, _args);
            if (results == null) {
                L.pushNil();
            } else if (results instanceof Map) {
                L.push((Map) results);
            } else if (results instanceof Collection<?>) {
                L.push((Collection) results);
            } else if (results instanceof Number) {
                L.push((Number) results);
            } else if (results.getClass().isArray()) {
                L.pushArray(results);
            } else {
                L.push(results, Lua.Conversion.SEMI);
            }
            return 1;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    Object doAction(Lua L, Object[] args);

}
