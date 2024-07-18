package wxdgaming.mmo;


import org.iq80.leveldb.WriteBatch;
import org.iq80.leveldb.impl.Iq80DBFactory;
import wxdgaming.mmo.leveldb.LevelDbConnectPool;

public class LevelDbExample {

    public static void main(String[] args) throws Exception {
        LevelDbConnectPool levelDbConnectPool = new LevelDbConnectPool("./target/leveldb/");
        levelDbConnectPool.start();
        long timeMillis = System.currentTimeMillis();
        WriteBatch writeBatch = levelDbConnectPool.getOpen().createWriteBatch();
        for (int i = 0; i < 2000_0000; i++) {
            writeBatch.put(Iq80DBFactory.bytes(String.valueOf(System.nanoTime())), Iq80DBFactory.bytes(String.valueOf(System.nanoTime())));
        }
        levelDbConnectPool.getOpen().write(writeBatch);
        long l = System.currentTimeMillis() - timeMillis;
        System.out.println(levelDbConnectPool.keys());
        System.out.println(l + " ms");
        timeMillis = System.currentTimeMillis();
        System.out.println(levelDbConnectPool.getString("211555920891700"));
        l = System.currentTimeMillis() - timeMillis;
        System.out.println(l + " ms");
        levelDbConnectPool.close();
    }

}
