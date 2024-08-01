package demo;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import wxdgaming.boot.batis.DbConfig;
import wxdgaming.boot.batis.mongodb.MongoDataHelper;
import wxdgaming.boot.batis.mongodb.MongoQueryBuilder;
import wxdgaming.boot.batis.query.QueryWhere;
import wxdgaming.boot.batis.query.WhereEnum;
import wxdgaming.boot.batis.sql.mysql.MysqlDataHelper;
import wxdgaming.boot.core.system.MarkTimer;
import wxdgaming.boot.core.timer.MyClock;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-09-25 09:58
 **/
public class CheckOrder {

    MysqlDataHelper mysqlDataHelper;
    MongoDataHelper mongoDataHelper;

    @Before
    public void beforeClass() throws Exception {
        {
            DbConfig dbConfig = new DbConfig();
            dbConfig
                    .setDbHost("47.108.150.14").setDbPort(3306)
                    .setDbUser("reader")
                    .setDbPwd("readonly1314")
                    .setCreateDbBase(false)
                    .setDbBase("db_log_sj_3");// db_log_sj_57
            mysqlDataHelper = new MysqlDataHelper(dbConfig);
        }
        {
            DbConfig dbConfig = new DbConfig();
            dbConfig
                    .setDbHost("127.0.0.1").setDbPort(27017)
                    .setDbUser("test")
                    .setDbPwd("test")
                    .setCreateDbBase(true)
                    .setDbBase("order_test");// db_log_sj_57
            mongoDataHelper = new MongoDataHelper(dbConfig);
            MongoCollection<Document> order = mongoDataHelper.getCollection("order");
            {
                Document document = new Document();
                document.append("time", 1);
                order.createIndex(document);
            }
            {
                Document document = new Document();
                document.append("order", 1);
                order.createIndex(document);
            }
            {
                Document document = new Document();
                document.append("exchangeType", 1);
                order.createIndex(document);
            }
            {
                Document document = new Document();
                document.append("roleId", 1);
                order.createIndex(document);
            }
            {
                Document document = new Document();
                document.append("platform", 1);
                order.createIndex(document);
            }
            {
                Document document = new Document();
                document.append("serialNumber", 1);
                order.createIndex(document);
            }
        }
    }

    /** 把数据导入本地测试 */
    @Test
    public void inLocalDb() throws Exception {
        mysqlDataHelper.query("SELECT * FROM roleexchangelog", null, row -> {
            Document document = new Document();
            for (Map.Entry<Object, Object> objectEntry : row.entrySet()) {
                document.put(String.valueOf(objectEntry.getKey()), objectEntry.getValue());
            }
            MongoCollection<Document> order = mongoDataHelper.getCollection("order");
            order.insertOne(document);
            return true;
        });
        Thread.sleep(30000);

    }

    @Test
    public void count() throws Exception {
        MarkTimer markTimer = MarkTimer.build();
        MongoQueryBuilder queryBuilder = mongoDataHelper.getDataWrapper().queryWrapper();
//        queryBuilder.select("rmb", QueryEnum.Sum);
        queryBuilder.from("order");
        QueryWhere queryWhere = queryBuilder.newQueryWhere();
        queryWhere.append("time", WhereEnum.Gte, MyClock.addDayOfTime(-300));
        queryBuilder.where(queryWhere);
        queryBuilder.group("order");
        AtomicLong atomicLong = new AtomicLong();
        mongoDataHelper.query(queryBuilder, row -> {
            atomicLong.addAndGet(row.getInteger("rmb"));
        });
        markTimer.print(atomicLong.toString());
    }

}
