package wxdgaming.boot.springstarter.batis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import wxdgaming.boot.batis.sql.mysql.MysqlDataHelper;
import wxdgaming.boot.springstarter.config.DataBaseConfig;

/**
 * mysql
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-12-11 18:18
 **/
@Service
@ConditionalOnProperty(value = "database.mysql2.dbHost")
public class MysqlService2 extends MysqlDataHelper {

    @Autowired
    public MysqlService2(DataBaseConfig dataBaseConfig) {
        super(dataBaseConfig.getMysql2());
    }


}
