package me.yufan.gossip.rest;

import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Common default method in this interface, abstract class is not well supported in Guice 4
 * So it's good to use java 8 default method, no more abstract class
 */
interface BaseResource {
}
