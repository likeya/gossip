package me.yufan.gossip.mybatis.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.yufan.gossip.mybatis.entity.base.GeneralEntity;
import me.yufan.gossip.mybatis.entity.enums.AuthorType;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class Author extends GeneralEntity {
    private static final long serialVersionUID = 98996706127837178L;

    @NotNull
    private String name;

    @NotNull
    private String email;

    private String homepage;

    private AuthorType authorType;
}
