package org.wxd.mmo.gamesr.bean.mail.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.wxd.mmo.GameBase;
import org.wxd.mmo.gamesr.bean.bag.goods.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-03 20:12
 **/
@Getter
@Setter
@Accessors(chain = true)
public class Mail extends GameBase {

    private String sender;
    private String title;
    private String content;
    private List<String> contentParams = new ArrayList<>();
    private boolean read = false;
    private boolean reward = false;
    private List<Item> items = new ArrayList<>();
    private String logs;

}
