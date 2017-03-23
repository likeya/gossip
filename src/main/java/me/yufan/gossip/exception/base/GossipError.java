package me.yufan.gossip.exception.base;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum GossipError {

    // The exception code for unexpected exception especially on bootstrap
    INTERNAL_ERROR(100001, 500),


    // The exception for db
    SQL_EXCEPTION(200001, 400),
    DUPLICATED_ITEM_IN_DB(200002, 400);

    public final Integer errorCode;

    public final Integer serverCode;
}
