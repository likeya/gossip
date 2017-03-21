package me.yufan.gossip.service;

import me.yufan.gossip.mybatis.entity.Article;

import java.util.List;

public interface ArticleService {

    Article getArticleByUniqueKey(String uniqueKey);

    Article getOrRegisterArticle(Article article);

    void deleteArticle(Long articleId);

    void modifyArticle(Long articleId, Article article);

    List<Article> listArticle(Integer start, Integer end);
}
