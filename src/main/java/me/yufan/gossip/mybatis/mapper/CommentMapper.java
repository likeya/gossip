package me.yufan.gossip.mybatis.mapper;

import me.yufan.gossip.mybatis.entity.Comment;
import me.yufan.gossip.mybatis.mapper.base.BaseMapper;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {

    List<Comment> getCommentsByArticleId(Long articleId);
}
