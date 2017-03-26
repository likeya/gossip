package me.yufan.gossip.mybatis.mapper.base;

import com.google.common.base.Charsets;
import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.SneakyThrows;
import me.yufan.gossip.module.GossipConfigModule;
import me.yufan.gossip.module.GossipDataModule;
import me.yufan.gossip.module.GossipLogModule;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.BeforeClass;

import javax.sql.DataSource;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;

public abstract class MyBatisTestHelper {

    private static final String INIT_DB_SCRIPTS = "initialize-db.sql";
    protected static Injector injector;

    @SneakyThrows
    private static void loadInitializeData(Injector injector, Reader reader) {
        DataSource dataSource = injector.getInstance(DataSource.class);

        try (final Connection connection = dataSource.getConnection()) {
            ScriptRunner runner = new ScriptRunner(connection);
            runner.setAutoCommit(true);
            runner.setStopOnError(true);
            runner.runScript(reader);
        } // Auto close connection
    }

    @BeforeClass
    @SneakyThrows
    public static void initializeDatasource() {
        final Path testResourcePath = Paths.get("src/test/resources");
        final String path = testResourcePath.toAbsolutePath().toString();

        final URI dbInitFile = Paths.get((path.endsWith("/") ? path : path + "/") + INIT_DB_SCRIPTS).toUri();
        Reader reader = new StringReader(IOUtils.toString(dbInitFile, Charsets.UTF_8.name()));

        injector = Guice.createInjector(new GossipLogModule(path), new GossipConfigModule(path, "gossip-test.properties"),
            new GossipDataModule());
        loadInitializeData(injector, reader);
    }
}
