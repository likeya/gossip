package me.yufan.gossip.service.impl;

import me.yufan.gossip.converter.entity.ArticleConverter;
import me.yufan.gossip.exception.database.NotPresentException;
import me.yufan.gossip.mybatis.entity.Article;
import me.yufan.gossip.mybatis.mapper.ArticleMapper;
import me.yufan.gossip.rest.dto.ArticleDTO;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static me.yufan.gossip.utils.RandomArticle.randomArticle;
import static me.yufan.gossip.utils.RandomArticle.randomRawArticle;
import static me.yufan.gossip.utils.RandomEntityGenerator.randomId;
import static me.yufan.gossip.utils.RandomEntityGenerator.randomString;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArticleServiceImplTest {

    private ArticleMapper articleMapper = mock(ArticleMapper.class);

    private ArticleConverter articleConverter = new ArticleConverter();

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
    public void register_a_new_article_when_no_article_existed_from_unique_key() {
        Article article = randomRawArticle();
        Long newId = randomId();

        noArticleFromUniqueKey(article.getUniqueKey());
        registerArticle(article, newId);
        ArticleDTO convert = articleConverter.reverse().convert(article);

        assertThat(article.getId(), nullValue());
        ArticleDTO newArticle = articleService.getOrRegisterArticle(convert);
        assertThat(article.getId(), is(newId));
        assertThat(convert, is(newArticle));
    }

    @Test
    public void do_noting_when_the_article_to_register_existed() {
        Article article = randomArticle();

        oneArticleFromUniqueKey(article);
        final ArticleDTO convert = articleConverter.reverse().convert(article);
        ArticleDTO registeredArticle = articleService.getOrRegisterArticle(convert);

        verify(articleMapper, only()).queryByKey(article.getUniqueKey());
        verify(articleMapper, never()).insert(article);
        assertThat(registeredArticle, is(convert));
    }

    @Test
    public void when_no_article_present_by_the_query_key() {
        final String uniqueKey = randomString(20);
        noArticleFromUniqueKey(uniqueKey);

        try {
            articleService.getArticleByKey(uniqueKey);
            fail("should throw NotPresentException");
        } catch (NotPresentException e) {
            assertThat(e.getMessage(), containsString("The article doesn't exist"));
        }
    }
}
