package me.yufan.gossip.exception.base;

import lombok.AllArgsConstructor;

import javax.ws.rs.core.Response.Status;

@AllArgsConstructor
public enum GossipError {

    // The exception code for unexpected exception especially on bootstrap
    INTERNAL_ERROR(100001, Status.INTERNAL_SERVER_ERROR),

    // The exception for db
    SQL_EXCEPTION(200001, Status.INTERNAL_SERVER_ERROR),
    DUPLICATED_ITEM_IN_DB(200002, Status.CONFLICT),

    // The exception for api
    VALIDATE_ERROR(300001, Status.BAD_REQUEST);

    public final Integer errorCode;

    public final Status status;
}
