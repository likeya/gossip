package me.yufan.gossip.mybatis.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import me.yufan.gossip.mybatis.entity.base.IdEntity;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Article extends IdEntity {
    private static final long serialVersionUID = 6986779319370876492L;

    @NotNull
    private String name;

    @NotNull
    private String uniqueKey;

    @NotNull
    private String url;
}
