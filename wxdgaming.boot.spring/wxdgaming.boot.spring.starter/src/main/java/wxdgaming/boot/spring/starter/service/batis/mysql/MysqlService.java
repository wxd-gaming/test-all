package wxdgaming.boot.spring.starter.service.batis.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import wxdgaming.boot.batis.sql.mysql.MysqlDataHelper;
import wxdgaming.boot.spring.starter.service.batis.DataBaseConfig;

/**
 * mysql
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-12-11 18:18
 **/
@Component
@ConditionalOnProperty(value = "wxdgaming.database.mysql.dbHost")
public class MysqlService extends MysqlDataHelper implements Ordered {

    @Override public int getOrder() {
        return 10;
    }

    @Autowired
    public MysqlService(DataBaseConfig dataBaseConfig) {
        super(dataBaseConfig.getMysql());
    }
}
