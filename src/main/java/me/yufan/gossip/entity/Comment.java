package me.yufan.gossip.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.yufan.gossip.entity.base.VersionalEntity;

@Data
@EqualsAndHashCode(callSuper = true)
public class Comment extends VersionalEntity {
    private static final long serialVersionUID = -1543025605634867939L;

    private Long authorId;

    private Long articleId;

    private String message;

    private Long parentId;
}
