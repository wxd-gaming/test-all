package test;

import org.luaj.vm2.*;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 陈大侠
 * @date: 2024/6/27 13:08
 * @desc:
 */
public class LuajUtils {

    public static Object luaValue2Object(LuaValue luaValue) {
        if (luaValue instanceof LuaInteger) {
            return luaValue.toint();
        } else if (luaValue instanceof LuaDouble) {
            return luaValue.todouble();
        } else if (luaValue instanceof LuaUserdata) {
            return luaValue.touserdata();
        } else if (luaValue instanceof LuaNumber) {
            return luaValue.todouble();
        } else if (luaValue instanceof LuaBoolean) {
            return luaValue.toboolean();
        } else if (luaValue instanceof LuaString) {
            return luaValue.toString();
        } else if (luaValue instanceof LuaNil) {
            return null;
        } else if (luaValue instanceof LuaTable) {
            LuaTable luaTable = (LuaTable) luaValue;
            LuaValue[] keys = luaTable.keys();
            if (keys.length == 0) {
                return new HashMap<>();
            }
            Map<Object, Object> map = new HashMap<>();
            for (LuaValue key : keys) {
                Object objKey = luaValue2Object(key);
                Object objValue = luaValue2Object(luaTable.get(key));
                map.put(objKey, objValue);
            }
            return map;
        } else {
            throw new RuntimeException("未知lua类型，请处理，luaValue:" + luaValue);
        }
    }

    public static final long LONG_2_DOUBLE_SAFE_VALUE = (long) Math.pow(2, 52);

    public static Object asLuaNum(Object value) {
        if (value instanceof Long) {
            long lVal = (long) value;
            if (lVal <= LONG_2_DOUBLE_SAFE_VALUE && lVal >= -LONG_2_DOUBLE_SAFE_VALUE) {
                return (double) lVal;
            } else {
                return lVal;
            }
        } else {
            return value;
        }
    }

    public static LuaValue[] vsArray(Object... os) {
        LuaValue[] vs = new LuaValue[os.length];
        for (int i = 0; i < os.length; i++) {
            Object o = os[i];
            vs[i] = valueOf(o);
        }
        return vs;
    }

    public static LuaValue valueOf(Object value) {
        if (value == null) {
            return LuaNil.NIL;
        }
        value = asLuaNum(value);

        if (value instanceof Integer) {
            return LuaValue.valueOf(((Integer) value));
        } else if (value instanceof Short) {
            return LuaValue.valueOf(((short) value));
        } else if (value instanceof Double) {
            return LuaValue.valueOf(((double) value));
        } else if (value instanceof Float) {
            return LuaValue.valueOf(((float) value));
        } else if (value instanceof Character) {
            char character = (char) value;
            return LuaValue.valueOf(character);
        } else if (value instanceof Boolean) {
            return LuaValue.valueOf(((boolean) value));
        } else if (value instanceof Byte) {
            byte bt = (byte) value;
            return LuaValue.valueOf(bt);
        } else if (value instanceof Long) {
            // 此处不要是使用 CoerceJavaToLua.coerce(value)，会出现long -> double -> long导致精度丢失BUG
            return LuaValue.userdataOf(value);
        } else if (value instanceof String) {
            return LuaValue.valueOf(value.toString());
        } else if (value instanceof List) {
            LuaTable table = LuaValue.tableOf();
            List<?> list = (List<?>) value;
            for (int i = 0; i < list.size(); i++) {
                Object item = list.get(i);
                LuaValue itemValue = valueOf(item);
                table.set(i + 1, itemValue);
            }
            return table;
        } else if (value instanceof Map) {
            LuaTable table = LuaValue.tableOf();
            Map<?, ?> map = (Map<?, ?>) value;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                Object key = entry.getKey();
                Object val = entry.getValue();
                LuaValue luaKey = valueOf(key);
                LuaValue luaVal = valueOf(val);
                table.set(luaKey, luaVal);
            }
            return table;
        } /* else if (value.getClass().isArray()) {
            Object[] array = (Object[]) value;
            LuaValue[] vs = new LuaValue[array.length];
            for (int i = 0; i < array.length; i++) {
                Object a = array[i];
                vs[i] = valueOf(a);
            }
            return LuaValue.listOf(vs);
        } */ else {
            return CoerceJavaToLua.coerce(value);
        }
    }
}
