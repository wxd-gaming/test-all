package wxdgaming.boot.springstarter.batis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import wxdgaming.boot.batis.sql.mysql.MysqlDataHelper;
import wxdgaming.boot.springstarter.config.db.DataBaseConfig;

import java.sql.SQLNonTransientConnectionException;

/**
 * mysql
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-12-11 18:18
 **/
@Service
@ConditionalOnProperty(value = "database.mysql.dbHost")
public class MysqlService extends MysqlDataHelper {

    @Autowired
    public MysqlService(DataBaseConfig dataBaseConfig) {
        super(dataBaseConfig.getMysql());
    }
}
