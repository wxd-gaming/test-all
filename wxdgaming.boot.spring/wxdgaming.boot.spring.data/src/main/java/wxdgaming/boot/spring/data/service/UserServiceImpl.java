package wxdgaming.boot.spring.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wxdgaming.boot.spring.data.repository.UserRepository;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-02 19:51
 **/
@Component
public class UserServiceImpl {
    @Autowired private UserRepository userRepository;
}
