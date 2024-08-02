package wxdgaming.boot.springstarter.scripts;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import wxdgaming.boot.core.lang.RandomUtils;
import wxdgaming.boot.spring.starter.core.BastFilter;
import wxdgaming.boot.spring.starter.test.B1;
import wxdgaming.boot.spring.starter.test.B2;

/**
 * 测试
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-07-26 10:35
 **/
@Slf4j
@RestController
@RequestMapping("/test")
public class TestApi implements BastFilter {

    @Override public void filter(InterceptorRegistration registration) {

    }

    @Autowired B1 b1;
    @Autowired B2 b2;

    public TestApi() {
        System.out.println("\n" + this.getClass());
    }

    @RequestMapping("/lua")
    public ResponseEntity<?> lua(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @RequestBody(required = false) String body) throws Exception {
        return ResponseEntity.ok("ok-" + b1 + " - ");
    }

    @RequestMapping("/api")
    public ResponseEntity<?> api(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @RequestBody(required = false) String body) throws Exception {
        if (RandomUtils.randomBoolean()) {
            throw new RuntimeException("test");
        }
        return ResponseEntity.ok("ok-" + b1);
    }

    @GetMapping("/users/{userId}")
    public String getUserById(@PathVariable String userId) {
        return "User Id is: " + userId;
    }


}
