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
    public String helloWorld() {
        return "Hello";
    }
}
