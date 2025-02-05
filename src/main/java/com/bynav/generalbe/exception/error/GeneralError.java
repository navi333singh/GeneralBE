package com.bynav.generalbe.exception.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Contains error data.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralError {
    private Error error;
}