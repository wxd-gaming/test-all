package agent.bytebuddy;

public class ByteBuddyExample {

    public static void main(String[] args) {
        new Target().sayHello();
        System.out.println(System.currentTimeMillis());
    }
}
