package wxdgaming.mmo;

import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfiguration;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Embedded MariaDB4j
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-07-23 13:37
 **/
public class EmbeddedMariaDB4jExample {

    public static class MyDb extends DB {

        public MyDb(DBConfiguration config) {
            super(config);
            try {
                this.prepareDirectories();
                this.unpackEmbeddedDb();
                this.install();
            } catch (Exception e) {}
        }

    }

    public static void main(String[] args) throws Exception {

        DBConfigurationBuilder configBuilder = DBConfigurationBuilder.newBuilder();
        configBuilder.setPort(3309); // OR, default: setPort(0); => autom. detect free port
        configBuilder.setBaseDir("data-base/mariadb4j"); // just an example
        configBuilder.setDataDir("data-base/mariadb4j/data");


        // DB db = DB.newEmbeddedDB(configBuilder.build());
        DB db = new MyDb(configBuilder.build());
        db.start();
        db.createDB("mm", "root", "root");

        System.out.println("test");
        System.in.read();

        // 获取数据源
        MariaDbDataSource dataSource = new MariaDbDataSource(configBuilder.getURL("mm"));
        dataSource.setUser("root");
        dataSource.setPassword("root");

        Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "CREATE TABLE if not exists employees (id INT PRIMARY KEY, name VARCHAR(255))";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO employees (id, name) VALUES (2, 'John')";
        stmt.executeUpdate(sql);

        System.out.println("Created table and inserted row!");

        // 查询向数据库中插入的数据
        ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

        // 打印查询结果
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            System.out.println(id + ", " + name);
        }
    }

}
