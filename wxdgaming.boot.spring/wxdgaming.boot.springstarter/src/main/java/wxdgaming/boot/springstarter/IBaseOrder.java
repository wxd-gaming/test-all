package wxdgaming.boot.springstarter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-07-30 09:27
 **/
public interface IBaseOrder extends ApplicationContextAware {

    @Override
    default void setApplicationContext(ApplicationContext applicationContext) throws BeansException {}

}
