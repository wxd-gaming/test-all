package scripts;

import lombok.extern.slf4j.Slf4j;
import org.example.HelloController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestApi2 {

    @Autowired HelloController helloController;
    @Autowired TestApi testApi;

    @RequestMapping(value = "/test2")
    public String test2() throws Exception {
        log.debug("{}", testApi);
        return "test2";
    }

}
