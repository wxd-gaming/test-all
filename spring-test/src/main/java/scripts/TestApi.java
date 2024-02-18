package scripts;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi {

    @RequestMapping(value = "/test")
    public String test() {
        return "ok";
    }

}
