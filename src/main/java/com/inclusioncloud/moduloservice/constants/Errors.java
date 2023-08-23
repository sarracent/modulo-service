package com.inclusioncloud.moduloservice.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Errors {
    ERROR_TECHNICAL_EXCEPTION("200101", "TechnicalException %s");

    private final String code;
    private final String message;
}
