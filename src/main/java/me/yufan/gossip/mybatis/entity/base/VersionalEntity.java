package me.yufan.gossip.mybatis.entity.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public abstract class VersionalEntity extends GeneralEntity {
    private static final long serialVersionUID = 5231584472205367120L;

    private Date lastUpdateTime;
}
