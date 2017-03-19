package me.yufan.gossip.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.yufan.gossip.entity.base.GeneralEntity;

@Data
@EqualsAndHashCode(callSuper = true)
public class Article extends GeneralEntity {
    private static final long serialVersionUID = 6986779319370876492L;

    private String name;

    private String uniqueKey;

    private String url;
}
