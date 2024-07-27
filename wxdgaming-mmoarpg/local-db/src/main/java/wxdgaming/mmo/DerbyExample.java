package wxdgaming.mmo;


import wxdgaming.mmo.derby.DerbyConnectionPool;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public class DerbyExample {

    public static void main(String[] args) throws Exception {
        DerbyConnectionPool test = new DerbyConnectionPool("test");
        test.createTable("\n" +
                "create table `users` (\n" +
                "  id  bigint primary key,\n" +
                "  name varchar(20),\n" +
                "  email varchar(20),\n" +
                "  country varchar(20),\n" +
                "  password varchar(20)\n" +
                "  );");

        test.createTable("CREATE TABLE `s_sql_version`\n" +
                "(\n" +
                "    `createTime` varchar(64) NOT NULL\n" +
                ") ;"
        );

        test.update("INSERT INTO s_sql_version VALUES ('2021-01-01 00:00:00')");

        insert(test);

        // CountDownLatch countDownLatch = new CountDownLatch(1000);
        // long count = countDownLatch.getCount();
        // for (int i = 0; i < count; i++) {
        //     CompletableFuture.runAsync(() -> {
        //         insert(test);
        //         countDownLatch.countDown();
        //     });
        // }
        // countDownLatch.await();

        test.queryAll("select * from users", new Object[]{}, row -> {
            System.out.println(row.toJSONString());
        });
    }

    public static void insert(DerbyConnectionPool connectPool) {
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
