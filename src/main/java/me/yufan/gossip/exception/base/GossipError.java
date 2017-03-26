package me.yufan.gossip.exception.base;

import lombok.AllArgsConstructor;

import javax.ws.rs.core.Response.Status;

@AllArgsConstructor
public enum GossipError {

    // The exception code for unexpected exception especially on bootstrap
    INTERNAL_ERROR(100001, Status.INTERNAL_SERVER_ERROR, "The unexpected exception"),

    // The exception for db
    SQL_EXCEPTION(200001, Status.INTERNAL_SERVER_ERROR, "Wrong format in SQL"),
    DUPLICATED_ITEM_IN_DB(200002, Status.CONFLICT, "The item to insert is already existed in DB"),
    NO_SUCH_ITEM_IN_DB(200003, Status.BAD_REQUEST, "The item we need is not existed but should be expected"),

    // The exception for api
    VALIDATE_ERROR(300001, Status.BAD_REQUEST, "Wrong query parameter in API's constraint");

    public final Integer errorCode;

    public final Status status;

    // Only for developer to understand the meaning of error item
    private final String desc; // NOSONAR
}
