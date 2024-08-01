package wxdgaming.mmo;

import java.sql.*;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-07-23 13:44
 **/
public class EmbeddedMysqlDemo {
    public static void main(String[] args) throws Exception {

        String databaseName = "test";
        String url = String.format("jdbc:mysql://embedded:6066/%s", databaseName);

        try (Connection conn = DriverManager.getConnection(url, "root", "")) {

            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE if not exists employees (id INT PRIMARY KEY, name VARCHAR(255))";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO employees (id, name) VALUES (1, 'John')";
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
