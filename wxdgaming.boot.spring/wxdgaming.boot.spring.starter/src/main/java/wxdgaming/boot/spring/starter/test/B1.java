package wxdgaming.boot.spring.starter.test;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class B1 implements AutoCloseable {
    private int b = 1;

    public B1() {
        System.out.println("\n" + this.getClass());
    }

    @Override public void close() throws Exception {
    }
}
