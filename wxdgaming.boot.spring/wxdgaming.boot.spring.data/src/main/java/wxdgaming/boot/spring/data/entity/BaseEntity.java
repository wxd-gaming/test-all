package wxdgaming.boot.spring.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 实体基类
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-03 20:19
 **/
@Table
@Entity
@Getter
@Setter
@Accessors(chain = true)
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private long uid;

}
