package com.bynav.generalbe.exception;

import com.bynav.generalbe.exception.error.GeneralError;
import com.bynav.generalbe.exception.error.GeneralErrorEnum;
import com.bynav.generalbe.exception.error.GeneralException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionMapper extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    @Order(Ordered.LOWEST_PRECEDENCE)
    public ResponseEntity<Object> handleException(Exception exception) {
        GeneralException ex = GeneralErrorEnum.GENERIC_ERROR.buildGeneralException();
        return ResponseEntity.status(ex.getHttpStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(GeneralError.builder()
                        .error(ex.getError())
                        .build());
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<GeneralError> handleGeneralExcepton(
            GeneralException ex) {
        return ResponseEntity.status(ex.getHttpStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(GeneralError.builder()
                        .error(ex.getError())
                        .build());
    }

}