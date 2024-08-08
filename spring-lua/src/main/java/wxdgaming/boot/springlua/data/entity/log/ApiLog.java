package wxdgaming.boot.springlua.data.entity.log;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 操作日志
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-03 10:35
 **/
@Data
@Accessors(chain = true)
@Entity
@Table
public class ApiLog {
    @Id
    private long uid;

    private String url;
    private String path;
    private String method;
    private String contentType;
    private String ip;
    private String params;

}
