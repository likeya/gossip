package me.yufan.gossip.mybatis.mapper;

import lombok.extern.slf4j.Slf4j;
import me.yufan.gossip.mybatis.entity.Article;
import me.yufan.gossip.mybatis.mapper.base.MyBatisTestHelper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static me.yufan.gossip.mybatis.utils.RandomArticle.randomArticle;
import static me.yufan.gossip.mybatis.utils.RandomEntityGenerator.randomString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Basic CRUD test for article
 */
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArticleMapperTest extends MyBatisTestHelper {

    private ArticleMapper mapper;

    @Before
    public void setUp() {
        mapper = injector.getInstance(ArticleMapper.class);
    }

    @Test
    public void query_a_article() throws Exception {
        Article article = mapper.queryOne(888888L);
        assertThat(article, notNullValue());
        assertThat(article.getName(), is("Test Article 1"));
    }

    @Test
    public void insert_a_article() {
        Article article = randomArticle();
        log.debug("insert a article {} into db", article);
        final boolean inserted = mapper.insert(article);
        assertThat(inserted, is(true));
        assertThat(article.getId(), notNullValue());
    }

    @Test
    public void insert_a_article_list() {
        List<Article> articles = IntStream.range(0, 5).boxed().map(i -> randomArticle()).collect(toList());
        mapper.batchInsert(articles);
        long failedCount = articles.stream().filter(t -> t.getId() == null).count();
        assertThat(failedCount, is(0L));
    }

    @Test
    public void update_a_article() {
        Article existedArticle = mapper.queryOne(999999L);
        assertThat(existedArticle, notNullValue());

        String newArticleName = randomString(20);
        existedArticle.setName(newArticleName);
        boolean update = mapper.update(existedArticle);
        assertThat(update, is(true));

        Article updatedArticle = mapper.queryOne(999999L);
        assertThat(updatedArticle, notNullValue());
        assertThat(updatedArticle.getName(), is(newArticleName));
    }

    @Test
    public void delete_one() throws Exception {
        Article article = randomArticle();
        final boolean inserted = mapper.insert(article);
        assertThat(inserted, is(true));

        final Long articleId = article.getId();
        assertThat(articleId, notNullValue());

        final long deleteTime = System.currentTimeMillis();
        mapper.delete(articleId, deleteTime);

        final Article deletedArticle = mapper.queryOne(articleId);
        assertThat(deletedArticle, notNullValue());
        assertThat(deletedArticle.getDeleted(), is(deleteTime));
    }

    @Test
    public void query_a_existed_article_by_its_unique_key() throws Exception {
        String uniqueKey = "a-unique-key";
        final Article article = mapper.queryByKey(uniqueKey);

        assertThat(article, notNullValue());
        assertThat(article.getUniqueKey(), is(uniqueKey));
        assertThat(article.getName(), is("Test Article 3"));
    }
}
