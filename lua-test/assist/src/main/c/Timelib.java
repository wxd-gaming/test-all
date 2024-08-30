import java.time.Clock;

public class Timelib {

    static {
        // java.lang.System.out.println(java.lang.System.getProperty("java.library.path"));
        // java.lang.System.loadLibrary("TimeLib");
    }

    public static long currentTimeMillis() {
        return Clock.systemUTC().millis();
    }

    public native static void register();

    public static void main(String[] args) throws Exception {
        // System.out.println(System.currentTimeMillis());
        // System.out.println(Clock.systemUTC().millis());
        // ClassLoader classLoader = Timelib.class.getClassLoader();
        // classLoader.loadClass("java.lang.System");
        // System.out.println("原始时间：" + System.currentTimeMillis());
        // long currentTimeMillis = currentTimeMillis();
        // java.lang.System.out.println("当前时间: " + currentTimeMillis);
        // System.out.println("原始时间：" + System.currentTimeMillis());
    }

}
