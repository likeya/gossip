package me.yufan.gossip.rest.support;

import me.yufan.gossip.exception.base.BaseGossipException;
import me.yufan.gossip.rest.response.BaseApiResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

@Provider
public class GossipExceptionProvider implements ExceptionMapper<BaseGossipException> {

    @Override
    public Response toResponse(BaseGossipException exception) {
        return Response.status(exception.getError().status).type(APPLICATION_JSON_TYPE)
            .entity(BaseApiResponse.failed(exception)).build();
    }
}
