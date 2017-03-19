package me.yufan.gossip.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.yufan.gossip.entity.base.SerialEntity;

@Data
@EqualsAndHashCode(callSuper = true)
public class Author extends SerialEntity {
    private static final long serialVersionUID = 98996706127837178L;

    private String name;

    private String email;

    private String homepage;
}
