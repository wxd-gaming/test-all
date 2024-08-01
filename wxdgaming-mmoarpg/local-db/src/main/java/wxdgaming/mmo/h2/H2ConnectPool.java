package wxdgaming.mmo.h2;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.mmo.BaseConnectionPool;

import java.io.File;

/**
 * h2数据库模块
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-07-16 11:18
 */
@Slf4j
@Getter
public class H2ConnectPool extends BaseConnectionPool {

    /*

     #第一种，内存模式运行 ;IGNORECASE=TRUE;auto_server=true;database_to_upper=false 是忽略大小写
jdbc:h2:mem:testDB;IGNORECASE=TRUE;auto_server=true;database_to_upper=false

#第二种，使用本地文件方式 auto_server=true;database_to_upper=false 是忽略大小写
jdbc:h2:file:./target/testDB;IGNORECASE=TRUE;auto_server=true;database_to_upper=false

     */
    @Getter
    public enum DbType {
        FILE("file"),
        MEM("mem");
        private final String ext;

        DbType(String ext) {
            this.ext = ext;
        }
    }

    final String DRIVER_CLASS = "org.h2.Driver";
    final DbType dbType;

    public H2ConnectPool(DbType dbType, String dataBaseName) {
        super(dataBaseName);
        this.dbType = dbType;
        init();
    }

    public H2ConnectPool(DbType dbType, String USER, String PASSWORD, String dataBaseName) {
        super(USER, PASSWORD, dataBaseName);
        this.dbType = dbType;
        init();
    }

    protected void init() {
        this.db_url = String.format("jdbc:h2:%s:%s;IGNORECASE=TRUE;auto_server=true;database_to_upper=false", this.dbType.getExt(), dataBaseName);
        if (dbType == DbType.FILE) {
            String[] split = this.db_url.split(":");
            new File(split[split.length - 1]).getParentFile().mkdirs();
        }
        try {
            Class.forName(DRIVER_CLASS);
        } catch (Throwable e) {
            log.error(DRIVER_CLASS, e);
        }
        log.info("{} 启动 h2 host={}", this.getClass(), dataBaseName);
    }

}
