package me.yufan.gossip.mybatis.support;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.util.Providers;

import javax.inject.Provider;
import java.util.List;
import java.util.StringTokenizer;

import static java.util.stream.Collectors.joining;

class Formatter implements Provider<String> {
    private static final String VAR_BEGIN = "${";

    private static final String VAR_END = "}";

    private final List<Provider<String>> appenderList = Lists.newArrayList();

    private final List<KeyResolver> resolvers = Lists.newArrayList();

    Formatter(final String pattern) {
        final List<String> patterns = Splitter.on(VAR_BEGIN).omitEmptyStrings().splitToList(pattern);

        patterns.forEach(pt -> {
            if (!pt.contains(VAR_END)) {
                appenderList.add(Providers.of(pt));
            } else {
                StringTokenizer token = new StringTokenizer(pt, VAR_END);
                String guiceKey = token.nextToken();
                String rawString = null;
                if (token.hasMoreTokens()) {
                    rawString = token.nextToken();
                }
                final KeyResolver resolver = new KeyResolver(guiceKey);
                appenderList.add(resolver);
                resolvers.add(resolver);
                appenderList.add(Providers.of(rawString));
            }
        });
    }

    @Inject
    public void setInjector(Injector injector) {
        resolvers.forEach(keyResolver -> keyResolver.setInjector(injector));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String get() {
        return appenderList.stream().map(Provider::get).collect(joining());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return appenderList.toString();
    }
}
