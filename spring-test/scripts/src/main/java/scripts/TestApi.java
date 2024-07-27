package scripts;

import org.example.HelloController;
import org.example.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi {

    @Autowired HelloController helloController;

    @RequestMapping(value = "/test")
    public String test() throws Exception {
        Main.reload();
        return "ok4";
    }

}
