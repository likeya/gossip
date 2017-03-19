package me.yufan.gossip.module;

import com.google.inject.AbstractModule;
import me.yufan.gossip.rest.IndexResource;
import me.yufan.gossip.rest.support.GossipExceptionProvider;

public class GossipResourceModule extends AbstractModule {

    @Override
    protected void configure() {
        binder().bind(GossipExceptionProvider.class);
        binder().bind(IndexResource.class);
    }
}
