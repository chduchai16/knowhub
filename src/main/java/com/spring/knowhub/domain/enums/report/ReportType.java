package com.spring.knowhub.domain.enums.report;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ReportType {
    SPAM, // spam
    HARASSMENT, // quấy rối
    INAPPROPRIATE, // không phù hợp
    MISINFORMATION, // sai lệch thông tin
    COPYRIGHT_VIOLATION, // vi phạm bản quyền
    OTHER; // khác

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
