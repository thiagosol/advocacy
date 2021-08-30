package com.advocacy.advocacysystem.entrypoint.dto.lawsuit;

import com.advocacy.advocacysystem.core.domain.Lawsuit;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class LawsuitUpdateDTO {


    @ApiModelProperty("Número do Processo")
    private Long number;

    @ApiModelProperty("Descrição do Processo")
    private String description;

    public Lawsuit toLawsuit(Long id) {
        return new Lawsuit(id, number, description, null, null);
    }
}
