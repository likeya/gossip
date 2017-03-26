package me.yufan.gossip.mybatis.support;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import lombok.extern.slf4j.Slf4j;
import me.yufan.gossip.exception.GossipInitializeException;
import org.apache.ibatis.jdbc.RuntimeSqlException;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.mybatis.guice.datasource.druid.DruidDataSourceProvider;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.apache.ibatis.io.Resources.getResourceAsReader;

/**
 * Override default datasource to auto create mysql database and initialize schema
 */
@Slf4j
public class GossipDataSourceProvider extends DruidDataSourceProvider {

    private void executeSQL(Connection connection, Reader reader) throws SQLException {
        ScriptRunner runner = new ScriptRunner(connection);
        runner.setAutoCommit(true);
        runner.setStopOnError(true);

        try {
            runner.runScript(reader);
        } catch (RuntimeSqlException e) {
            throw new SQLException(e); // Force to handle the exception by myself
        }
    }

    /**
     * Create database if it's not existed
     */
    private void createSchema(final String jdbcUrl, final String databaseName, final String username, final String password) {
        log.debug("Execute auto schema creation on {}", jdbcUrl);

        try (final Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            executeSQL(connection, new StringReader("CREATE DATABASE IF NOT EXISTS " + databaseName + ";"));
        } catch (SQLException e) {
            throw new GossipInitializeException("Couldn't create database, this may be the wrong privilege on you operation." +
                "\n Or could be a db misconfiguration on gossip, error info [" + e.getMessage() + "]", e);
        }
    }

    /**
     * Auto load database schema
     * TODO how to auto update database schema
     */
    private void loadSchema(final String jdbcUrl, final String username, final String password, final String dbType) {
        log.debug("Execute auto schema load on {}", jdbcUrl);
        try (final Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            executeSQL(connection, getResourceAsReader(String.format("schema-%s.sql", dbType)));
        } catch (SQLException | IOException e) {
            throw new GossipInitializeException("Database schema load failed, plz check your db connection and configuration", e);
        }
    }

    @Inject
    public void initialSchema(@Named("JDBC.url") String jdbcUrl, @Named("JDBC.rawUrl") String jdbcRawUrl,
                              @Named("JDBC.schema") String databaseName, @Named("JDBC.username") final String username,
                              @Named("JDBC.password") final String password, @Named("JDBC.type") String type) {
        if (!"".equals(jdbcRawUrl)) {
            createSchema(jdbcRawUrl, databaseName, username, password);
        }
        loadSchema(jdbcUrl, username, password, type);
    }
}
