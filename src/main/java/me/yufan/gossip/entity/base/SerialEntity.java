package me.yufan.gossip.entity.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class SerialEntity extends GeneralEntity {
    private static final long serialVersionUID = 7896864693045705673L;

    private Date createTime;
}
