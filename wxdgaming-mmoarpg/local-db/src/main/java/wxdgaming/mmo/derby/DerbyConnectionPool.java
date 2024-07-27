package wxdgaming.mmo.derby;

import wxdgaming.mmo.BaseConnectionPool;

/**
 * Derby 数据库
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-07-16 20:41
 **/
public class DerbyConnectionPool extends BaseConnectionPool {

    // Derby的JDBC驱动名称
    protected final String driver = org.apache.derby.jdbc.EmbeddedDriver.class.getName();

    public DerbyConnectionPool(String dataBase) {
        super(dataBase);
        init();
    }

    public DerbyConnectionPool(String USER, String PASSWORD, String dataBaseName) {
        super(USER, PASSWORD, dataBaseName);
        init();
    }

    protected void init() {
        System.setProperty("derby.stream.error.file", "data-base/derby/derby.log");
        db_url = "jdbc:derby:data-base/derby/" + getDataBaseName() + ";IGNORECASE=TRUE;auto_server=true;database_to_upper=false;create=true";
        // 注册JDBC驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override protected String checkTableSql(String tableSql) {
        String s = super.checkTableSql(tableSql);
        s = s.replace("`", "")
                .replace(";", "");
        return s;
    }
}
