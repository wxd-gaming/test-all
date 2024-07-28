package wxdgaming.boot.springstarter.scripts;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wxdgaming.boot.LuaBus;
import wxdgaming.boot.springstarter.test.B1;

/**
 * 测试
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-07-26 10:35
 **/
@Slf4j
@RestController
public class TestApi {

    @Autowired B1 b1;
    @Autowired B2 b2;

    LuaBus luaBus;

    @PostConstruct
    public void init() {
        luaBus = LuaBus.buildFromDirs("lua");

    }

    @RequestMapping("/**")
    public ResponseEntity<?> all(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @RequestBody(required = false) String body) throws Exception {
        luaBus.get("index").call(
                CoerceJavaToLua.coerce(httpServletRequest),
                CoerceJavaToLua.coerce(httpServletResponse)
        );
        return ResponseEntity.ok("ok-" + b1);
    }

    @RequestMapping("/api")
    public ResponseEntity<?> api(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @RequestBody(required = false) String body) throws Exception {

        return ResponseEntity.ok("ok-" + b1);
    }

    @GetMapping("/users/{userId}")
    public String getUserById(@PathVariable String userId) {
        return "User Id is: " + userId;
    }

}
