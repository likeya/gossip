package me.yufan.gossip;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.yufan.gossip.module.GossipConfigModule;
import me.yufan.gossip.module.GossipDataModule;
import me.yufan.gossip.module.GossipResourceModule;
import org.jboss.resteasy.plugins.guice.ModuleProcessor;
import org.jboss.resteasy.spi.ResteasyDeployment;

/**
 * The bootstrap snippet for integration guice and resteasy
 */
@AllArgsConstructor
@Slf4j
class Bootstrap {

    private final ResteasyDeployment deployment;

    private final String configPath;

    private final String configFile;

    void start() {
        final Injector injector = Guice.createInjector(new GossipConfigModule(configPath, configFile), new GossipDataModule(),
                new GossipResourceModule());
        ModuleProcessor processor = new ModuleProcessor(deployment.getRegistry(), deployment.getProviderFactory());
        processor.processInjector(injector);

        log.info("Successful launched gossip server");
    }
}
