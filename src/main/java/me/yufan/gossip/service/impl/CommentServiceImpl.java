package me.yufan.gossip.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.yufan.gossip.converter.entity.CommentConverter;
import me.yufan.gossip.exception.database.AlreadyExistedException;
import me.yufan.gossip.mybatis.entity.Comment;
import me.yufan.gossip.mybatis.mapper.CommentMapper;
import me.yufan.gossip.rest.dto.ArticleDTO;
import me.yufan.gossip.rest.dto.CommentDTO;
import me.yufan.gossip.service.CommentService;
import org.mybatis.guice.transactional.Transactional;

import java.util.Date;
import java.util.List;

import static java.util.Collections.emptyList;
import static me.yufan.gossip.mybatis.entity.enums.CommentStatus.APPROVED;

@Singleton
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    private final CommentConverter commentConverter;

    @Inject
    public CommentServiceImpl(CommentMapper commentMapper, CommentConverter commentConverter) {
        this.commentMapper = commentMapper;
        this.commentConverter = commentConverter;
    }

    @Override
    public List<CommentDTO> getCommentsByArticle(ArticleDTO article) {
        if (article == null || article.getId() == null) {
            return emptyList();
        }
        return commentMapper.getCommentsByArticleId(article.getId());
    }

    @Override
    public void addComment(CommentDTO comment, Long articleId, Long authorId) {
        Comment commentToSave = commentConverter.convert(comment);
        commentToSave.setAuthorId(authorId);
        commentToSave.setArticleId(articleId);

        if (commentMapper.query(commentToSave).isEmpty()) {
            commentToSave.setStatus(APPROVED).setLastUpdateTime(new Date()).setCreateTime(new Date());
            commentMapper.insert(commentToSave);
        } else {
            throw new AlreadyExistedException("Duplicated comment, you have made same comment.");
        }
    }
}
