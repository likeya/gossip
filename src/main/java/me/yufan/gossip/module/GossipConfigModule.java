package me.yufan.gossip.module;

import com.google.common.base.Charsets;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.StringReader;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Gossip configuration loader
 */
public class GossipConfigModule extends AbstractModule {

    private static final String CONFIG_FILE = "gossip.properties";

    private static Properties gossipConfig = new Properties();

    @SneakyThrows
    public GossipConfigModule(String configPath) {
        Properties config = new Properties();
        config.load(new StringReader(IOUtils.toString(gossipConfigUri(configPath), Charsets.UTF_8.name())));
        gossipConfig = config;
    }

    @Override
    protected void configure() {
        Names.bindProperties(binder(), gossipConfig);
    }

    @SneakyThrows
    private URI gossipConfigUri(String configPath) {
        if (configPath == null || "".equals(configPath.trim())) {
            throw new IllegalArgumentException("Wrong config path, check your bootstrap shell");
        }
        return Paths.get(configPath + (configPath.endsWith("/") ? "" : "/") + CONFIG_FILE).toUri();
    }

    static String getDatabaseType() {
        // if no database specified, we would use mysql
        return gossipConfig.getProperty("gossip.database.type", "mysql");
    }
}
