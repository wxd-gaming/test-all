package test;

import org.wxd.boot.core.str.StringUtil;

/**
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-29 16:22
 **/
public class Player {

    /** 后续不再使用 */
    @Deprecated
    private String oldField;
    private long newField;

    public void convert() {
        /*todo 数据结构发生变化后处理 */
        if (StringUtil.notEmptyOrNull(oldField)) {
            newField = Long.parseLong(oldField);
            oldField = "";
        }
    }

}
