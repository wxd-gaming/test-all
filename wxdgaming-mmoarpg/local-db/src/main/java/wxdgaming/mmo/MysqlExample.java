package wxdgaming.mmo;


import com.mysql.management.MysqldResource;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 本地mysql服务
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-07-17 16:16
 */
public class MysqlExample {

    public static void main(String[] args) throws Exception {

        System.setProperty("os.name", "Win");
        System.setProperty("os.arch", "x86");
        System.setProperty("windows-kill-command", "close-mysql.ps1");
        MysqldResource mysqldResource = new MysqldResource(
                new File("db"),
                null,
                null
        );
        mysqldResource.setKillDelay(300);
        Map<String, Object> mapArgs = new HashMap<>();
        mapArgs.put("port", "3308");
        mapArgs.put("kill-delay", 300);
        mapArgs.put("initialize-user", "true");
        mapArgs.put("initialize-user.user", BaseConnectionPool._USER);
        mapArgs.put("initialize-user.password", BaseConnectionPool._PASSWORD);
        mysqldResource.start("mysql", mapArgs);
        System.out.println("Hello world!");
        System.in.read();

        mysqldResource.shutdown();

    }
}
