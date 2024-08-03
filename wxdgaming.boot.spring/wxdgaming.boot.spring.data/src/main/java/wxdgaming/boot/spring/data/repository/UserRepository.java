package wxdgaming.boot.spring.data.repository;

import org.springframework.stereotype.Repository;
import wxdgaming.boot.spring.data.BaseRepository;
import wxdgaming.boot.spring.data.entity.user.User;

/**
 * s
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-02 19:28
 **/
@Repository
public interface UserRepository extends BaseRepository<User, String> {

}
