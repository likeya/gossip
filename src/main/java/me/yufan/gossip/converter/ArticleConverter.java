package me.yufan.gossip.converter;

import com.google.common.base.Converter;
import me.yufan.gossip.mybatis.entity.Article;
import me.yufan.gossip.rest.dto.ArticleDTO;

public class ArticleConverter extends Converter<ArticleDTO, Article> {

    @Override
    protected Article doForward(ArticleDTO articleDTO) {
        return new Article()
            .setName(articleDTO.getName())
            .setUniqueKey(articleDTO.getKey())
            .setUrl(articleDTO.getUrl());
    }

    @Override
    protected ArticleDTO doBackward(Article article) {
        return new ArticleDTO()
            .setName(article.getName())
            .setUrl(article.getUrl())
            .setKey(article.getUniqueKey());
    }
}
