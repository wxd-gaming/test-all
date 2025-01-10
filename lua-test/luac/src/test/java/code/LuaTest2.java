package code;

import lombok.extern.slf4j.Slf4j;
import luajava.ILuaContext;
import luajava.LuaService;
import luajava.LuaType;
import luajava.bean.LuaActor;
import luajava.bean.LuaGmVar;
import luajava.bean.LuaMap;
import luajava.luac.LuaFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import party.iroiro.luajava.Lua;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 测试
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-21 19:21
 **/
@Slf4j
public class LuaTest2 {

    public static void before() {
        try {
            /*TODO 必须要等他初始化完成*/
            PrintStream printStream = new PrintStream(System.out) {

                @Override public void write(@NotNull byte[] b) throws IOException {
                    super.write(b);
                }

                @Override public PrintStream append(char c) {
                    return super.append(c);
                }

                @Override public PrintStream append(CharSequence csq) {
                    return super.append(csq);
                }

                @Override public PrintStream append(CharSequence csq, int start, int end) {
                    return super.append(csq, start, end);
                }

                @Override public PrintStream format(@NotNull String format, Object... args) {
                    return super.format(format, args);
                }

                @Override public PrintStream format(Locale l, @NotNull String format, Object... args) {
                    return super.format(l, format, args);
                }

                @Override public void print(boolean b) {
                    super.print(b);
                }

                @Override public void print(char c) {
                    super.print(c);
                }

                @Override public void print(double d) {
                    super.print(d);
                }

                @Override public void print(float f) {
                    super.print(f);
                }

                @Override public void print(int i) {
                    super.print(i);
                }

                @Override public void print(long l) {
                    super.print(l);
                }

                @Override public void print(@NotNull char[] s) {
                    super.print(s);
                }

                @Override public PrintStream printf(@NotNull String format, Object... args) {
                    return super.printf(format, args);
                }

                @Override public PrintStream printf(Locale l, @NotNull String format, Object... args) {
                    return super.printf(l, format, args);
                }

                @Override public void println() {
                    super.println();
                }

                @Override public void println(boolean x) {
                    super.println(x);
                }

                @Override public void println(char x) {
                    super.println(x);
                }

                @Override public void println(@NotNull char[] x) {
                    super.println(x);
                }

                @Override public void println(double x) {
                    super.println(x);
                }

                @Override public void println(float x) {
                    super.println(x);
                }

                @Override public void println(int x) {
                    super.println(x);
                }

                @Override public void println(long x) {
                    super.println(x);
                }

                @Override public void println(@Nullable Object x) {
                    super.println(x);
                }

                @Override public void println(@Nullable String x) {
                    super.println(x);
                }

                @Override public void write(int b) {
                    super.write(b);
                }

                @Override public void write(@NotNull byte[] buf, int off, int len) {
                    super.write(buf, off, len);
                }

                @Override public void print(Object obj) {
                    print(String.valueOf(obj));
                }

                @Override public void print(String x) {
                    log.info(x);
                }
            };
            System.setOut(printStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {

        before();

        LuaService luaService = LuaService.of(LuaType.LUA54, true, "lua");
        HashMap<String, Object> value = new HashMap<>();
        LuaGmVar localVar = new LuaGmVar();

        luaService.getRuntime().getGlobals().put("print", new LuaFunction() {
            @Override public Object doAction(Lua L, Object[] args) {
                System.out.println(Arrays.stream(args).map(v -> String.valueOf(v)).collect(Collectors.joining(" ")));
                return null;
            }
        });

        luaService.getRuntime().getGlobals().put("getdata", new LuaFunction() {
            @Override public Object doAction(Lua L, Object[] args) {
                return value.get(String.valueOf(args[0]));
            }
        });

        luaService.getRuntime().getGlobals().put("setdata", new LuaFunction() {
            @Override public Object doAction(Lua L, Object[] args) {
                return value.put(String.valueOf(args[0]), args[1]);
            }
        });

        luaService.getRuntime().getGlobals().put("getvardata", new LuaFunction() {
            @Override public Object doAction(Lua L, Object[] args) {
                if (args.length == 2)
                    return localVar.getVar(String.valueOf(args[0]), args[1]);
                return localVar.getVar(String.valueOf(args[0]));
            }
        });

        luaService.getRuntime().getGlobals().put("setvardata", new LuaFunction() {
            @Override public Object doAction(Lua L, Object[] args) {
                if (args.length == 2) {
                    Object arg = args[1];
                    if (arg instanceof Map) {
                        localVar.setVar(String.valueOf(args[0]), (Map) arg);
                    } else if (arg instanceof Number || arg instanceof String) {
                        localVar.removeVar(String.valueOf(args[0]), arg);
                    }
                } else if (args.length == 1) {
                    localVar.removeVar(String.valueOf(args[0]));
                } else {
                    localVar.setVar(String.valueOf(args[0]), args[1], args[2]);
                }
                return null;
            }
        });

        ILuaContext context = luaService.getRuntime().context();
        context.call(true, "testActor", new LuaActor(7788L, "7788L"));
        // while (true){
        //     Thread.sleep(3000);
        context.call(true, "forTable0");
        // }
        context.call(true, "onInit");
        context.call(true, "onLogin", new LuaActor(7788L, "7788L"));
        context.call(true, "onEnterMap", new LuaMap(9527, 9527), new LuaActor(7788L, "7788L"));

        // luaService.getRuntime().call("printData");
        // luaService.getRuntime().call("showmemory", Thread.currentThread().getName());
        // luaService.getRuntime().call("t3", Long.MAX_VALUE);
        // luaService.getRuntime().call("cache_memory");
        // luaService.getRuntime().call("printData");

        // luaService.getRuntime().call("showmemory", Thread.currentThread().getName());
        Thread.sleep(500);
    }


}
