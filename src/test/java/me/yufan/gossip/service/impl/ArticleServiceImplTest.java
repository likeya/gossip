package me.yufan.gossip.service.impl;

import me.yufan.gossip.mybatis.entity.Article;
import me.yufan.gossip.mybatis.mapper.ArticleMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static me.yufan.gossip.utils.RandomArticle.randomArticle;
import static me.yufan.gossip.utils.RandomArticle.randomRawArticle;
import static me.yufan.gossip.utils.RandomEntityGenerator.randomId;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArticleServiceImplTest {

    private ArticleMapper articleMapper = mock(ArticleMapper.class);

    private ArticleServiceImpl articleService = new ArticleServiceImpl(articleMapper, articleConverter);

    private void noArticleFromUniqueKey(String uniqueKey) {
        when(articleMapper.queryByKey(uniqueKey)).thenReturn(null);
    }

    private void oneArticleFromUniqueKey(Article article) {
        when(articleMapper.queryByKey(article.getUniqueKey())).thenReturn(article);
    }

    private void registerArticle(Article article, Long articleId) {
        when(articleMapper.insert(article)).then(invocation -> {
            if (articleId != null) {
                article.setId(articleId);
            }
            return true;
        });
    }

    @Test
    public void query_article_by_its_unique_key() throws Exception {
        Article article = randomArticle();
        String uniqueKey = article.getUniqueKey();
        oneArticleFromUniqueKey(article);

        final Article queryArticle = articleService.getArticleByUniqueKey(uniqueKey);
        verify(articleMapper, only()).queryByKey(uniqueKey);
        assertThat(article, is(queryArticle));
    }

    @Test
    public void register_a_new_article_when_no_article_existed_from_unique_key() {
        Article article = randomRawArticle();
        Long newId = randomId();

        noArticleFromUniqueKey(article.getUniqueKey());
        registerArticle(article, newId);

        assertThat(article.getId(), nullValue());
        Article newArticle = articleService.getOrRegisterArticle(article);
        assertThat(article.getId(), is(newId));
        assertThat(article, is(newArticle));
    }

    @Test
    public void do_noting_when_the_article_to_register_existed() throws Exception {
        Article article = randomArticle();

        oneArticleFromUniqueKey(article);
        final Article registeredArticle = articleService.getOrRegisterArticle(article);

        verify(articleMapper, only()).queryByKey(article.getUniqueKey());
        verify(articleMapper, never()).insert(article);
        assertThat(registeredArticle, is(article));
    }
}
