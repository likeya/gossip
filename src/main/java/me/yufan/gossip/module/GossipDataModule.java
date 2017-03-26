package me.yufan.gossip.module;

import com.google.inject.Singleton;
import me.yufan.gossip.converter.ArticleConverter;
import me.yufan.gossip.converter.CommentConverter;
import me.yufan.gossip.mybatis.support.GossipDataSourceProvider;
import me.yufan.gossip.mybatis.support.JdbcHelper;
import me.yufan.gossip.service.ArticleService;
import me.yufan.gossip.service.AuthorService;
import me.yufan.gossip.service.CommentService;
import me.yufan.gossip.service.impl.ArticleServiceImpl;
import me.yufan.gossip.service.impl.AuthorServiceImpl;
import me.yufan.gossip.service.impl.CommentServiceImpl;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;

/**
 * Configuration on database and service interface
 */
public class GossipDataModule extends MyBatisModule {

    private void initialDatabaseType() {
        final String databaseType = GossipConfigModule.getDatabaseType();
        install(JdbcHelper.jdbcModule(databaseType));
    }

    /**
     * Common dto service initialize
     */
    private void initialService() {
        bind(ArticleConverter.class).toProvider(ArticleConverter::new).in(Singleton.class);
        bind(CommentConverter.class).toProvider(CommentConverter::new).in(Singleton.class);

        bind(ArticleService.class).to(ArticleServiceImpl.class).in(Singleton.class);
        bind(AuthorService.class).to(AuthorServiceImpl.class).in(Singleton.class);
        bind(CommentService.class).to(CommentServiceImpl.class).in(Singleton.class);
    }

    @Override
    protected void initialize() {
        initialDatabaseType();

        environmentId("default");
        useGeneratedKeys(true);
        mapUnderscoreToCamelCase(true);

        bindDataSourceProviderType(GossipDataSourceProvider.class);
        bindTransactionFactoryType(JdbcTransactionFactory.class);
        addSimpleAliases("me.yufan.gossip.mybatis.entity");
        addMapperClasses("me.yufan.gossip.mybatis.mapper");
        addTypeHandlerClasses("me.yufan.gossip.mybatis.handler");

        initialService();
    }
}
