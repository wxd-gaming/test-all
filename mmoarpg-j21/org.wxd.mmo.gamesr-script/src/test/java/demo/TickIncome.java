package demo;

import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.core.lang.RandomUtils;

/**
 * 收益计算
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-30 10:43
 **/
@Slf4j
public class TickIncome {

    private volatile int gatherSecond = 0;
    private volatile int buffProSecond = 0;

    public static void main(String[] args) {
        TickIncome tickIncome = new TickIncome();
        for (int i = 0; i < 1000; i++) {
            /*模拟1000秒钟的收益*/
            tickIncome.heart();
        }
        float pro0 = 3;
        long s = (long) (tickIncome.gatherSecond * pro0 + tickIncome.buffProSecond * (pro0 + 0.2f/*相当于buff翻倍*/));
        log.info(
                "累计收益 {} 秒, buff加成：{} 秒 , 收益金币：{}",
                (tickIncome.gatherSecond + tickIncome.buffProSecond),
                tickIncome.buffProSecond,
                s
        );
    }

    public void heart() {
        if (checkBuffPro()) {buffProSecond++;/*有buff算buff*/} else {gatherSecond++;}
    }

    public boolean checkBuffPro() {
        /*30%概率有buff*/
        return RandomUtils.randomBoolean(3000);
    }

}
