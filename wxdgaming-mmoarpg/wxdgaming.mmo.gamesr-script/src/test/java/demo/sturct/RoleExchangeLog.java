package demo.sturct;


import lombok.Getter;
import lombok.Setter;

/** 角色充值表 Created by shell on 2015/11/3. */
@Getter
@Setter
public class RoleExchangeLog extends RoleLog {

    /** 订单号 */
    private String order;
    /** 充值类型 */
    private int exchangeType;
    /** 人民币数量 */
    private int rmb;
    /** 元宝数量 */
    private int gold;
    /** 当前玩家的等级 */
    private int roleLevel;
    /** 购买商品ID */
    private int productID;
    /** 购买商品名称 */
    private String productName;
    /** 玩家最后登录时间 */
    private long lastLoginTime;
    private byte subPlatform;

}
