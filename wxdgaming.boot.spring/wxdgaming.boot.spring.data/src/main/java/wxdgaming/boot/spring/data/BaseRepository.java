package wxdgaming.boot.spring.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 仓储
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-03 20:13
 **/
@NoRepositoryBean
public interface BaseRepository<T, I> extends JpaRepository<T, I> {

}
