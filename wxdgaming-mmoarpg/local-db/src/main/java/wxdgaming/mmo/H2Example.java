package wxdgaming.mmo;


import org.h2.server.web.WebServer;
import org.h2.tools.Server;
import wxdgaming.mmo.h2.H2ConnectPool;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public class H2Example {

    public static void main(String[] args) throws Exception {
        H2ConnectPool test = new H2ConnectPool(H2ConnectPool.DbType.FILE, "./target/h2_database/test");
        test.update("\n" +
                "create table `users` (\n" +
                "  id  bigint primary key,\n" +
                "  name varchar(20),\n" +
                "  email varchar(20),\n" +
                "  country varchar(20),\n" +
                "  password varchar(20)\n" +
                "  );");

        test.update("CREATE TABLE `s_sql_version`\n" +
                "(\n" +
                "    `createTime` datetime NOT NULL COMMENT '创建时间'\n" +
                ") ;\n" +
                "INSERT INTO `s_sql_version` VALUES ('2021-01-01 00:00:00');");

        CountDownLatch countDownLatch = new CountDownLatch(1000);
        long count = countDownLatch.getCount();
        for (int i = 0; i < count; i++) {
            CompletableFuture.runAsync(() -> {
                insert(test);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

        test.queryAll("select * from users", new Object[]{}, row -> {
            System.out.println(row.toJSONString());
        });

        /*开启web模式*/
        WebServer webServer = new WebServer();
        /*开启web模式*/
        Server server = new Server(
                webServer,
                "-webPort", "4308"/*指定web访问端口*/
                // "-webAdminPassword", "drt" /*密码要128位*/
        );
        server.start();
        System.out.println(server.getURL());
        System.in.read();
    }

    public static void insert(H2ConnectPool connectPool) {
        final String INSERT_USERS_SQL = "INSERT INTO users" +
                "  (id, name, email, country, password) VALUES " +
                " (?, ?, ?, ?, ?)";
        connectPool.updateBatch(
                INSERT_USERS_SQL,
                List.of(
                        new Object[]{System.nanoTime(), "test1", "test", "test", "test"},
                        new Object[]{System.nanoTime(), "test2", "test2", "test", "test"}
                )
        );
    }

}
