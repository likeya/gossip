package me.yufan.gossip.mybatis.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import me.yufan.gossip.mybatis.entity.base.VersionalEntity;
import me.yufan.gossip.mybatis.entity.enums.CommentStatus;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
// TODO add UA records
public class Comment extends VersionalEntity {
    private static final long serialVersionUID = -1543025605634867939L;

    private Long authorId;

    private Long articleId;

    private String message;

    private Long parentId;

    private CommentStatus status;
}
