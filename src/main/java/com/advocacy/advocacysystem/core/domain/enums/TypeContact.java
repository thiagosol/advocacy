package com.advocacy.advocacysystem.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TypeContact {

    TELEPHONE("Telefone"),
    EMAIL("E-mail");

    private final String label;
}
