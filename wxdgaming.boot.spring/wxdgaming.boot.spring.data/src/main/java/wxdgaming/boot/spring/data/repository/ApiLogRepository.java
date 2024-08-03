package wxdgaming.boot.spring.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wxdgaming.boot.spring.data.entity.log.ApiLog;

/**
 * 日志记录
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-03 10:36
 **/
@Repository
public interface ApiLogRepository extends JpaRepository<ApiLog, Long> {

}
