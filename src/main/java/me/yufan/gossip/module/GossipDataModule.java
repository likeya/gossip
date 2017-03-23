package me.yufan.gossip.module;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Singleton;
import com.mysql.jdbc.Driver;
import lombok.extern.slf4j.Slf4j;
import me.yufan.gossip.converter.ArticleConverter;
import me.yufan.gossip.converter.CommentConverter;
import me.yufan.gossip.exception.GossipInitializeException;
import me.yufan.gossip.mybatis.GossipDataSourceProvider;
import me.yufan.gossip.service.ArticleService;
import me.yufan.gossip.service.AuthorService;
import me.yufan.gossip.service.CommentService;
import me.yufan.gossip.service.impl.ArticleServiceImpl;
import me.yufan.gossip.service.impl.AuthorServiceImpl;
import me.yufan.gossip.service.impl.CommentServiceImpl;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import java.util.Map;

import static com.google.inject.name.Names.named;
import static org.mybatis.guice.datasource.helper.JdbcHelper.H2_EMBEDDED;
import static org.mybatis.guice.datasource.helper.JdbcHelper.MySQL;

/**
 * Configuration on database and service interface
 */
@Slf4j
public class GossipDataModule extends MyBatisModule {

    private static final Map<String, JdbcHelper> databaseHolder = ImmutableMap.<String, JdbcHelper>builder()
            .put("mysql", MySQL)
            .put("h2", H2_EMBEDDED)
            .build();

    private static final Map<JdbcHelper, String> driverClassMap = ImmutableMap.<JdbcHelper, String>builder()
            .put(MySQL, Driver.class.getName())
            .put(H2_EMBEDDED, org.h2.Driver.class.getName())
            .build();

    private void initialDatabaseType() {
        final String databaseType = GossipConfigModule.getDatabaseType();
        final JdbcHelper helper = databaseHolder.get(databaseType);
        if (helper == null) {
            throw new GossipInitializeException("Illegal config, we only support mysql or h2");
        }
        install(helper);
        bindConstant().annotatedWith(named("JDBC.driverClassName")).to(driverClassMap.get(helper));
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
