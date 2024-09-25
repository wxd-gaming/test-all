package test;

import party.iroiro.luajava.AbstractLua;
import party.iroiro.luajava.LuaException;
import party.iroiro.luajava.lua54.Lua54;
import party.iroiro.luajava.value.LuaValue;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-27 15:48
 **/
public class Lua712_54 extends Lua54 {

    public Lua712_54() throws LinkageError {
    }

    public Lua712_54(long L, int id, AbstractLua main) {
        super(L, id, main);
    }

    @Override public Lua712_54 newThread() {
        return (Lua712_54) super.newThread();
    }

    @Override protected Lua712_54 newThread(long L, int id, AbstractLua mainThread) {
        return new Lua712_54(L, id, mainThread);
    }

    @Override public LuaValue get() {
        return super.get();
    }

    @Override public LuaValue from(long n) {
        return new LuaLong(this, n);
    }

    @Override public void checkStack(int extra) throws RuntimeException {
        super.checkStack(extra);
    }

    @Override public void pushArray(Object array) throws IllegalArgumentException {
        pushJavaArray(array);
    }

    public void pCall(int nArgs, int nResults, int errfunc) throws LuaException {
        checkStack(Math.max(nResults - nArgs - 1, 0));
        checkError(C.lua_pcall(L, nArgs, nResults, errfunc), false);
    }

    @Override protected void checkError(int code, boolean runtime) throws LuaException {
        super.checkError(code, runtime);
    }

    @Override public void pCall(int nArgs, int nResults) throws LuaException {
        super.pCall(nArgs, nResults);
    }

    @Override public void close() {
        try {
            super.close();
        } catch (Exception ignore) {}
    }
}
