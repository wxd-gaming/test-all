package action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import gvm.test.c.ReflectContext;
import gvm.test.c.RemoteClassLoader;
import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 * 资源读取
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-07-10 09:37
 **/
public class NativeClassActionTest {

    /**
     * 通过mvn编译调用
     * mvn clean compile test -Dtest=action.NativeClassActionTest#f1 -DfailIfNoTests=false package -f pom.xml
     *
     * @throws Exception
     * @author: Troy.Chen(無心道, 15388152619)
     * @version: 2024-07-12 09:56
     */
    @Test
    public void f1() throws Exception {

        ArrayList<String> classNames = new ArrayList<>();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        classLoader = RemoteClassLoader.build(
                classLoader,
                "http://localhost/qj5/a.jar",
                "http://localhost/qj5/b.jar"
        );

        ReflectContext.Builder
                .of(classLoader, "gvm.test").build()
                .classStream()
                .forEach(c -> {
                    System.out.println("ReflectContext：" + c);
                    classNames.add(c.getName());
                });

        String jsonString = JSON.toJSONString(classNames, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.PrettyFormat);
        System.out.println(jsonString);

        String first = "src/main/resources/resources.json";
        new File(first).getParentFile().mkdirs();
        Files.write(Paths.get(first),
                jsonString.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );

    }

}
