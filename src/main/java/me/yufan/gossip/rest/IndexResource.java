package me.yufan.gossip.rest;

import com.google.inject.Singleton;
import me.yufan.gossip.rest.response.BaseApiResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/")
public class IndexResource implements BaseResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response welcome() {
        final String welcome = "Welcome to use gossip comment system," +
                "\n the system is fully started when you saw this page";
        return Response.ok(BaseApiResponse.message(welcome)).build();
    }
}
