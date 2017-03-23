package me.yufan.gossip.exception.database;

import me.yufan.gossip.exception.base.BaseGossipException;
import me.yufan.gossip.exception.base.GossipError;

public class AlreadyExistedException extends BaseGossipException {
    private static final long serialVersionUID = -5984992765811313302L;

    public AlreadyExistedException(String message) {
        super(message, GossipError.DUPLICATED_ITEM_IN_DB);
    }

    public AlreadyExistedException(String message, Throwable cause) {
        super(message, cause, GossipError.DUPLICATED_ITEM_IN_DB);
    }
}
