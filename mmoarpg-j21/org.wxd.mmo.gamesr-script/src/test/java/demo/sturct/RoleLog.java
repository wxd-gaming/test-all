package demo.sturct;

import lombok.Getter;
import lombok.Setter;

/** 角色相关日志 Created by shell on 2015/10/19. */
@Getter
@Setter
public abstract class RoleLog extends AbstractLog {
    /** 序列号 */
    private long serialNumber;
    /** 平台 */
    private byte platform;
    /** 服务器 */
    private int server;
    /** 帐号 */
    private String account;
    /** 帐号id */
    private long accountID;
    /** 帐号创建时间 */
    private long accountCreateTime;
    /** 角色名 */
    private String role;
    /** 角色id */
    private long roleId;
    /** 角色创建时间 */
    private long roleCreateTime;
    /** 渠道 */
    private String channel;
    /** 当前充值钻石数量 */
    private long curRecharge;
    /** 角色标记 0普通玩家 1内服号 */
    private int roleMark;

}
