package com.spring.knowhub.domain.enums.report;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ReportType {
    SPAM,
    HARASSMENT,
    INAPPROPRIATE,
    MISINFORMATION,
    COPYRIGHT_VIOLATION,
    OTHER;

    @JsonCreator
    public static ReportType fromString(String value) {
        if (value == null) {
            return null;
        }
        return ReportType.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
