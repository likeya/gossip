package me.yufan.gossip.rest.support;

import lombok.extern.slf4j.Slf4j;
import me.yufan.gossip.rest.response.BaseApiResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

@Slf4j
@Provider
public class UnexpectedExceptionProvider implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        if (log.isDebugEnabled()) {
            log.debug("", exception);
        }
        return Response.serverError().type(APPLICATION_JSON_TYPE).entity(BaseApiResponse.failed(exception)).build();
    }
}
