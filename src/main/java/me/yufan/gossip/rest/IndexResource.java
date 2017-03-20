package me.yufan.gossip.rest;

import com.google.inject.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Singleton
@Path("/")
public class IndexResource implements BaseResource {

    @GET
    @Produces(value = "text/plain")
    // TODO change it to a api friendly response, could be used as heart beat page
    // TODO add gossip info, system info, trace info, etc
    public String welcome() {
        return "Welcome to use gossip comment system,\n the system is fully started when you saw this page";
    }
}
