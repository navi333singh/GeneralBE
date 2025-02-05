package com.bynav.generalbe.exception.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Contains error details.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    private SeverityEnum severity;
    private String code;
    private String message;
    private String source;
    private String target;
    private List<Error> innerErrors;
}
