package me.yufan.gossip.rest;

import com.google.inject.Singleton;
import me.yufan.gossip.rest.response.BaseApiResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Singleton
@Path("/")
@Produces(APPLICATION_JSON)
public class IndexResource implements BaseResource {

    @GET
    public BaseApiResponse<String> welcome() {
        final String welcome = "Welcome to use gossip comment system," +
                " the system is fully started when you saw this page";
        return BaseApiResponse.message(welcome);
    }

    @GET
    @Path("/system-info")
    public BaseApiResponse<Void> systemInfo() {
        // TODO add gossip info, system info, trace info, etc
        return BaseApiResponse.ok();
    }
}
