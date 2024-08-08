package wxdgaming.boot.springlua.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 传递给lua脚本使用的slf4j日志
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-07 20:07
 **/
@Slf4j
@Service
public class LuaLogger {

    public void debug(String msg) {
        log.debug(msg);
    }

    public void info(String msg) {
        log.info(msg);
    }

    public void warn(String msg) {
        log.warn(msg);
    }

    public void error(String msg) {
        log.error(msg);
    }

}
