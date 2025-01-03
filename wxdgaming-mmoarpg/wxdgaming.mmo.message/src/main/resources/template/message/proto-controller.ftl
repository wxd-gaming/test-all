package ${savePack};

<#list imports as row>
import ${row};
</#list>
import wxdgaming.mmo.gamesr.bean.user.Player;

/**
 * ${comment} file=${filePath}
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: ${times}
 */
@Slf4j
@ProtoController(${serviceClass})
public final class ${saveClassName} implements IController {

    /** ${comment} ${messageName} */
    @ProtoMapping(remarks = "${comment}")
    public void exec(SocketSession session, ${messageName} req) throws Exception {
        Player player = session.attr("player");
        ${res}
    }
}
