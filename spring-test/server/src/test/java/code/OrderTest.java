package code;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-10 14:51
 **/
public class OrderTest {

    AtomicInteger 库存 = new AtomicInteger(10);

    public boolean 秒杀(String 用户) {
        int _库存;
        synchronized (this) {
            /*这个位置可能是分布式的，可能是redis或者其它来源，只是判定库存*/
            if (库存.get() > 0) {
                _库存 = 库存.decrementAndGet();
            } else {
                System.out.println("用户：" + 用户 + " 秒杀失败");
                return false;
            }
        }
        System.out.println("用户：" + 用户 + " 秒杀成功 剩余库存：" + _库存);
        /*
        todo db.insert(用户,订单)
        send msg to user
        */
        return true;
    }

    public void 超时归还(String 用户) {
        synchronized (this) {
            int _库存 = 库存.incrementAndGet();
            System.out.println("用户：" + 用户 + " 超时未付款规划库存 剩余库存：" + _库存);
        }
    }

    public static void main(String[] args) {
        OrderTest order = new OrderTest();
        for (int i = 0; i < 100; i++) {
            final String 用户 = "用户" + i;
            new Thread(() -> {
                try {
                    /*todo 模拟延迟*/
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1, 100));
                } catch (InterruptedException ignore) {}
                boolean 秒杀 = order.秒杀(用户);
                if (秒杀) {
                    int nextInt = ThreadLocalRandom.current().nextInt(1, 100);
                    if (nextInt >= 50) {
                        /*todo 模拟付款超时*/
                        order.超时归还(用户);
                    }
                }
            }).start();
        }
    }

}
