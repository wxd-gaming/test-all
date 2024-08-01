package wxdgaming.mmo.core.bean.bag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.boot.batis.save.ObjectSave;
import wxdgaming.mmo.core.bean.bag.goods.Item;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 道具容器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-07-31 17:30
 **/
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
public class ItemPack extends ObjectSave {

    /** 容器类型 */
    private PackType packType;
    /** 初始化大小 */
    private int initSize;
    /** 购买背包大小 */
    private int buySize;
    /** 使用道具扩展 */
    private int itemSize;
    /** 货币 */
    private final ConcurrentSkipListMap<Integer, Long> moneys = new ConcurrentSkipListMap<>();
    /** 道具 */
    private final ConcurrentSkipListMap<Long, Item> items = new ConcurrentSkipListMap<>();

    public ItemPack(PackType packType, int initSize) {
        this.packType = packType;
        this.initSize = initSize;
    }

    public Optional<Item> item(long uid) {
        return Optional.ofNullable(items.get(uid));
    }

    public Stream<Item> stream(int cfgId) {
        return items.values().stream().filter(v -> v.getCfgId() == cfgId);
    }

    public Collection<Item> items(int cfgId) {
        return stream(cfgId).collect(Collectors.toList());
    }

    public int bagSize() {
        return initSize + buySize;
    }

    public int addBuySize(int size) {
        return buySize = buySize + size;
    }

    public int addItemSize(int size) {
        return itemSize = itemSize + size;
    }
}
