package wxdgaming.mmo.leveldb;

import lombok.Getter;
import lombok.SneakyThrows;
import org.iq80.leveldb.*;
import org.iq80.leveldb.impl.Iq80DBFactory;
import wxdgaming.boot.core.str.json.FastJsonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * leveldb
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-07-18 19:53
 */
@Getter
public class LevelDbConnectPool {

    private final File db_file;
    private final Options options;
    private DB open;

    public LevelDbConnectPool(String db_file) {
        this(new File(db_file), new Options());
    }

    public LevelDbConnectPool(File db_file) {
        this(db_file, new Options());
    }

    public LevelDbConnectPool(File db_file, Options options) {
        this.db_file = db_file;
        this.options = options;
    }

    @SneakyThrows public void start() {
        open = Iq80DBFactory.factory.open(db_file, options);
    }

    @SneakyThrows public void close() {
        open.close();
    }

    public Collection<String> keys() {
        // 读取当前快照，重启服务仍能读取，说明快照持久化至磁盘，
        Snapshot snapshot = open.getSnapshot();
        // 读取操作
        ReadOptions readOptions = new ReadOptions();
        /*遍历中swap出来的数据，不应该保存在memtable中。*/
        readOptions.fillCache(false);
        /*默认snapshot为当前*/
        readOptions.snapshot(snapshot);
        /*从当前快照读取数据*/
        DBIterator iterator = open.iterator(readOptions);
        List<String> strings = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<byte[], byte[]> next = iterator.next();
            String string = Iq80DBFactory.asString(next.getKey());
            strings.add(string);
        }
        return strings;
    }

    public void putJson(String key, Object value) {
        put(key, FastJsonUtil.toBytes(value));
    }

    public void put(String key, String value) {
        put(key, Iq80DBFactory.bytes(value));
    }

    public void put(String key, byte[] value) {
        put(Iq80DBFactory.bytes(key), value);
    }

    public void put(byte[] key, byte[] value) {
        open.put(key, value);
    }

    public <T> T getJson(String key, Class<T> tClass) {
        byte[] bytes = getBytes(Iq80DBFactory.bytes(key));
        return FastJsonUtil.parse(bytes, tClass);
    }

    public String getString(String key) {
        byte[] bytes = getBytes(Iq80DBFactory.bytes(key));
        return Iq80DBFactory.asString(bytes);
    }

    public String getString(byte[] key) {
        byte[] bytes = getBytes(key);
        return Iq80DBFactory.asString(bytes);
    }

    public byte[] getBytes(String key) {
        byte[] bytes = getBytes(Iq80DBFactory.bytes(key));
        return bytes;
    }

    public byte[] getBytes(byte[] key) {
        return open.get(key);
    }

}
