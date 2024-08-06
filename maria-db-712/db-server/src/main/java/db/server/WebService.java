package db.server;


import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-07-29 16:46
 **/
@Slf4j
public class WebService {

    @Getter private static final WebService ins = new WebService();

    WebService() {}

    HttpServer httpServer;

    public void start() throws Exception {
        httpServer = HttpServer.create(new InetSocketAddress(19902), 0);

        HttpHandler httpHandler = exchange -> {

            byte[] bytes = "ok".getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            exchange.getResponseHeaders().add("Access-Control-Max-Age", "3600");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "x-requested-with,content-type");
            exchange.sendResponseHeaders(200, bytes.length);
            exchange.getResponseBody().write(bytes);
            exchange.getResponseBody().flush();
            exchange.close();
        };

        httpServer.createContext("/api/db/stop", exchange -> {
            httpHandler.handle(exchange);
            DBFactory.getIns().stop();
        });

        httpServer.createContext("/api/db/check", exchange -> {
            httpHandler.handle(exchange);
        });
        httpServer.start();
        log.info("http://localhost:19902/api/db/stop");
        log.info("http://localhost:19902/api/db/check");
    }

}
