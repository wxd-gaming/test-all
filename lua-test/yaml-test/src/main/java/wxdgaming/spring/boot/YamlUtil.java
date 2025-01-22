package wxdgaming.spring.boot;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * yaml文件读取
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-04-23 17:23
 **/
public class YamlUtil {

    public static BufferedReader reader(ClassLoader classLoader, String fileName) throws Exception {
        Path path = Paths.get(fileName);
        InputStream inputStream = null;
        if (Files.exists(path)) {
            inputStream = Files.newInputStream(path);
        } else {
            inputStream = classLoader.getResourceAsStream(fileName);
        }
        if (inputStream == null) return null;
        InputStreamReader in = new InputStreamReader(inputStream);
        return new BufferedReader(in);
    }

    public static <T> T loadYaml(String path, Class<T> clazz) {
        return loadYaml(Thread.currentThread().getContextClassLoader(), path, clazz);
    }

    public static <T> T loadYaml(ClassLoader classLoader, String path, Class<T> clazz) {
        try {
            return loadYaml(reader(classLoader, path), clazz);
        } catch (Exception e) {
            throw new RuntimeException("读取文件：" + path, e);
        }
    }

    public static <T> T loadYaml(Reader reader, Class<T> clazz) {
        DumperOptions options = new DumperOptions();
        Representer representer = new Representer(options);
        representer.getPropertyUtils().setSkipMissingProperties(true);
        Yaml yaml = new Yaml(representer, options);
        return yaml.loadAs(reader, clazz);
    }

}
