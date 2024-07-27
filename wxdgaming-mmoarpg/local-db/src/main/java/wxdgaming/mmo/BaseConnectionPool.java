package wxdgaming.mmo;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.List;

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
    public static final String _PASSWORD = "test";

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

    public boolean createTable(String tableSql) {
        String tmp = checkTableSql(tableSql);
        return update(tmp);
    }

    protected String checkTableSql(String tableSql) {
        return tableSql;
    }

    public boolean update(String sql) {
        try (Connection connection = getConnection()) {
            return connection.createStatement().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int update(String sql, Object[] params) {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                for (int i = 1; i <= params.length; i++) {
                    preparedStatement.setObject(i, params[i - 1]);
                }
                String string = preparedStatement.toString();
                System.out.println(string);
                return preparedStatement.executeUpdate();
            } finally {
                connection.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int updateBatch(String sql, List<Object[]> paramList) {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            int count = 0;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                for (Object[] params : paramList) {
                    preparedStatement.clearParameters();
                    for (int i = 1; i <= params.length; i++) {
                        preparedStatement.setObject(i, params[i - 1]);
                    }
                    count += preparedStatement.executeUpdate();
                }
                return count;
            } finally {
                connection.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void queryAll(String sql, Object[] params, Consumer consumer) {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                for (int i = 1; i <= params.length; i++) {
                    preparedStatement.setObject(i, params[i - 1]);
                }
                String string = preparedStatement.toString();
                System.out.println(string);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    JSONObject rowMap = new JSONObject(true);
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    for (int j = 1; j < columnCount + 1; j++) {
                        Object object = resultSet.getObject(j);
                        String columnName = resultSet.getMetaData().getColumnLabel(j);
                        rowMap.put(columnName, object);
                    }
                    consumer.accept(rowMap);
                }
            } finally {
                connection.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface Consumer {
        void accept(JSONObject row) throws Exception;
    }

}
