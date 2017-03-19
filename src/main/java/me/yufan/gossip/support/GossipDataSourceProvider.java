package me.yufan.gossip.support;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.mybatis.guice.datasource.druid.DruidDataSourceProvider;

import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;

import static org.apache.ibatis.io.Resources.getResourceAsReader;

/**
 * Override default datasource to auto create mysql database and initialize schema
 */
@Slf4j
public class GossipDataSourceProvider extends DruidDataSourceProvider {

    @SneakyThrows
    private void executeSQL(Connection connection, Reader reader) {
        ScriptRunner runner = new ScriptRunner(connection);
        runner.setAutoCommit(true);
        runner.setStopOnError(true);
        runner.runScript(reader);
        runner.closeConnection();
    }

    /**
     * Create database if it's not existed
     */
    @SneakyThrows
    private void createSchema(final String jdbcUrl, final String databaseName, final String username, final String password) {
        log.debug("Execute auto schema creation on {}", jdbcUrl);

        try (final Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            executeSQL(connection, new StringReader("CREATE DATABASE IF NOT EXISTS " + databaseName));
        }
    }

    /**
     * Auto load database schema
     * TODO how to auto update database schema
     */
    @SneakyThrows
    private void loadSchema(final String jdbcUrl, final String username, final String password) {
        log.debug("Execute auto schema load on {}", jdbcUrl);

        try (final Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            executeSQL(connection, getResourceAsReader("schema.sql"));
        }
    }

    private String normalizeJdbcUrl(@NonNull String url) {
        return url.substring(0, url.lastIndexOf('/') + 1);
    }

    @Inject
    public void initialSchema(@Named("JDBC.url") String jdbcUrl, @Named("JDBC.schema") String databaseName,
                              @Named("JDBC.username") final String username, @Named("JDBC.password") final String password) {
        createSchema(normalizeJdbcUrl(jdbcUrl), databaseName, username, password);
        loadSchema(jdbcUrl, username, password);
    }
}
