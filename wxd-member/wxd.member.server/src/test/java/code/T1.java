package code;

import org.junit.Test;
import wxdgaming.boot.agent.io.FileUtil;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-05-16 10:31
 **/
public class T1 {

    @Test
    public void t0() {
        System.out.println(FileUtil.getCanonicalPath(new File("../../out")));
        System.out.println(FileUtil.getCanonicalPath(new File("/out")));
    }

    @Test
    public void t2() {
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        System.out.println(atomicBoolean.compareAndSet(true, false) + " - " + atomicBoolean.get());
        System.out.println(atomicBoolean.compareAndSet(false, true) + " - " + atomicBoolean.get());
        System.out.println(atomicBoolean.compareAndSet(true, false) + " - " + atomicBoolean.get());
        System.out.println(atomicBoolean.compareAndSet(true, false) + " - " + atomicBoolean.get());
    }

    @Test
    public void t3() {
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        System.out.println(atomicBoolean.getAndSet(false) + " - " + atomicBoolean.get());
        System.out.println(atomicBoolean.getAndSet(true) + " - " + atomicBoolean.get());
        System.out.println(atomicBoolean.getAndSet(false) + " - " + atomicBoolean.get());
        System.out.println(atomicBoolean.getAndSet(true) + " - " + atomicBoolean.get());
    }

    @Test
    public void t4() throws InterruptedException {
        AtomicReference<BankCard> bankCardRef = new AtomicReference<>(new BankCard("cxuan", 100));
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    // 使用 AtomicReference.get 获取
                    final BankCard card = bankCardRef.get();
                    BankCard newCard = new BankCard(card.getAccountName(), card.getMoney() + 100);
                    // 使用 CAS 乐观锁进行非阻塞更新
                    if (bankCardRef.compareAndSet(card, newCard)) {
                        System.out.println(newCard);
                        break;
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
    }

    public static class BankCard {

        private final String accountName;
        private final int money;

        // 构造函数初始化 accountName 和 money
        public BankCard(String accountName, int money) {
            this.accountName = accountName;
            this.money = money;
        }

        // 不提供任何修改个人账户的 set 方法，只提供 get 方法
        public String getAccountName() {
            return accountName;
        }

        public int getMoney() {
            return money;
        }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BankCard bankCard = (BankCard) o;
            return Objects.equals(accountName, bankCard.accountName);
        }

        @Override public int hashCode() {
            return Objects.hash(accountName);
        }

        // 重写 toString() 方法， 方便打印 BankCard
        @Override
        public String toString() {
            return "BankCard{" +
                    "accountName='" + accountName + '\'' +
                    ", money='" + money + '\'' +
                    '}';
        }
    }

}
