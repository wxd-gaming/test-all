package code;

import com.alibaba.fastjson.annotation.JSONCreator;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.junit.Test;
import org.wxd.boot.core.lang.ObjectBase;
import org.wxd.boot.core.str.json.FastJsonUtil;
import org.wxd.mmo.core.bean.bag.ItemCfg;

/**
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-07 20:56
 **/
public class NewTest {

    @Test
    public void t1() {
        {
            ItemCfg.ItemCfgBuilder o = ItemCfg.builder().setNum(1).setCfgId(1);
            o.setNum(o.build().getNum() + 1);
            ItemCfg build = o.build();
            String json = build.toJson();
            System.out.println(json);
            ItemCfg parse = FastJsonUtil.parse(json, ItemCfg.class);
            System.out.println(parse);
        }
        {
            A str = new B().setStr("s1").setStr2("s2");
            String json1 = str.toJson();
            System.out.println(json1);
            A parsed = FastJsonUtil.parse(json1, A.class);
            System.out.println(parsed);
        }
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    public static class A extends ObjectBase {

        private long a1;
        private long a2;
        private String str;
        private String str2;

        @JSONCreator
        public A() {
        }
    }

    public static class B extends A {

    }

}
