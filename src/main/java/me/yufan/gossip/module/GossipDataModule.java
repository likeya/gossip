package me.yufan.gossip.module;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Singleton;
import me.yufan.gossip.service.ArticleService;
import me.yufan.gossip.service.AuthorService;
import me.yufan.gossip.service.CommentService;
import me.yufan.gossip.service.impl.ArticleServiceImpl;
import me.yufan.gossip.service.impl.AuthorServiceImpl;
import me.yufan.gossip.service.impl.CommentServiceImpl;
import me.yufan.gossip.support.GossipDataSourceProvider;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import java.util.Map;

/**
 * Configuration on database and service interface
 */
public class GossipDataModule extends MyBatisModule {

    private static final Map<String, JdbcHelper> databaseHolder = ImmutableMap.<String, JdbcHelper>builder()
            .put("mysql", JdbcHelper.MySQL)
            .put("sqlite", JdbcHelper.SQLITE_FILE)
            .build();

    private void initialDatabaseType() {
        final String databaseType = GossipConfigModule.getDatabaseType();
        final JdbcHelper helper = databaseHolder.get(databaseType);
        if (helper == null) {
            throw new IllegalArgumentException("Illegal config, we only support mysql or sqlite");
        }
        install(helper);
    }

    /**
     * Common dto service initialize
     */
    private void initialService() {
        bind(ArticleService.class).to(ArticleServiceImpl.class).in(Singleton.class);
        bind(AuthorService.class).to(AuthorServiceImpl.class).in(Singleton.class);
        bind(CommentService.class).to(CommentServiceImpl.class).in(Singleton.class);
    }

    @Override
    protected void initialize() {
        initialDatabaseType();

        bindDataSourceProviderType(GossipDataSourceProvider.class);
        bindTransactionFactoryType(JdbcTransactionFactory.class);
        addSimpleAliases("me.yufan.gossip.entity");
        addMapperClasses("me.yufan.gossip.mapper");

        initialService();
    }
}
