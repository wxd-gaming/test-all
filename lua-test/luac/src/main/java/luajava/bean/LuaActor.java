package luajava.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * 测试
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-12-09 10:15
 **/
@Getter
@Setter
public class LuaActor {

    private int lv = 1;
    private Long uid;
    private String name;

    public LuaActor() {
    }

    public LuaActor(Long uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public Long getUid() {
        System.out.println("cccccccccccccccccccccccccccccccccccccc");
        return uid;
    }

    public String getName() {
        System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
        return name;
    }

    @Override public String toString() {
        return uid.toString();
    }
}
