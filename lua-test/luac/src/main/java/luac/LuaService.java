package luac;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * lua 服务
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-21 16:11
 */
@Slf4j
public class LuaService {

    private HashMap<String, LuaRuntime> runtimeHashMap = new HashMap<>();

    public static LuaService of(LuacType luacType, boolean useModule, boolean xpcall, String paths) {
        LuaService luaService = new LuaService();
        if (useModule) {
            Path script_path = Paths.get(paths + "/script");
            try {
                Files.walk(script_path, 1)
                        .filter(Files::isDirectory)
                        .forEach(dir -> {
                            if (dir.equals(script_path)) return;
                            log.info("load lua module：{} - {}", dir, dir.getFileName());
                            LuaRuntime luaRuntime = new LuaRuntime(luacType, dir.getFileName().toString(), xpcall, new Path[]{dir, Paths.get(paths + "/util")});
                            luaService.addRuntime(luaRuntime);
                        });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            LuaRuntime luaRuntime = new LuaRuntime(luacType, "root", xpcall, new Path[]{Paths.get(paths)});
            luaService.addRuntime(luaRuntime);
        }
        return luaService;
    }

    public LuaRuntime getRuntime() {
        return runtimeHashMap.get("root");
    }

    public LuaRuntime getRuntime(String name) {
        return runtimeHashMap.get(name);
    }

    public void addRuntime(LuaRuntime runtime) {
        runtimeHashMap.put(runtime.getName(), runtime);
    }

    public void close() {
        for (LuaRuntime runtime : runtimeHashMap.values()) {
            runtime.close();
        }
    }

}
