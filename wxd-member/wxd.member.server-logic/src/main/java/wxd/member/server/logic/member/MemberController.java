package wxd.member.server.logic.member;

import lombok.extern.slf4j.Slf4j;
import wxdgaming.boot.agent.io.FileReadUtil;
import wxdgaming.boot.net.controller.ann.TextController;
import wxdgaming.boot.net.controller.ann.TextMapping;

/**
 * 会员管理
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-05-22 19:16
 **/
@Slf4j
@TextController
public class MemberController {

    @TextMapping
    public String index() {
        return FileReadUtil.readString("html/member.html");
    }

}
