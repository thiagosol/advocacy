package com.advocacy.advocacysystem.entrypoint.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class PageableDTO {

    @ApiParam(value = "Número de registros por pagina", example = "0")
    @Nullable
    private Integer size;

    @ApiParam(value = "Página de resultados (0..N)", example = "0")
    @Nullable
    private Integer page;

    @ApiParam(value = "Ordenação: propriedades(,asc|desc). Default ordenação é crescente.")
    @Nullable
    private String sort;
}