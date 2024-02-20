package org.wxd.mmo.script.gamesr.mail;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.wxd.mmo.bean.data.UidSeed;
import org.wxd.mmo.gamesr.bean.bag.goods.Item;
import org.wxd.mmo.gamesr.bean.mail.MailPackage;
import org.wxd.mmo.gamesr.bean.mail.impl.Mail;
import org.wxd.mmo.gamesr.cache.mail.MailPackageCache;
import org.wxd.mmo.gamesr.data.DataCenter;

import java.util.ArrayList;


/**
 * 邮件模块
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-03 20:20
 **/
@Singleton
public class MailModule {

    @Inject DataCenter dataCenter;
    @Inject MailPackageCache mailPackageCache;

    public Mail addSysMail(long toPlayer,
                           String title, String content,
                           String logs) {
        return addSysMail(mailPackageCache.cache(toPlayer), title, content, logs);
    }

    public Mail addSysMail(MailPackage mailPackage,
                           String title, String content,
                           String logs) {
        return addSysMail(mailPackage, title, content, null, null, logs);
    }

    public Mail addSysMail(long toPlayer,
                           String title, String content, ArrayList<String> contentParams,
                           ArrayList<Item> items, String logs) {
        return addSysMail(mailPackageCache.cache(toPlayer), title, content, contentParams, items, logs);
    }

    public Mail addSysMail(MailPackage mailPackage,
                           String title, String content, ArrayList<String> contentParams,
                           ArrayList<Item> items, String logs) {
        Mail mail = new Mail(1, "系统", title, content, contentParams, items, logs);
        return addMail(mailPackage, mail);
    }

    public Mail addMail(MailPackage mailPackage,
                        long senderId, String sender,
                        String title, String content,
                        String logs) {
        Mail mail = new Mail(senderId, sender, title, content, null, null, logs);
        return addMail(mailPackage, mail);
    }

    public Mail addMail(MailPackage mailPackage,
                        long senderId, String sender,
                        String title, String content, ArrayList<String> contentParams,
                        ArrayList<Item> items, String logs) {
        Mail mail = new Mail(senderId, sender, title, content, contentParams, items, logs);
        return addMail(mailPackage, mail);
    }

    public Mail addMail(MailPackage mailPackage, Mail mail) {
        long l = dataCenter.newUid(UidSeed.Type.Mail);
        mail.setUid(l);
        mailPackage.getMails().put(mail.getUid(), mail);
        return mail;
    }

}
