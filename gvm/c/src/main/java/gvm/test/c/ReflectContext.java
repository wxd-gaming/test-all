package gvm.test.c;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 资源处理
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-10-16 10:11
 **/
@Slf4j
@Getter
public class ReflectContext {

    /** 判定 接口, 枚举, 注解, 抽象类 返回 false */
    public static boolean checked(Class<?> aClass) {
        /* 判定 是否可用 */
        return !(
                Object.class.equals(aClass)
                        || aClass.isInterface()
                        || aClass.isEnum()
                        || aClass.isAnnotation()
                        || Modifier.isAbstract(aClass.getModifiers())
        );
    }

    /** 获取类型实现的接口 */
    public static Stream<Class<?>> getInterfaces(Class<?> cls) {
        TreeMap<String, Class<?>> classCollection = new TreeMap<>();
        getInterfaces(classCollection, cls);
        return classCollection.values().stream();
    }

    /** 获取类实现的接口 */
    public static void getInterfaces(TreeMap<String, Class<?>> classCollection, Class<?> cls) {
        if (cls == null || Object.class.equals(cls)) {
            return;
        }
        /*查找父类*/
        getInterfaces(classCollection, cls.getSuperclass());
        Class<?>[] interfaces = cls.getInterfaces();
        for (Class<?> aInterface : interfaces) {
            /*查找接口，实现的接口*/
            getInterfaces(classCollection, aInterface);
            /*查找父类*/
            getInterfaces(classCollection, aInterface.getSuperclass());
            if (aInterface.isInterface()) {
                classCollection.put(aInterface.getName(), aInterface);
            }
        }
    }

    /** 获取泛型的第一个 */
    public static Class<?> getTClass(Class<?> source) {
        return getTClass(source, 0);
    }

