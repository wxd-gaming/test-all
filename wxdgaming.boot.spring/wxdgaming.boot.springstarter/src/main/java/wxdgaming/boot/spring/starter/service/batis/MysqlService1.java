package wxdgaming.boot.spring.starter.service.batis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import wxdgaming.boot.batis.sql.mysql.MysqlDataHelper;
import wxdgaming.boot.spring.a.DataBaseConfig;

/**
 * mysql
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-12-11 18:18
 **/
@Order(101)
@Component
@ConditionalOnProperty(value = "database.mysql1.dbHost")
public class MysqlService1 extends MysqlDataHelper {

    @Autowired
    public MysqlService1(DataBaseConfig dataBaseConfig) {
        super(dataBaseConfig.getMysql1());
    }


}
