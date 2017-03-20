package me.yufan.gossip.exception.base;

import lombok.Getter;

@Getter
public abstract class BaseGossipException extends RuntimeException {
    private static final long serialVersionUID = -98965946339543780L;

    private final GossipError error;

    public BaseGossipException(String message, GossipError error) {
        super(message);
        this.error = error;
    }

    public BaseGossipException(String message, Throwable cause, GossipError error) {
        super(message, cause);
        this.error = error;
    }
}
