package me.yufan.gossip.mybatis.mapper;

import lombok.extern.slf4j.Slf4j;
import me.yufan.gossip.mybatis.entity.Article;
import me.yufan.gossip.mybatis.mapper.base.MyBatisTestHelper;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class ArticleMapperTest extends MyBatisTestHelper {

    private ArticleMapper mapper;

    @Before
    public void setUp() {
        mapper = injector.getInstance(ArticleMapper.class);
    }

    private Article randomArticle() {
        Article article = new Article();
        article.setId(randomId());
        article.setName(randomString(128));
        article.setUniqueKey(randomString(1024));
        article.setUrl(randomString(512));
        return article;
    }

    @Test
    public void insert_a_article() {
        Article article = randomArticle();
        log.debug("insert a article {} into db", article);
        mapper.insert(article);
    }
}
