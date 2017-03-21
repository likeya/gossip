package me.yufan.gossip.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.yufan.gossip.mybatis.entity.Article;
import me.yufan.gossip.mybatis.mapper.ArticleMapper;
import me.yufan.gossip.service.ArticleService;

import java.util.List;

@Singleton
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    @Inject
    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }


    @Override
    public void registerArticle(Article article) {

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
