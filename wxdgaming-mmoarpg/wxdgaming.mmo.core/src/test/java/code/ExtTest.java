package code;

import com.alibaba.fastjson.JSONObject;
import wxdgaming.boot.core.str.json.FastJsonUtil;

import java.util.PriorityQueue;

/**
 * 扩展测试
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-02-27 19:56
 **/
public class ExtTest {

    public static void main(String[] args) {
        ExtTest extTest = new ExtTest();
        extTest.setI(1);
        String json = FastJsonUtil.toJson(extTest);
        System.out.println(json);
        System.out.println(FastJsonUtil.parse(json));
    }

    private transient final JSONObject jsonObject = new JSONObject();

    private String s;
    private int i;

    public String getS() {
        return jsonObject.getString("s");
    }

    public ExtTest setS(String s) {
        jsonObject.put("s", s);
        return this;
    }

    public int getI() {
        return jsonObject.getIntValue("i");
    }

    public ExtTest setI(int i) {
        jsonObject.put("i", i);
        return this;
    }

}
