package me.yufan.gossip.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.api.validation.ResteasyConstraintViolation;

import java.io.Serializable;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static me.yufan.gossip.exception.base.GossipError.VALIDATE_ERROR;

@Slf4j
public class ValidateErrorResponse extends BaseApiResponse<List<ValidateErrorResponse.ErrorItem>> {

    public ValidateErrorResponse(String msg) {
        super.setSuccess(false);
        super.setErrorMsg(msg);
        super.setErrorCode(VALIDATE_ERROR.errorCode);
    }

    public ValidateErrorResponse(List<ResteasyConstraintViolation> violations) {
        super.setSuccess(false);
        super.setResult(translateViolation(violations));
        super.setErrorCode(VALIDATE_ERROR.errorCode);
    }

    private List<ValidateErrorResponse.ErrorItem> translateViolation(List<ResteasyConstraintViolation> violations) {
        return violations.stream().map(violator -> new ErrorItem(violator.getPath(), violator.getMessage())).collect(toList());
    }

    @AllArgsConstructor
    @Getter
    static class ErrorItem implements Serializable {
        private static final long serialVersionUID = 8683650730034504749L;

        private final String field;

        private final String constant;
    }
}

