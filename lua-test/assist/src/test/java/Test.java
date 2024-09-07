import com.alibaba.jvm.sandbox.api.ProcessController;
import com.alibaba.jvm.sandbox.api.event.Event;
import com.alibaba.jvm.sandbox.api.filter.Filter;
import com.alibaba.jvm.sandbox.api.listener.EventListener;
import com.alibaba.jvm.sandbox.api.listener.ext.Advice;
import com.alibaba.jvm.sandbox.api.listener.ext.AdviceListener;
import com.alibaba.jvm.sandbox.api.listener.ext.EventWatchBuilder;
import com.alibaba.jvm.sandbox.api.listener.ext.EventWatchCondition;
import com.alibaba.jvm.sandbox.api.resource.ModuleEventWatcher;

import java.lang.invoke.MethodHandles;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-30 14:44
 **/
public class Test {

    public static class CustomModuleEventWatcher implements ModuleEventWatcher {

        @Override public void delete(int watcherId) {

        }

        @Override public int watch(Filter filter, EventListener listener, Progress progress, Event.Type... eventType) {
            return 0;
        }

        @Override public int watch(Filter filter, EventListener listener, Event.Type... eventType) {
            return 0;
        }

        @Override public int watch(EventWatchCondition condition, EventListener listener, Progress progress, Event.Type... eventType) {
            return 0;
        }

        @Override public void delete(int watcherId, Progress progress) {

        }

        @Override public void watching(Filter filter, EventListener listener, Progress wProgress, WatchCallback watchCb, Progress dProgress, Event.Type... eventType) throws Throwable {

        }

        @Override public void watching(Filter filter, EventListener listener, WatchCallback watchCb, Event.Type... eventType) throws Throwable {

        }
    }

    public static void main(String[] args) throws Exception {
        MethodHandles
        ModuleEventWatcher moduleEventWatcher = new CustomModuleEventWatcher();
        new EventWatchBuilder(moduleEventWatcher)
                .onClass("java.lang.System")
                .includeBootstrap()
                .onBehavior("currentTimeMillis")
                .onWatch(new AdviceListener() {
                    @Override
                    protected void before(Advice advice) throws Throwable {
                        ProcessController.returnImmediately(1697782952000L);
                    }
                });
        System.out.println(System.currentTimeMillis());
    }

}
