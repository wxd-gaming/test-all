package boot.b;

import org.springframework.stereotype.Component;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-03 21:25
 **/
@Component
public class B {

    public B() {
        System.out.println("\n" + this.getClass());
    }

}
