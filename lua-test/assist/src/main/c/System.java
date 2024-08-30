public class System {

    static {
        java.lang.System.out.println(java.lang.System.getProperty("java.library.path"));
        java.lang.System.loadLibrary("System");
    }

    public native static long currentTimeMillis();

    public native static void setOffset(long offset);

    public static void main(String[] args) {
        long currentTimeMillis = currentTimeMillis();
        java.lang.System.out.println("Current time (milliseconds): " + currentTimeMillis);
    }

}
