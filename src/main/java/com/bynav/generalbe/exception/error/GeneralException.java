package com.bynav.generalbe.exception.error;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneralException extends RuntimeException {
    private Error error;
    private HttpStatus httpStatus;
}
