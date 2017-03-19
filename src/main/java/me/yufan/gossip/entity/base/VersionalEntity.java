package me.yufan.gossip.entity.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class VersionalEntity extends GeneralEntity {
    private static final long serialVersionUID = 5231584472205367120L;

    private Date lastUpdateTime;
}
