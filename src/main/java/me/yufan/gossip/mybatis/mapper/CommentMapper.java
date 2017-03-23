package me.yufan.gossip.mybatis.mapper;

import me.yufan.gossip.mybatis.entity.Comment;
import me.yufan.gossip.mybatis.mapper.base.BaseMapper;
import me.yufan.gossip.rest.dto.CommentDTO;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentDTO> getCommentsByArticleId(Long articleId);
}
