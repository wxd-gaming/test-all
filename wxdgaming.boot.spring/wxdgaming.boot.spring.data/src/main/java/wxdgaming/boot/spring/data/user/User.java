package wxdgaming.boot.spring.data.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 用户
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-02 19:07
 **/
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table
public class User {

    @Id()
    @Column(length = 64)
    private String openId;
    private String account;

}

