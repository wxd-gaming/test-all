package demo.sturct;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.wxd.boot.lang.ObjectBase;
import org.wxd.boot.timer.MyClock;

/** 运营日志 */
@Getter
@Setter
@Accessors(chain = true)
public abstract class AbstractLog extends ObjectBase {

    private long time;

    public AbstractLog() {
        this.time = MyClock.millis();
    }

}