    /** 获取泛型的类型 */
    public static Class<?> getTClass(Class<?> source, int index) {
        Type genType = source.getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<?>) params[index];
    }

    /** 所有的类 */
    private final List<Class<?>> classList;

    public ReflectContext(Collection<Class<?>> classList) {
        this.classList = new ArrayList<>(classList);
    }

    /** 所有的类 */
    public Stream<Class<?>> classStream() {
        return classList.stream();
    }

    /** 父类或者接口 */
    public <U> Stream<Class<U>> classWithSuper(Class<U> cls) {
        return classWithSuper(cls, null);
    }

    /** 父类或者接口 */
    public <U> Stream<Class<U>> classWithSuper(Class<U> cls, Predicate<Class<U>> predicate) {
        @SuppressWarnings("unchecked")
        Stream<Class<U>> tmp = classStream().filter(cls::isAssignableFrom).map(c -> (Class<U>) c);
        if (predicate != null) tmp = tmp.filter(predicate);
        return tmp;
    }

    /** 所有添加了这个注解的类 */
    public Stream<Class<?>> classWithAnnotated(Class<? extends Annotation> annotation) {
        return classWithAnnotated(annotation, null);
    }

    /** 所有添加了这个注解的类 */
    public Stream<Class<?>> classWithAnnotated(Class<? extends Annotation> annotation, Predicate<Class<?>> predicate) {
        Stream<Class<?>> tmp = classStream().filter(c -> c.getAnnotation(annotation) != null);
        if (predicate != null) tmp = tmp.filter(predicate);
        return tmp;
    }

    public Stream<Content<?>> stream() {
        return classList.stream().map(Content::new);
    }

    /** 父类或者接口 */
    public <U> Stream<Content<U>> withSuper(Class<U> cls) {
        return withSuper(cls, null);
    }

    /** 父类或者接口 */
    public <U> Stream<Content<U>> withSuper(Class<U> cls, Predicate<Class<U>> predicate) {
        return classWithSuper(cls, predicate).map(Content::new);
    }

    /** 所有添加了这个注解的类 */
    public Stream<Content<?>> withAnnotated(Class<? extends Annotation> annotation) {
        return withAnnotated(annotation, null);
    }

    /** 所有添加了这个注解的类 */
    public Stream<Content<?>> withAnnotated(Class<? extends Annotation> annotation, Predicate<Class<?>> predicate) {
        return classWithAnnotated(annotation, predicate).map(Content::new);
    }

    @Getter
    public static class Content<T> {

        private final Class<T> cls;

        public static <U> Content<U> of(Class<U> cls) {
            return new Content<>(cls);
        }

        Content(Class<T> cls) {
            this.cls = cls;
        }

        /** 是否添加了注解 */
        public boolean withAnnotated(Class<? extends Annotation> annotation) {
            return cls.getAnnotation(annotation) != null;
        }

        /** 所有的方法 */
        public Collection<Method> getMethods() {
            // return MethodUtil.readAllMethod(cls).values();
            return null;
        }

        /** 所有的方法 */
        public Stream<Method> methodStream() {
            return getMethods().stream();
        }

        /** 所有添加了这个注解的方法 */
        public Stream<Method> methodsWithAnnotated(Class<? extends Annotation> annotation) {
            return methodStream().filter(m -> m.getAnnotation(annotation) != null);
        }

        /** 所有的字段 */
        public Collection<Field> getFields() {
            // return FieldUtil.getFields(false, cls).values();
            return null;
        }

        /** 所有的字段 */
        public Stream<Field> fieldStream() {
            return getFields().stream();
        }

        /** 所有添加了这个注解的字段 */
        public Stream<Field> fieldWithAnnotated(Class<? extends Annotation> annotation) {
            return fieldStream().filter(f -> f.getAnnotation(annotation) != null);
        }

    }

    @Setter
    @Accessors(chain = true)
    public static class Builder {

        public static Builder of(String... packageNames) {
            return of(Thread.currentThread().getContextClassLoader(), packageNames);
        }

        public static Builder of(ClassLoader classLoader, String... packageNames) {
            return new Builder(classLoader, packageNames);
        }

        private final ClassLoader classLoader;
        private final String[] packageNames;
        /** 是否读取子包 */
        private boolean findChild = true;
        /** 查找类的时候忽略接口 */
        private boolean filterInterface = true;
        /** 过滤掉抽象类 */
        private boolean filterAbstract = true;
        /** 过滤掉枚举类 */
        private boolean filterEnum = true;
        private ArrayList<String> resources = null;

        public ArrayList<String> getResources() {
            if (resources == null) {
                InputStream resourceAsStream = classLoader.getResourceAsStream("resources.json");
                if (resourceAsStream != null) {
                    byte[] bytes = readBytes(resourceAsStream);
                    String string = new String(bytes, StandardCharsets.UTF_8);
                    resources = JSON.parseObject(string, new TypeReference<ArrayList<String>>() {});
                }
            }
            if (resources == null) {
                resources = new ArrayList<>();
            }
            return resources;
        }

        private Builder(ClassLoader classLoader, String[] packageNames) {
            this.classLoader = classLoader;
            this.packageNames = packageNames;
        }

        /** 所有的类 */
        public ReflectContext

        build() {
            TreeMap<String, Class<?>> classCollection = new TreeMap<>();
            for (String packageName : packageNames) {
                findClasses(packageName, aClass -> classCollection.put(aClass.getName(), aClass));
            }
            List<Class<?>> list = classCollection.values()
                    .stream()
                    .filter(v -> !Object.class.equals(v))
                    .filter(v -> !filterInterface || !v.isInterface())
                    .filter(v -> !filterAbstract || !Modifier.isAbstract(v.getModifiers()))
                    .filter(v -> !filterEnum || !v.isEnum())
                    .filter(v -> !v.isAnnotation())
                    .collect(Collectors.toList());
            return new ReflectContext(list);
        }

        private void findClasses(String packageName, Consumer<Class<?>> consumer) {
            String packagePath = packageName;
            if (packageName.endsWith(".jar") || packageName.endsWith(".war")) {
                packagePath = packageName;
            } else if (!".".equals(packageName)) {
                packagePath = packageName.replace(".", "/");
            }
            try {
                if (classLoader instanceof RemoteClassLoader) {
                    RemoteClassLoader remoteClassLoader = (RemoteClassLoader) classLoader;
                    remoteClassLoader.classStream().forEach(consumer);
                }
                Enumeration<URL> resources = classLoader.getResources(packagePath);
                if (resources != null) {
                    URL url = null;
                    while (resources.hasMoreElements()) {
                        url = resources.nextElement();
                        if (url != null) {
                            String type = url.getProtocol();
                            String urlPath = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8.toString());
                            // System.out.println("url info：" + type + " - " + urlPath);
                            switch (type) {
                                case "file":
                                    String dir = urlPath.substring(0, urlPath.lastIndexOf(packagePath));
                                    findClassByFile(dir, urlPath, consumer);
                                    break;
                                case "jar":
                                case "zip":
                                    findClassByJar(urlPath, consumer);
                                    break;
                                case "resource":
                                    getResources()
                                            .stream()
                                            .filter(v -> v.startsWith(packageName))
                                            .forEach(v -> {
                                                loadClass(v, consumer);
                                            });
                                    break;
                                default:
                                    System.out.println("未知类型：" + type + " - " + urlPath);
                                    break;
                            }
                        } else {
                            findClassByJars(
                                    ((URLClassLoader) classLoader).getURLs(),
                                    packagePath,
                                    consumer
                            );
                        }
                    }
                }
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * 从项目文件获取某包下所有类
         *
         * @param dir      父级文件夹
         * @param filePath 文件路径
         */
        private void findClassByFile(String dir, String filePath, Consumer<Class<?>> consumer) {
            File file = new File(filePath);
            File[] childFiles = file.listFiles();
            if (childFiles != null) {
                for (File childFile : childFiles) {
                    if (childFile.isDirectory()) {
                        if (findChild) {
                            findClassByFile(dir, childFile.getPath(), consumer);
                        }
                    } else {
                        String childFilePath = childFile.getPath();
                        if (childFilePath.endsWith(".class")) {
                            childFilePath = childFilePath.substring(dir.length() - 1, childFilePath.lastIndexOf("."));
                            childFilePath = childFilePath.replace("\\", ".");

                            loadClass(childFilePath, consumer);

                        }
                    }
                }
            }
        }

        /**
         * 从所有jar中搜索该包，并获取该包下所有类
         *
         * @param urls        URL集合
         * @param packagePath 包路径
         */
        private void findClassByJars(URL[] urls, String packagePath, Consumer<Class<?>> consumer) {

            if (urls != null) {
                for (URL url : urls) {
                    String urlPath = null;
                    try {
                        urlPath = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8.toString());
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                    // 不必搜索classes文件夹
                    if (urlPath.endsWith("classes/")) {
                        continue;
                    }
                    String jarPath = urlPath + "!/" + packagePath;
                    findClassByJar(jarPath, consumer);
                }
            }
        }

        /**
         * 从jar获取某包下所有类
         *
         * @param jarPath jar文件路径
         */
        private void findClassByJar(String jarPath, Consumer<Class<?>> consumer) {
            if (jarPath.startsWith("http://") || jarPath.startsWith("https://")) return;
            String[] jarInfo = jarPath.split("!");
            String jarFilePath = jarInfo[0].substring(jarInfo[0].indexOf("/"));
            String packagePath = jarInfo[1].substring(1);
            String entryName;
            try (JarFile jarFile = new JarFile(jarFilePath)) {
                Enumeration<JarEntry> entrys = jarFile.entries();
                while (entrys.hasMoreElements()) {
                    JarEntry jarEntry = entrys.nextElement();
                    entryName = jarEntry.getName();
                    if (entryName.endsWith(".class")) {
                        if (findChild) {
                            if (!entryName.startsWith(packagePath)) {
                                continue;
                            }
                        } else {
                            int index = entryName.lastIndexOf("/");
                            String myPackagePath;
                            if (index != -1) {
                                myPackagePath = entryName.substring(0, index);
                            } else {
                                myPackagePath = entryName;
                            }
                            if (!myPackagePath.equals(packagePath)) {
                                continue;
                            }
                        }
                        entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
                        loadClass(entryName, consumer);
                    }
                }
            } catch (Throwable e) {
                throw new RuntimeException(jarPath, e);
            }
        }

        private void loadClass(String childFilePath, Consumer<Class<?>> consumer) {
            try {
                Class<?> clazz = classLoader.loadClass(childFilePath);
                consumer.accept(clazz);
            } catch (Throwable e) {
                log.error(childFilePath, e);
            }
        }

    }

    public static byte[] readBytes(InputStream inputStream) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            readBytes(outputStream, inputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void readBytes(OutputStream outputStream, InputStream inputStream) {
        byte[] bytes = new byte[200];
        try {
            int read = 0;
            while ((read = inputStream.read(bytes, 0, bytes.length)) >= 0) {
                outputStream.write(bytes, 0, read);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
