package org.wxd.mmo.gamesr.bean.bag;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.wxd.boot.batis.save.ObjectSave;
import org.wxd.boot.lang.LNum;
import org.wxd.mmo.gamesr.bean.bag.goods.Item;

import java.util.Collection;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

/**
 * 道具容器
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-07-31 17:30
 **/
@Getter
@Setter
@Accessors(chain = true)
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

    public Collection<Item> items(int cfgId) {
        return items.values().stream().filter(v -> v.getCfgId() == cfgId).collect(Collectors.toList());
    }

    public long itemNum(int cfgId) {
        return items.values().stream().filter(v -> v.getCfgId() == cfgId).mapToLong(LNum::getNum).sum();
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
