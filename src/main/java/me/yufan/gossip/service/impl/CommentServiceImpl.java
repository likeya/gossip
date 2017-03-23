package me.yufan.gossip.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.yufan.gossip.converter.CommentConverter;
import me.yufan.gossip.mybatis.mapper.CommentMapper;
import me.yufan.gossip.rest.dto.ArticleDTO;
import me.yufan.gossip.rest.dto.CommentDTO;
import me.yufan.gossip.service.CommentService;

import java.util.List;

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
    public List<CommentDTO> getCommentsByArticleId(ArticleDTO article) {
        // TODO convert comment entity to comment dto
        return null;
    }
}
