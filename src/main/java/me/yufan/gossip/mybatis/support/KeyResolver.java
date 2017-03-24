package me.yufan.gossip.mybatis.support;

import com.google.inject.ConfigurationException;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import me.yufan.gossip.exception.GossipInitializeException;

import javax.inject.Provider;

import static com.google.inject.name.Names.named;

class KeyResolver implements Provider<String> {

    private final Key<String> key;

    private final String keyName;

    @Inject
    private Injector injector;

    KeyResolver(final String key) {
        this.key = Key.get(String.class, named(key));
        this.keyName = key;
    }

    @Inject
    public void setInjector(Injector injector) {
        this.injector = injector;
    }

    @Override
    public String get() {
        try {
            return injector.getInstance(key);
        } catch (ConfigurationException e) { // NOSONAR no need to logs
            throw new GossipInitializeException(keyName + " should be configured on gossip.properties");
        }
    }

    @Override
    public String toString() {
        return keyName;
    }
}
