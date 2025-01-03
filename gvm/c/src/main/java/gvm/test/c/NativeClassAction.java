package gvm.test.c;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.File;
import java.io.IOException;
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
public class NativeClassAction {

    public static void main(String[] args) throws IOException {

        ArrayList<String> classNames = new ArrayList<>();

        ReflectContext.Builder
                .of(Thread.currentThread().getContextClassLoader(), "gvm.test").build()
                .classStream()
                .forEach(c -> {
                    System.out.println("ReflectContext：" + c);
                    classNames.add(c.getName());
                });

        String jsonString = JSON.toJSONString(classNames, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.PrettyFormat);
        System.out.println(jsonString);

        String first = "c/src/main/resources/resources.json";
        new File(first).getParentFile().mkdirs();
        Files.write(Paths.get(first),
                jsonString.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );

    }

}
