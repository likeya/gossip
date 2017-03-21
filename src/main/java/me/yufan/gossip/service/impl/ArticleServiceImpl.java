package me.yufan.gossip.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import me.yufan.gossip.mybatis.entity.Article;
import me.yufan.gossip.mybatis.mapper.ArticleMapper;
import me.yufan.gossip.service.ArticleService;

import java.util.List;

@Singleton
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    @Inject
    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }


    @Override
    public Article getArticleByUniqueKey(String uniqueKey) {
        return articleMapper.queryByKey(uniqueKey);
    }

    @Override
    public Article getOrRegisterArticle(Article article) {
        Article existedArticle = articleMapper.queryByKey(article.getUniqueKey());
        if (existedArticle == null) {
            log.debug("The article {} is not existed, register it.", article.getUniqueKey());

            articleMapper.insert(article);
        }
        return article;
    }

    @Override
    public void deleteArticle(Long articleId) {

    }

    @Override
    public void modifyArticle(Long articleId, Article article) {

    }

    @Override
    public List<Article> listArticle(Integer start, Integer end) {
        return null;
    }
}
