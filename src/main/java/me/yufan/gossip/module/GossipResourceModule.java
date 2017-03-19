package me.yufan.gossip.module;

import com.google.inject.Binder;
import com.google.inject.Module;
import me.yufan.gossip.rest.IndexResource;

public class GossipResourceModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(IndexResource.class);
    }
}
