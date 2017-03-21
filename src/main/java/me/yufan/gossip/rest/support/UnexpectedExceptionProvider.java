package me.yufan.gossip.rest.support;

import me.yufan.gossip.rest.response.BaseApiResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnexpectedExceptionProvider implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        return Response.serverError().entity(BaseApiResponse.failed(exception)).build();
    }
}
