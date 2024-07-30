package springmariadbserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * 接口
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-07-29 16:08
 **/
@RestController
public class ApiController {

    @Autowired DBService dbService;

    @RequestMapping("/api/db/stop")
    public String stop() {
        CompletableFuture.runAsync(() -> {
            dbService.stop();
            Runtime.getRuntime().halt(0);
        });
        return "ok";
    }


    @RequestMapping("/api/db/backup")
    public String backup() {
        CompletableFuture.runAsync(() -> {
            dbService.getMyDB().bakSql();
        });
        return "ok";
    }

}
