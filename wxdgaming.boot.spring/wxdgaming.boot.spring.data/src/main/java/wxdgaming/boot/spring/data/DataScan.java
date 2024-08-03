package wxdgaming.boot.spring.data;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * 注解
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-03 20:25
 **/
@EntityScan
@ComponentScan()
public class DataScan {

    public DataScan() {
        System.out.println("\n" + this.getClass());
    }

}
