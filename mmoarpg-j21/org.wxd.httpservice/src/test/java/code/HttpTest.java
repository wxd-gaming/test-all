package code;

import org.junit.Test;
import org.wxd.boot.collection.ObjMap;
import org.wxd.boot.httpclient.url.HttpBuilder;

public class HttpTest {

    @Test
    public void t1() {
        String s = HttpBuilder.postText("http://127.0.0.1:19000/test/ok").paramJson(new ObjMap().append("ddd", "dd\ndd").toJson()).request().bodyUnicodeDecodeString();
        System.out.println(ObjMap.parse(s).getString("ddd"));

        HttpBuilder.postText("http://127.0.0.1:19000/test/ok").paramText("ddd=dd\ndd").request().systemOut();
    }
}
