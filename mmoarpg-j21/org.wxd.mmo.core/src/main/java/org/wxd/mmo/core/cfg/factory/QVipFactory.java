package org.wxd.mmo.core.cfg.factory;


import lombok.Getter;
import org.wxd.boot.batis.struct.DbBean;
import org.wxd.mmo.core.cfg.bean.QVipBean;

import java.io.Serializable;


/**
 * excel 构建 Vip.xlsx - q_vip - vip等级
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-03-02 17:06:48
 **/
@Getter
public class QVipFactory extends DbBean<QVipBean> implements Serializable {

    @Override public void initDb() {
        /*todo 实现一些数据分组*/

    }

}