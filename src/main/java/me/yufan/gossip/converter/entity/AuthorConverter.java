package me.yufan.gossip.converter.entity;

import com.google.common.base.Converter;
import me.yufan.gossip.mybatis.entity.Author;
import me.yufan.gossip.mybatis.entity.enums.AuthorType;
import me.yufan.gossip.rest.dto.AuthorDTO;

public class AuthorConverter extends Converter<AuthorDTO, Author> {

    @Override
    protected Author doForward(AuthorDTO authorDTO) {
        Author author = new Author()
            .setAuthorType(AuthorType.convert(authorDTO.getAuthorType().toString()))
            .setEmail(authorDTO.getEmail())
            .setHomepage(authorDTO.getHomepage())
            .setName(authorDTO.getName());
        author.setId(authorDTO.getId());

        return author;
    }

    @Override
    protected AuthorDTO doBackward(Author author) {
        return new AuthorDTO()
            .setAuthorType(author.getAuthorType().getCode())
            .setId(author.getId())
            .setEmail(author.getEmail())
            .setHomepage(author.getHomepage())
            .setName(author.getName());
    }
}
