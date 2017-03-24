package me.yufan.gossip.module;

import com.google.inject.Binder;
import com.google.inject.Module;
import org.apache.ibatis.logging.LogFactory;

/**
 * Make sure all the framework that gossip used would use
 */
public class GossipLogModule implements Module {

    @Override
    public void configure(Binder binder) {
        // Set jboss logging component to use slf4j, it's a shit hard coding
        System.setProperty("org.jboss.logging.provider", "slf4j");

        // Set MyBatis to use slf4j
        LogFactory.useSlf4jLogging();

        // Set druid to use slf4f
        System.setProperty("druid.logType", "slf4j");

        // Netty would first use slf4j, no need to config
    }
}
