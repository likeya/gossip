package me.yufan.gossip.exception;

import me.yufan.gossip.exception.base.BaseGossipException;
import me.yufan.gossip.exception.base.GossipError;

/**
 * Bootstrap exception for gossip, only allowed in guice module & root package class
 */
public class GossipInitializeException extends BaseGossipException {
    private static final long serialVersionUID = 1162166231465265844L;

    public GossipInitializeException(String message) {
        super(message, GossipError.INTERNAL_ERROR);
    }

    public GossipInitializeException(String message, Throwable cause) {
        super(message, cause, GossipError.INTERNAL_ERROR);
    }
}
