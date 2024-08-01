package wxdgaming.boot.springlua.serice;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * lua service
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-07-31 20:08
 **/
@Slf4j
@Getter
@Service
public class LuaService {

    private Globals globals;

    public LuaService() {

    }

    @PostConstruct
    public void init() throws IOException {
        this.globals = JsePlatform.standardGlobals();
        this.globals.set("responseUtil", CoerceJavaToLua.coerce(new ResponseUtil()));
        Files.walk(Path.of("lua"), 99)
                .map(Path::toFile)
                .filter(File::isFile)
                .forEach(f -> {
                    try {
                        String path = f.getPath();
                        log.info("load lua {}", path);
                        String string = Files.readString(f.toPath());
                        this.globals.load(string, path).call();
                    } catch (Exception e) {
                        log.error("load lua error", e);
                    }
                });
    }

    public LuaValue[] parse(Object... params) {
        LuaValue[] luaValues = new LuaValue[params.length];
        for (int i = 0; i < params.length; i++) {
            luaValues[i] = CoerceJavaToLua.coerce(params[i]);
        }
        return luaValues;
    }

    public LuaValue get(String key) {
        return this.globals.get(key);
    }

    public LuaValue func(String method, Object... params) {
        LuaValue luaValue = this.globals.get(method);
        LuaValue[] luaValues = parse(params);
        Varargs invoke = luaValue.invoke(luaValues);
        LuaValue ret = null;
        if (invoke != null && invoke != LuaValue.NONE && invoke != LuaValue.NIL) {
            ret = invoke.arg1();
        }
        return ret;
    }

}
