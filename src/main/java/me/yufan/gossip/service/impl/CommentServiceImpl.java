package me.yufan.gossip.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.yufan.gossip.converter.CommentConverter;
import me.yufan.gossip.mybatis.mapper.CommentMapper;
import me.yufan.gossip.rest.dto.ArticleDTO;
import me.yufan.gossip.rest.dto.CommentDTO;
import me.yufan.gossip.service.CommentService;

import java.util.List;

import static java.util.Collections.emptyList;

@Singleton
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
}
