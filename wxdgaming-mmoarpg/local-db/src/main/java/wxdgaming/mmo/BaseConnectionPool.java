package wxdgaming.mmo;

import lombok.Getter;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库链接
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-07-17 16:15
 */
@Getter
public abstract class BaseConnectionPool {

    /** 默认 */
    public static final String _USER = "test";
    /** 默认 */
    public static final String _PASSWORD = "geytghjioqw984jnkdxv89034";

    protected final String USER;
    protected final String PASSWORD;
    protected final String dataBaseName;
    protected String db_url = null;

    public BaseConnectionPool(String dataBaseName) {
        this(_USER, _PASSWORD, dataBaseName);
    }

    public BaseConnectionPool(String USER, String PASSWORD, String dataBaseName) {
        this.USER = USER;
        this.PASSWORD = PASSWORD;
        this.dataBaseName = dataBaseName;
    }

    @SneakyThrows public Connection getConnection() {
        // 与数据库建立连接
        return DriverManager.getConnection(db_url, USER, PASSWORD);
    }

    public void release(Connection connection) throws SQLException {
        try {
            connection.commit();
        } catch (Exception ignore) {}
        try {
            connection.close();
        } catch (Exception ignore) {}
    }

    public boolean update(String sql) {
        try (Connection connection = getConnection()) {
            return connection.createStatement().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
