package me.yufan.gossip.service;

import me.yufan.gossip.rest.dto.ArticleDTO;

import java.util.List;

public interface ArticleService {

    ArticleDTO getOrRegisterArticle(ArticleDTO article);

    ArticleDTO getArticleByKey(String uniqueKey);

    void deleteArticle(Long articleId);

    void modifyArticle(Long articleId, ArticleDTO article);

    List<ArticleDTO> listArticle(Integer start, Integer end);
}
