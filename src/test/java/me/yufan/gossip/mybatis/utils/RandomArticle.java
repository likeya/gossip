package me.yufan.gossip.mybatis.utils;

import me.yufan.gossip.mybatis.entity.Article;

public class RandomArticle extends RandomEntityGenerator {

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
}
