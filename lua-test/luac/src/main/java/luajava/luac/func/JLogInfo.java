package luajava.luac.func;

import lombok.extern.slf4j.Slf4j;
import luajava.luac.LuaFunction;
import party.iroiro.luajava.Lua;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * java log info
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-12-26 16:00
 **/
@Slf4j
public class JLogInfo extends LuaFunction {

    @Override public Object doAction(Lua L, Object[] args) {
        log.info("{}", Arrays.stream(args).map(String::valueOf).collect(Collectors.joining(" ")));
        return null;
    }

}
