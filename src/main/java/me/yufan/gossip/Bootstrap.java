package me.yufan.gossip;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.AllArgsConstructor;
import me.yufan.gossip.module.GossipResourceModule;
import org.jboss.resteasy.plugins.guice.ModuleProcessor;
import org.jboss.resteasy.spi.ResteasyDeployment;

/**
 * The bootstrap snippet for integration guice and resteasy
 */
@AllArgsConstructor
class Bootstrap {

    private final ResteasyDeployment deployment;

    void start() {
        final Injector injector = Guice.createInjector(new GossipResourceModule());
        ModuleProcessor processor = new ModuleProcessor(deployment.getRegistry(), deployment.getProviderFactory());
        processor.processInjector(injector);
    }
}
