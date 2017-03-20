package me.yufan.gossip.mybatis.entity.base;

import lombok.Data;

import java.io.Serializable;

/**
 * Common dao entity properties, add optimistic lock on mybatis
 */
@Data
public abstract class IdEntity implements Serializable {
    private static final long serialVersionUID = 5610225466434157947L;

    private Long id;

    // 0 identify normal status, if it's deleted this field would be the deleted time miles
    // Logic delete is good for mysql, because it doesn't have the real physical delete
    private Long deleted;
}
