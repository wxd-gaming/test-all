package wxdgaming.boot.spring.starter.service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import wxdgaming.boot.LuaBus;

/**
 * lua
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-07-28 12:15
 **/

@Slf4j
@Getter
@Configuration
public class LuaService {

    LuaBus luaBus;

    @PostConstruct
    public void init() {
        luaBus = LuaBus.buildFromDirs("wxdgaming.boot.springstarter/src/lua");
    }

}
