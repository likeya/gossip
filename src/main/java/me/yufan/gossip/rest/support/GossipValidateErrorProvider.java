package me.yufan.gossip.rest.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import me.yufan.gossip.rest.response.ValidateErrorResponse;
import org.jboss.resteasy.api.validation.ResteasyConstraintViolation;
import org.jboss.resteasy.api.validation.ResteasyViolationException;

import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static org.jboss.resteasy.api.validation.Validation.VALIDATION_HEADER;

/**
 * handle {@see javax.validation.ValidationException} for frontend friendly response.
 * The check logic is based {@see org.jboss.resteasy.api.validation.ResteasyViolationExceptionMapper}
 */
@Slf4j
@Provider
public class GossipValidateErrorProvider implements ExceptionMapper<ValidationException> {

    private final ObjectMapper objectMapper;

    @Inject
    public GossipValidateErrorProvider(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Response toResponse(ValidationException exception) {
        if (exception instanceof ResteasyViolationException) {
            ResteasyViolationException resteasyViolationException = ResteasyViolationException.class.cast(exception);
            Exception e = resteasyViolationException.getException();
            if (e == null) {
                return buildViolationReportResponse(resteasyViolationException);
            }
        }

        // Common response
        return exceptionResponse(exception);
    }

    private Response exceptionResponse(Exception exception) {
        ValidateErrorResponse errorResponse = new ValidateErrorResponse(unwrapException(exception));
        return Response.status(BAD_REQUEST).header(VALIDATION_HEADER, "true")
            .type(APPLICATION_JSON_TYPE).entity(errorResponse).build();
    }

    private Response buildViolationReportResponse(ResteasyViolationException exception) {
        ResponseBuilder builder = Response.status(BAD_REQUEST);
        builder.header(VALIDATION_HEADER, "true");
        builder.type(APPLICATION_JSON_TYPE);

        List<ResteasyConstraintViolation> violations = exception.getViolations();

        if (violations.isEmpty()) {
            builder.entity(new ValidateErrorResponse(exception.toString()));
        } else {
            builder.entity(new ValidateErrorResponse(violations));
        }

        return builder.build();
    }

    // No recursion on exception handle
    private String unwrapException(final Throwable t) {
        if (t != null) {
            StringBuilder sb = new StringBuilder(128);
            Throwable throwable = t;
            int depth = 0;
            sb.append(throwable.toString());
            while (throwable.getCause() != null && throwable != throwable.getCause()) {
                sb.append("[");
                throwable = throwable.getCause();
                sb.append(throwable.toString());
                depth++;
            }
            sb.append(Strings.repeat("]", depth));
            return sb.toString();
        } else {
            return "";
        }
    }
}
