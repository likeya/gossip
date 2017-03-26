package me.yufan.gossip.converter.dto;

import com.google.common.base.Converter;
import me.yufan.gossip.mybatis.entity.Author;
import me.yufan.gossip.rest.dto.CommentDTO;

public class CommentAuthorConverter extends Converter<CommentDTO, Author> {

    @Override
    protected Author doForward(CommentDTO comment) {
        return new Author()
            .setName(comment.getName())
            .setHomepage(comment.getWebsite())
            .setEmail(comment.getEmail());
    }

    @Override
    protected CommentDTO doBackward(Author author) {
        throw new UnsupportedOperationException("This conversion is not implemented yet");
    }
}
