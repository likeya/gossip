package me.yufan.gossip.exception.database;

import me.yufan.gossip.exception.base.BaseGossipException;

import static me.yufan.gossip.exception.base.GossipError.NO_SUCH_ITEM_IN_DB;

public class NotPresentException extends BaseGossipException {
    private static final long serialVersionUID = 445238232813786603L;

    public NotPresentException(String message) {
        super(message, NO_SUCH_ITEM_IN_DB);
    }

    public NotPresentException(String message, Throwable cause) {
        super(message, cause, NO_SUCH_ITEM_IN_DB);
    }
}
