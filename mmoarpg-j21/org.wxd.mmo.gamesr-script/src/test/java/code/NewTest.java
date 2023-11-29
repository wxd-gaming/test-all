package code;

import org.junit.Test;

/**
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-07 20:56
 **/
public class NewTest {

    @Test
    public void t1() {
        ItemCfg.ItemCfgBuilder o = ItemCfg.builder().setNum(1).setCfgId(1);
        o.setNum(o.build().getNum() + 1);
        System.out.println(o);

    }

}
