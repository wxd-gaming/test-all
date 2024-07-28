package wxdgaming.boot.springstarter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wxdgaming.boot.springstarter.BootStarter;

/**
 * gm
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-07-28 13:10
 **/
@RestController
public class GMService {

    @Autowired LuaService luaService;

    @GetMapping("/reload/jar")
    public String reload_jar() throws Exception {
        BootStarter.reload();
        return "reload/jar";
    }

    @GetMapping("/reload/lua")
    public String reload_lua() throws Exception {
        luaService.init();
        return "reload/lua";
    }

}
