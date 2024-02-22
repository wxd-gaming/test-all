package org.wxd.mmo.gamesr.bean.mail;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.wxd.boot.batis.struct.DbTable;
import org.wxd.mmo.core.GameBase;
import org.wxd.mmo.gamesr.bean.mail.impl.Mail;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 邮件背包
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-03 20:11
 **/
@Getter
@Setter
@Accessors(chain = true)
@DbTable
public class MailPackage extends GameBase {

    private ConcurrentSkipListMap<Long, Mail> mails = new ConcurrentSkipListMap<>();

}
