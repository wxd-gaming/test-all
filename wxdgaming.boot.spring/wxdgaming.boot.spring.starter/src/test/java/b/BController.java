package b;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-07-30 20:57
 **/
@Controller
public class BController {

    public BController() {
        System.out.println("BController");
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

}
