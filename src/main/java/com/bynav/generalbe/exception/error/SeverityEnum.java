package com.bynav.generalbe.exception.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Severity levels.
 */
public enum SeverityEnum {
    CRITICAL("critical"),
    ERROR("error"),
    WARNING("warning"),
    INFO("info");

    private final String value;

    private SeverityEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    public String toString() {
        return this.value;
    }

    @JsonCreator
    public static SeverityEnum fromValue(String value) {
        for(SeverityEnum var4 : values()) {
            if (var4.value.equals(value) || var4.name().equals(value)) {
                return var4;
            }
        }

        throw new IllegalArgumentException("Unexpected severity value '" + value + "' (expecting lowercase)");
    }
}
