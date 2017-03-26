package me.yufan.gossip.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.yufan.gossip.converter.dto.CommentAuthorConverter;
import me.yufan.gossip.converter.entity.AuthorConverter;
import me.yufan.gossip.mybatis.entity.Author;
import me.yufan.gossip.mybatis.mapper.AuthorMapper;
import me.yufan.gossip.rest.dto.AuthorDTO;
import me.yufan.gossip.rest.dto.CommentDTO;
import me.yufan.gossip.service.AuthorService;
import org.mybatis.guice.transactional.Transactional;

import java.util.Date;
import java.util.Optional;

import static me.yufan.gossip.mybatis.entity.enums.AuthorType.GUEST;

@Singleton
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorMapper authorMapper;

    private final AuthorConverter authorConverter;

    private final CommentAuthorConverter commentAuthorConverter;

    @Inject
    public AuthorServiceImpl(AuthorMapper authorMapper, AuthorConverter authorConverter,
                             CommentAuthorConverter commentAuthorConverter) {
        this.authorMapper = authorMapper;
        this.authorConverter = authorConverter;
        this.commentAuthorConverter = commentAuthorConverter;
    }

    /**
     * The comment author type should be changed by gossip admin api.
     */
    @Override
    public AuthorDTO getOrRegisterAuthor(CommentDTO comment) {
        Author expectedAuthor = commentAuthorConverter.convert(comment);
        Optional<Author> author = authorMapper.query(expectedAuthor).stream().findFirst();

        if (author.isPresent()) {
            return authorConverter.reverse().convert(author.get());
        } else {
            expectedAuthor.setAuthorType(GUEST).setCreateTime(new Date());
            authorMapper.insert(expectedAuthor);
            return authorConverter.reverse().convert(expectedAuthor);
        }
    }
}
