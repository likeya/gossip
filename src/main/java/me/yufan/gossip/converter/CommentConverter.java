package me.yufan.gossip.converter;

import com.google.common.base.Converter;
import me.yufan.gossip.mybatis.entity.Comment;
import me.yufan.gossip.rest.dto.CommentDTO;

public class CommentConverter extends Converter<CommentDTO, Comment> {

    @Override
    protected Comment doForward(CommentDTO commentDTO) {
        return new Comment()
            .setMessage(commentDTO.getMessage())
            .setParentId(commentDTO.getReplyPostId());
    }

    @Override
    protected CommentDTO doBackward(Comment comment) {
        return new CommentDTO()
            .setMessage(comment.getMessage())
            .setReplyPostId(comment.getParentId());
    }
}
