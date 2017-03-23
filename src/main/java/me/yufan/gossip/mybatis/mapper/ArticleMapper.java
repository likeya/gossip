package me.yufan.gossip.mybatis.mapper;

import me.yufan.gossip.mybatis.entity.Article;
import me.yufan.gossip.mybatis.mapper.base.BaseMapper;

public interface ArticleMapper extends BaseMapper<Article> {

    Article queryByKey(String articleKey);
}
