package code;

import org.junit.Test;
import wxdgaming.boot.batis.code.CodeLan;
import wxdgaming.boot.batis.excel.ExcelRead2Json;
import wxdgaming.mmo.core.cfg.CfgCache;
import wxdgaming.mmo.core.cfg.table.QVipTable;

/**
 * excel 转化 json 文件
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-02-29 17:11
 **/
public class Excel2Json {

    @Test
    public void t1() throws Exception {
        ExcelRead2Json.builder()
                .dataIndex(2, 3, 4, 1, 5)
                .excelPaths(false, "../cfg")
                .loadExcel("server")
                .saveData("../cfg-json")
//                .showData()
                .createCode(CodeLan.Java, "src/main/java", "wxdgaming.mmo.core.cfg")
                .loadExcel("client")
                .saveData("../cfg-client/json")
                .createCode(CodeLan.CSharp, "../cfg-client/code", "wxdgaming.mmo.Core.Cfg")
        ;
        CfgCache cfgCache = new CfgCache("../cfg-json");
        cfgCache.beanInit(null);
        /*展示一下配置表*/
        QVipTable dbBean = cfgCache.getDbBean(QVipTable.class);
        System.out.println(dbBean.toDataString());
        System.out.println(dbBean.get(1));
        System.out.println(dbBean.get("1#100"));
    }

}
