package me.yufan.gossip.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import me.yufan.gossip.converter.entity.ArticleConverter;
import me.yufan.gossip.exception.database.NotPresentException;
import me.yufan.gossip.mybatis.entity.Article;
import me.yufan.gossip.mybatis.mapper.ArticleMapper;
import me.yufan.gossip.rest.dto.ArticleDTO;
import me.yufan.gossip.service.ArticleService;
import org.mybatis.guice.transactional.Transactional;

import java.util.List;

@Singleton
@Slf4j
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    private final ArticleConverter articleConverter;

    @Inject
    public ArticleServiceImpl(ArticleMapper articleMapper, ArticleConverter articleConverter) {
        this.articleMapper = articleMapper;
        this.articleConverter = articleConverter;
    }

    @Override
    public ArticleDTO getOrRegisterArticle(@NonNull final ArticleDTO article) {
        Article existedArticle = articleMapper.queryByKey(article.getKey());
        if (existedArticle == null) {
            log.debug("The article {} is not existed, register it.", article.getKey());

            existedArticle = articleConverter.convert(article);
            articleMapper.insert(existedArticle);
        }
        return articleConverter.reverse().convert(existedArticle);
    }

    @Override
    public ArticleDTO getArticleByKey(String uniqueKey) {
        Article existedArticle = articleMapper.queryByKey(uniqueKey);
        if (existedArticle == null) {
            throw new NotPresentException("The article doesn't exist");
        }
        return articleConverter.reverse().convert(existedArticle);
    }

    @Override
    public void deleteArticle(Long articleId) {

    }

    @Override
    public void modifyArticle(Long articleId, ArticleDTO article) {

    }

    @Override
    public List<ArticleDTO> listArticle(Integer start, Integer end) {
        return null;
    }
}
