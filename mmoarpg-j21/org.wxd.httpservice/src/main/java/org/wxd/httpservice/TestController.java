package org.wxd.httpservice;

import org.wxd.boot.collection.ObjMap;
import org.wxd.boot.net.controller.ann.TextController;
import org.wxd.boot.net.controller.ann.TextMapping;
import org.wxd.boot.net.web.hs.HttpSession;

/**
 * 测接口
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-15 11:13
 **/
@TextController
public class TestController {

    @TextMapping
    public String ok(HttpSession httpSession, ObjMap objMap) {
        return objMap.toJson();
    }

}
