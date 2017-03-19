package me.yufan.gossip.entity.base;

import lombok.Data;

import java.io.Serializable;

/**
 * Common dao entity properties, add optimistic lock on mybatis
 */
@Data
public abstract class GeneralEntity implements Serializable {
    private static final long serialVersionUID = 5610225466434157947L;

    // TODO add optimistic lock support
    private String version;

    private Long id;
}
