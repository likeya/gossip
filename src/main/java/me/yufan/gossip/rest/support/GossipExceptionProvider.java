package me.yufan.gossip.rest.support;

import me.yufan.gossip.exception.base.BaseGossipException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class GossipExceptionProvider implements ExceptionMapper<BaseGossipException> {

    @Override
    public Response toResponse(BaseGossipException exception) {
        // TODO using common rest response message to make front happy
        return Response.status(exception.getError().errorCode).entity(exception.getMessage()).build();
    }
}
