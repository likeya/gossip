package me.yufan.gossip;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;
import me.yufan.gossip.module.GossipConfigModule;
import me.yufan.gossip.module.GossipDataModule;
import me.yufan.gossip.module.GossipLogModule;
import me.yufan.gossip.module.GossipResourceModule;
import org.jboss.resteasy.plugins.guice.ModuleProcessor;
import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;

@Slf4j
class GossipServer extends NettyJaxrsServer {

    private final String configPath;

    private final String configFile;

    GossipServer(String configPath, String configFile) {
        super();
        this.configPath = configPath;
        this.configFile = configFile;
    }

    @Override
    public void start() {
        super.start();

        Injector injector = Guice.createInjector(new GossipLogModule(configPath), new GossipConfigModule(configPath, configFile),
            new GossipDataModule(), new GossipResourceModule());
        ModuleProcessor processor = new ModuleProcessor(deployment.getRegistry(), deployment.getProviderFactory());
        processor.processInjector(injector);

        log.info("Successfully launched gossip server");
    }
}
