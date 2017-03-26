package me.yufan.gossip.rest.support;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Provider;

import javax.ws.rs.ext.ContextResolver;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

/**
 * Jackson share config for Resteasy & Guice
 * Resteasy required {@see javax.ws.rs.ext.Provider} annotation, it's quit shit!
 */
@javax.ws.rs.ext.Provider
public class JacksonOptimizeProvider implements ContextResolver<ObjectMapper>, Provider<ObjectMapper> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public ObjectMapper getContext(Class<?> type) {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }

    @Override
    public ObjectMapper get() {
        return mapper;
    }
}
