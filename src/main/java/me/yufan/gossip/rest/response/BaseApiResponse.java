package me.yufan.gossip.rest.response;

import lombok.Data;
import lombok.experimental.Accessors;
import me.yufan.gossip.exception.base.BaseGossipException;
import me.yufan.gossip.exception.base.GossipError;

import java.io.Serializable;

/**
 * Common response for restful api
 */
@Data
@Accessors(chain = true)
public class BaseApiResponse<E> implements Serializable {
    private static final long serialVersionUID = -4213123393562785802L;

    private Boolean success = true;

    private String errorMsg;

    private Integer statusCode;

    private E result = null; // NOSONAR

    /**
     * Helper function below for quick create error Response
     * Such as exception handler
     */
    public static BaseApiResponse<Void> failed(BaseGossipException exception) {
        return new BaseApiResponse<Void>().setSuccess(false).setStatusCode(exception.getError().errorCode)
                .setErrorMsg(exception.getMessage());
    }

    public static BaseApiResponse<Void> failed(Exception exception) {
        return new BaseApiResponse<Void>().setSuccess(false).setStatusCode(GossipError.INTERNAL_ERROR.errorCode)
                .setErrorMsg(exception.getMessage());
    }

    public static BaseApiResponse<String> message(String msg) {
        return new BaseApiResponse<String>().setSuccess(true).setResult(msg);
    }

    public static BaseApiResponse<Void> ok() {
        return new BaseApiResponse<Void>().setSuccess(true);
    }
}
