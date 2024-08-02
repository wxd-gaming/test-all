package wxdgaming.boot.spring.starter.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wxdgaming.boot.spring.data.repository.UserRepository;
import wxdgaming.boot.spring.starter.BootStarter;

/**
 * gm
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-07-28 13:10
 **/
@Controller
public class GMController {

    @Autowired UserRepository userRepository;


    @ResponseBody
    @RequestMapping("/reload/jar")
    public String reload_jar() throws Exception {
        BootStarter.reload();
        return "reload/jar";
    }

    @ResponseBody
    @RequestMapping("/reload/lua")
    public String reload_lua(HttpServletRequest httpServletRequest,
                             @RequestBody(required = false) String body) throws Exception {
        return "ok";
    }

}
