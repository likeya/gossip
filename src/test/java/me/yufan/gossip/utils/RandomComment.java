package me.yufan.gossip.utils;

import me.yufan.gossip.mybatis.entity.Comment;
import me.yufan.gossip.rest.dto.CommentDTO;

public class RandomComment extends RandomEntityGenerator {

    public static CommentDTO randomCommentDTO() {
        return new CommentDTO()
            .setReplyPostId(randomId())
            .setMessage(randomString(300))
            .setArticleKey(randomString(20))
            .setEmail("example@example.com")
            .setName(randomString(40))
            .setWebsite("http://example.com");
    }

    public static Comment randomRawComment() {
        return new Comment()
            .setArticleId(randomId())
            .setParentId(randomId())
            .setAuthorId(randomId())
            .setMessage(randomString(400));
    }
}
