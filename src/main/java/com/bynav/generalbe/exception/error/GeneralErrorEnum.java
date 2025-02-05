package com.bynav.generalbe.exception.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Getter
public enum GeneralErrorEnum {
    TECH_ERROR("TECH_ERROR", "Technical error", HttpStatus.INTERNAL_SERVER_ERROR, SeverityEnum.CRITICAL),
    ENTRY_NOT_FOUND_INNER("53586", "ACCOUNT NOT FOUND", HttpStatus.NOT_FOUND, SeverityEnum.ERROR),
    ENTRY_NOT_FOUND("ENTRY_NOT_FOUND", "Resource not found", HttpStatus.NOT_FOUND, SeverityEnum.ERROR),
    AUTHENTICATION_ERROR("AUTHENTICATION_ERROR", "The user or password is incorrect", HttpStatus.UNAUTHORIZED, SeverityEnum.ERROR),
    INVALID_PARAMETERS("INVALID_PARAMETERS", "Invalid input parameter", HttpStatus.BAD_REQUEST, SeverityEnum.ERROR),
    USER_ALREADY_PRESENT("USER_ALREADY_PRESENT", "user already present", HttpStatus.CONFLICT, SeverityEnum.WARNING),
    NO_PERMISSION("AUTHORIZATION_ERROR", "The user has no permission", HttpStatus.FORBIDDEN, SeverityEnum.CRITICAL),
    SERVICE_UNAVAILABLE("SERVICE_UNAVAILABLE", "Service unavailable", HttpStatus.SERVICE_UNAVAILABLE, SeverityEnum.ERROR),
    GENERIC_ERROR("GENERIC_ERROR", "Generic Error", HttpStatus.INTERNAL_SERVER_ERROR, SeverityEnum.ERROR),
    BAD_REQUEST("BAD_REQUEST", "Bad request", HttpStatus.BAD_REQUEST, SeverityEnum.ERROR),
    ;
    private final Error error;
    private final HttpStatus httpStatus;

    GeneralErrorEnum(final String code, final String message, final HttpStatus httpStatus, final SeverityEnum severityEnum) {
        this.httpStatus = httpStatus;
        this.error = createError(code, message, severityEnum);
    }

    private Error createError(final String code, final String message, final SeverityEnum severityEnum) {
        Error errorObject = new Error();
        errorObject.setCode(code);
        errorObject.setMessage(message);
        errorObject.setSeverity(severityEnum);
        return errorObject;
    }

    /**
     * @return GeneralException
     */
    public <T extends GeneralException.GeneralExceptionBuilder> GeneralException buildGeneralException() {
        return new GeneralException(buildError(), getHttpStatus(new GeneralException()));
    }

    /**
     * Method to build .HttpStatus
     *
     * @return HttpStatus
     */
    private HttpStatus getHttpStatus(final Throwable throwable) {
        return Optional.ofNullable(throwable)
                .filter(GeneralException.class::isInstance)
                .map(GeneralException.class::cast)
                .map(GeneralException::getHttpStatus)
                .orElse(getHttpStatus());
    }

    /**
     * Method to build api error list
     *
     * @return Error
     */
    private Error buildError() {
        Error errorObj = new Error();
        errorObj.setCode(getError().getCode());
        errorObj.setMessage(getError().getMessage());
        errorObj.setSeverity(getError().getSeverity());
        return errorObj;
    }

}
