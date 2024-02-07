package code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class HashmapTest {


    @Test
    public void t1() {

        ConcurrentHashMap<String, HashMap<String, String>> tmp = new ConcurrentHashMap<>();
        tmp.computeIfAbsent("key1", l -> new HashMap<>());

        A a1 = new A(1, System.nanoTime());
        A a2 = new A(1, System.nanoTime());

        log.info("{}", a1 == a2);
        log.info("{}", a1.equals(a2));

        List<A> list = new ArrayList<>();

        list.add(a1);
        log.info("{}", list);
        list.remove(a2);
        log.info("{}", list);
        list.add(a2);
        log.info("{}", list);
        list.remove(a2);
        log.info("{}", list);
        list.add(a1);
        list.add(a2);
        list.remove(a2);
        log.info("{}", list);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class A {

        private long key;
        private long time;

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            A a = (A) o;

            return key == a.key;
        }

        @Override public int hashCode() {
            return (int) (key ^ (key >>> 32));
        }

        @Override public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append(this.getClass().getSimpleName()).append("{");
            sb.append("key=").append(key);
            sb.append(", time=").append(time);
            sb.append('}');
            return sb.toString();
        }
    }

}
