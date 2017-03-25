package me.yufan.gossip.rest.support;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

@Provider
public class JacksonOptimizeProvider implements ContextResolver<ObjectMapper> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public ObjectMapper getContext(Class<?> type) {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }
}
