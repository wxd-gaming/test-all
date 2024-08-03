package wxdgaming.boot.spring.starter;

import org.springframework.context.annotation.ComponentScan;

/**
 * 注解
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-03 20:25
 **/
@ComponentScan()
public class BootScan {

    public BootScan() {
        System.out.println("\n" + this.getClass());
    }

}
