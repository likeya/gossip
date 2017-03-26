package me.yufan.gossip.mybatis.support;

import com.google.inject.Binder;
import com.google.inject.Key;
import com.google.inject.Module;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.yufan.gossip.exception.GossipInitializeException;

import java.util.Arrays;
import java.util.Map;

import static com.google.inject.name.Names.named;
import static com.google.inject.util.Providers.guicify;
import static com.google.inject.util.Providers.of;
import static java.util.stream.Collectors.toMap;

/**
 * Inspired by {@see org.mybatis.guice.datasource.helper.JdbcHelper},
 * custom my own database switch for special needs.
 * Such as mysql logs, h2 running mode.
 * The most important thing, make the gossip configuration more friendly.
 */
@AllArgsConstructor
@Getter
public enum JdbcHelper implements Module {

    H2_EMBEDDED("h2", "jdbc:h2:${JDBC.schema};MODE=MySQL", "jdbc:h2:${JDBC.schema};MODE=MySQL", "org.h2.Driver"),

    MYSQL("mysql", "jdbc:mysql://${JDBC.host}:${JDBC.port}/${JDBC.schema}" + JdbcHelper.MYSQL_QUERY_STRING,
        "jdbc:mysql://${JDBC.host}:${JDBC.port}" + JdbcHelper.MYSQL_QUERY_STRING, "com.mysql.jdbc.Driver");

    private final String dbType;

    private final String urlTemplate;

    private final String rawUrlTemplate;

    private final String driverClass;

    private static final String MYSQL_QUERY_STRING = "?autoReconnect=true&useSSL=false&logger=com.mysql.jdbc.log.Slf4JLogger&profileSQL=true";

    private static final Map<String, JdbcHelper> helperMap = Arrays.stream(values()).collect(toMap(JdbcHelper::getDbType, helper -> helper));

    @Override
    public void configure(Binder binder) {
        // To make druid happy
        binder.bindConstant().annotatedWith(named("JDBC.driverClassName")).to(driverClass);
        binder.bindConstant().annotatedWith(named("JDBC.driver")).to(driverClass);

        binder.bind(Key.get(String.class, named("JDBC.url"))).toProvider(guicify(new Formatter(urlTemplate)));
        // To make custom initialize
        binder.bind(Key.get(String.class, named("JDBC.rawUrl"))).toProvider(guicify(new Formatter(rawUrlTemplate)));
        binder.bind(Key.get(String.class, named("JDBC.type"))).toProvider(of(dbType));
    }

    public static JdbcHelper jdbcModule(String dbType) {
        if (dbType == null) {
            throw new GossipInitializeException("Need configuring [gossip.database.type] in your gossip configuration file.");
        }
        final JdbcHelper helper = helperMap.get(dbType);
        if (helper == null) {
            throw new GossipInitializeException("Illegal config, we only support mysql or h2");
        }
        return helper;
    }
}
