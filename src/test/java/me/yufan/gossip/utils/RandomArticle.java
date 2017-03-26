package me.yufan.gossip.utils;

import me.yufan.gossip.mybatis.entity.Article;
import me.yufan.gossip.rest.dto.ArticleDTO;

public class RandomArticle extends RandomEntityGenerator {

    private RandomArticle() {
        // No constructor
    }

    public static Article randomArticle() {
        Article article = randomRawArticle();
        article.setId(randomId());
        return article;
    }

    public static Article randomRawArticle() {
        return new Article()
            .setName(randomString(128))
            .setUniqueKey(randomString(1024))
            .setUrl(randomString(512));
    }

    public static ArticleDTO randomArticleDTO() {
        return new ArticleDTO()
            .setKey(randomString(20))
            .setName(randomString(10))
            .setUrl(randomUrl());
    }
}
