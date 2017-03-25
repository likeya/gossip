package me.yufan.gossip.module;

import com.google.common.base.Charsets;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import me.yufan.gossip.exception.GossipInitializeException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Gossip configuration loader
 */
@Slf4j
public class GossipConfigModule extends AbstractModule {

    private static final String CONFIG_FILE = "gossip.properties";

    private static Properties gossipConfig = new Properties();

    public GossipConfigModule(String configPath, String configFile) {
        try {
            URI configUri = gossipConfigUri(configPath, configFile == null ? CONFIG_FILE : configFile);
            gossipConfig.load(new StringReader(IOUtils.toString(configUri, Charsets.UTF_8.name())));
        } catch (IOException e) {
            throw new GossipInitializeException("Couldn't load the configuration file," +
                    " check your command line arguments.", e);
        }
    }

    @Override
    protected void configure() {
        Names.bindProperties(binder(), gossipConfig);
    }

    private URI gossipConfigUri(String configPath, @NonNull String configFile) {
        if (configPath == null || "".equals(configPath.trim())) {
            throw new GossipInitializeException("Wrong config path, check your bootstrap shell scripts");
        }
        return Paths.get(configPath, configFile).toUri();
    }

    static String getDatabaseType() {
        if (gossipConfig.isEmpty()) {
            throw new GossipInitializeException("GossipConfigModule must be initialized before GossipDataModule");
        }
        // if no database specified, we would use mysql
        return gossipConfig.getProperty("gossip.database.type", "mysql");
    }
}
