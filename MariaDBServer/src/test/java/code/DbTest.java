package code;

import example.db.DBFactory;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbTest {


    public static void main(String[] args) throws Exception {

        DBFactory.getIns().init();

        // 获取数据源
        MariaDbDataSource dataSource = new MariaDbDataSource(DBFactory.getIns().getConfigBuilder().getURL("test"));
        dataSource.setUser("root");
        dataSource.setPassword("root");

        Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "CREATE TABLE if not exists employees (id INT PRIMARY KEY, name VARCHAR(255))";
        stmt.executeUpdate(sql);

        int cur = (int) (System.currentTimeMillis() / 1000);
        sql = "INSERT INTO employees (id, name) VALUES (" + cur + ", 'John')";
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
