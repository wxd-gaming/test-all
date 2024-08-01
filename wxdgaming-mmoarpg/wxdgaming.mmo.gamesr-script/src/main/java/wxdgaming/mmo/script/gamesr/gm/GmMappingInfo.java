package wxdgaming.mmo.script.gamesr.gm;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.lang.reflect.Method;

/**
 * gm 方法映射
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-02-09 19:56
 **/
@Getter
@Setter
@Accessors(chain = true)
public class GmMappingInfo {

    private Object instance;
    private Method method;
    private Gm gm;

}
