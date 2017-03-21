package me.yufan.gossip.mybatis.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.yufan.gossip.mybatis.entity.base.VersionalEntity;
import me.yufan.gossip.mybatis.entity.enums.CommentStatus;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static org.hibernate.validator.constraints.SafeHtml.WhiteListType.SIMPLE_TEXT;

@Data
@EqualsAndHashCode(callSuper = true)
// TODO add UA records
public class Comment extends VersionalEntity {
    private static final long serialVersionUID = -1543025605634867939L;

    @NotNull
    @Min(1)
    private Long authorId;

    @NotNull
    @Min(1)
    private Long articleId;

    @NotNull
    @SafeHtml(whitelistType = SIMPLE_TEXT)
    private String message;

    private Long parentId;

    private CommentStatus status;
}
