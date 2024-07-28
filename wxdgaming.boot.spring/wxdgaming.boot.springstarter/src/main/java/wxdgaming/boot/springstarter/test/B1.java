package wxdgaming.boot.springstarter.test;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class B1 implements AutoCloseable {
    private int b = 1;

    public B1() {
        System.out.println("\n\n" + this.getClass() + "\n\n");
    }

    @Override public void close() throws Exception {
        System.out.println("\n\nclose() " + this.getClass() + "\n\n");
    }
}
