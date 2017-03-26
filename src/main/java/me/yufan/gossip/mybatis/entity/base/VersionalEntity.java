package me.yufan.gossip.mybatis.entity.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class VersionalEntity extends GeneralEntity {
    private static final long serialVersionUID = 5231584472205367120L;

    private Date lastUpdateTime = new Date();

    // TODO add optimistic lock support in the future
    private String version = UUID.randomUUID().toString();
}
