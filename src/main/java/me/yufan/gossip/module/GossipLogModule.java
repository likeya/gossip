package me.yufan.gossip.module;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import com.google.inject.Binder;
import com.google.inject.Module;
import lombok.AllArgsConstructor;
import me.yufan.gossip.exception.GossipInitializeException;
import org.apache.ibatis.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Make sure all the framework that gossip used would use
 */
@AllArgsConstructor
public class GossipLogModule implements Module {

    private final String configPath;

    @Override
    public void configure(Binder binder) {
        // Set jboss logging component to use slf4j, it's a shit hard coding
        System.setProperty("org.jboss.logging.provider", "slf4j");

        // Set MyBatis to use slf4j
        LogFactory.useSlf4jLogging();

        // Set druid to use slf4f
        System.setProperty("druid.logType", "slf4j");

        // Netty would first use slf4j, no need to config
        // MySQL's logger is configurable through its jdbc properties, no need to config here

        // Make logback load external config file
        initializeLogback();
    }

    private void initializeLogback() {
        Path logbackFilePath = Paths.get(configPath, "logback.xml");
        if (logbackFilePath.toFile().exists()) {
            try {
                // Load logback configuration
                LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
                context.reset();
                JoranConfigurator configurator = new JoranConfigurator();
                configurator.setContext(context);
                configurator.doConfigure(logbackFilePath.toFile());

                // Install java.util.logging bridge
                SLF4JBridgeHandler.removeHandlersForRootLogger();
                SLF4JBridgeHandler.install();
            } catch (JoranException e) {
                throw new GossipInitializeException("Misconfiguration on logback.xml, check it.", e);
            }
        }
    }
}
