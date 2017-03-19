package me.yufan.gossip.rest.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum GossipError {

    INTERNAL_ERROR(500, 500);

    public final Integer errorCode;

    public final Integer serverCode;
}
