package me.yufan.gossip.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import me.yufan.gossip.rest.CommentResource;
import me.yufan.gossip.rest.IndexResource;
import me.yufan.gossip.rest.support.GossipExceptionProvider;
import me.yufan.gossip.rest.support.GossipValidateErrorProvider;
import me.yufan.gossip.rest.support.JacksonOptimizeProvider;
import me.yufan.gossip.rest.support.UnexpectedExceptionProvider;
import org.jboss.resteasy.plugins.guice.ext.RequestScopeModule;

/**
 * JAX-RS specified Resource, managed by guice to get a singleton instance
 */
public class GossipResourceModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new RequestScopeModule());

        // Jackson for resteasy
        bind(JacksonOptimizeProvider.class).in(Singleton.class);
        // Jackson for guice
        bind(ObjectMapper.class).toProvider(JacksonOptimizeProvider.class).in(Singleton.class);

        bind(GossipExceptionProvider.class).in(Singleton.class);
        bind(GossipValidateErrorProvider.class).in(Singleton.class);
        bind(UnexpectedExceptionProvider.class).in(Singleton.class);

        bind(IndexResource.class).in(Singleton.class);
        bind(CommentResource.class).in(Singleton.class);
    }
}
