package wxdgaming.boot.springstarter.scripts;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping("/api")
    public ResponseEntity<?> api(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @RequestBody(required = false) String body) throws Exception {

        return ResponseEntity.ok("ok-" + b1);
    }

}
