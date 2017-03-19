package me.yufan.gossip;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.AllArgsConstructor;
import me.yufan.gossip.module.GossipConfigModule;
import me.yufan.gossip.module.GossipDataModule;
import me.yufan.gossip.module.GossipResourceModule;
import org.jboss.resteasy.plugins.guice.ModuleProcessor;
import org.jboss.resteasy.spi.ResteasyDeployment;

/**
 * The bootstrap snippet for integration guice and resteasy
 */
@AllArgsConstructor
class Bootstrap {

    private final ResteasyDeployment deployment;

    private final String configPath;

    void start() {
        final Injector injector = Guice.createInjector(new GossipResourceModule(), new GossipConfigModule(configPath),
                new GossipDataModule());
        ModuleProcessor processor = new ModuleProcessor(deployment.getRegistry(), deployment.getProviderFactory());
        processor.processInjector(injector);
    }
}
