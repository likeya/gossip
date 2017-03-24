package me.yufan.gossip.rest.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import me.yufan.gossip.exception.base.BaseGossipException;
import me.yufan.gossip.exception.base.GossipError;
import me.yufan.gossip.rest.support.Pagination;
import org.jboss.resteasy.annotations.providers.jackson.Formatted;

/**
 * Common response for restful api
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Formatted
public class BaseApiResponse<E> extends Pagination {
    private static final long serialVersionUID = -4213123393562785802L;

    private Boolean success = true;

    private String errorMsg;

    private Integer errorCode;

    private E result = null; // NOSONAR

    /**
     * Helper function below for quick create error Response
     * Such as exception handler
     */
    public static BaseApiResponse<Void> failed(BaseGossipException exception) {
        return new BaseApiResponse<Void>().setSuccess(false).setErrorCode(exception.getError().errorCode)
            .setErrorMsg(exception.getMessage());
    }

    public static BaseApiResponse<Void> failed(Exception exception) {
        return new BaseApiResponse<Void>().setSuccess(false).setErrorCode(GossipError.INTERNAL_ERROR.errorCode)
            .setErrorMsg(exception.getMessage());
    }

    public static BaseApiResponse<String> message(String msg) {
        return new BaseApiResponse<String>().setSuccess(true).setResult(msg);
    }

    public static BaseApiResponse<Void> ok() {
        return new BaseApiResponse<Void>().setSuccess(true);
    }

    public BaseApiResponse<E> body(E entity) {
        return body(entity, null);
    }

    public BaseApiResponse<E> body(E entity, Pagination pagination) {
        BaseApiResponse<E> response = new BaseApiResponse<E>().setSuccess(true).setResult(entity);
        if (pagination != null) {
            response.setCurrentPage(pagination.getCurrentPage());
            response.setPageSize(pagination.getPageSize());
            response.setTotalPage(pagination.getTotalPage());
        }
        return response;
    }
}
