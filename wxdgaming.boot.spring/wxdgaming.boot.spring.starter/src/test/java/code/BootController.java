package code;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 合并
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-04 10:32
 **/
@Controller
public class BootController {

    @ResponseBody
    @RequestMapping("reload")
    public String reload() {
        BootStarterTest.load();
        return "ok";
    }

}
