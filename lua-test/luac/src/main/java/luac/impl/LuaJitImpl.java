package luac.impl;

import luac.LuaLong;
import party.iroiro.luajava.AbstractLua;
import party.iroiro.luajava.LuaException;
import party.iroiro.luajava.cleaner.LuaReference;
import party.iroiro.luajava.luajit.LuaJit;
import party.iroiro.luajava.value.LuaValue;

/**
 * 子类，重写 Lua54 pushArray to pushJavaArray
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-21 16:16
 */
public class LuaJitImpl extends LuaJit {

    private final String name;
    private boolean closed = false;

    public LuaJitImpl() throws LinkageError {
        this.name = Thread.currentThread().getName();
    }

    public LuaJitImpl(long L, int id, AbstractLua main) {
        super(L, id, main);
        this.name = Thread.currentThread().getName();
    }

    @Override public LuaJitImpl newThread() {
        return (LuaJitImpl) super.newThread();
    }

    @Override protected LuaJitImpl newThread(long L, int id, AbstractLua mainThread) {
        return new LuaJitImpl(L, id, mainThread);
    }

    @Override public LuaValue get() {
        return super.get();
    }

    @Override public LuaValue from(long n) {
        return new LuaLong(this, n);
    }

    @Override public void checkStack(int extra) throws RuntimeException {
        for (LuaReference ref = (LuaReference) this.recyclableReferences.poll(); ref != null; ref = (LuaReference) this.recyclableReferences.poll()) {
            this.recordedReferences.remove(ref.getReference());
            this.unref(ref.getReference());
        }
    }

    @Override public void pushArray(Object array) throws IllegalArgumentException {
        pushJavaArray(array);
    }

    @Override public void pCall(int nArgs, int nResults) throws LuaException {
        super.pCall(nArgs, nResults);
    }

    @Override protected void checkError(int code, boolean runtime) throws LuaException {
        super.checkError(code, runtime);
    }

    @Override public synchronized void close() {
        if (closed) return;
        try {
            super.close();
        } catch (Throwable ignore) {}
        closed = true;
    }

    @Override public String toString() {
        return "LuaJit{" + "name='" + name + '\'' + ", closed=" + closed + '}';
    }
}
