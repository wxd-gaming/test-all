package agent.assist;

import java.lang.instrument.Instrumentation;

public class AssistMain {

    public static void main(String[] args) {

        MyClass myClass = new MyClass();
        myClass.doSomething();
    }
}
