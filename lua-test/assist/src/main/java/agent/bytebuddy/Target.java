package agent.bytebuddy;

import java.time.Clock;

public class Target {

    public void sayHello() {
        System.out.println("Hello, ByteBuddy!");
    }

    public static long millis() {
        return Clock.systemDefaultZone().millis() + 9999999L;
    }

}
