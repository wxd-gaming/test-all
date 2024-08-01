package wxdgaming.boot.spring.starter.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wxdgaming.boot.spring.starter.BootStarter;

/**
 * gm
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-07-28 13:10
 **/
@RestController
public class GMController {

    @GetMapping("/reload/jar")
    public String reload_jar() throws Exception {
        BootStarter.reload();
        return "reload/jar";
    }

    @GetMapping("/reload/lua")
    public String reload_lua() throws Exception {
        return "reload/lua";
    }

}
