package me.yufan.gossip.service;

import me.yufan.gossip.rest.dto.ArticleDTO;
import me.yufan.gossip.rest.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    List<CommentDTO> getCommentsByArticle(ArticleDTO article);

    void addComment(CommentDTO comment, Long articleId, Long authorId);
}
