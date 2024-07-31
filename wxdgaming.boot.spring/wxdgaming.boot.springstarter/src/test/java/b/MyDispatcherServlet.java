package b;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-07-31 08:36
 **/
@Component
public class MyDispatcherServlet extends DispatcherServlet {

    @Autowired
    public MyDispatcherServlet(WebApplicationContext webApplicationContext) {
        super(webApplicationContext);
        System.out.println("MyDispatcherServlet");
    }

    @Override public void onRefresh(ApplicationContext context) {
        super.onRefresh(context);
    }
}
