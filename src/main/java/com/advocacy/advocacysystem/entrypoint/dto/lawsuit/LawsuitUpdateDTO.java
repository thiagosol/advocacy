package com.advocacy.advocacysystem.entrypoint.dto.lawsuit;

import com.advocacy.advocacysystem.core.domain.Lawsuit;
import com.advocacy.advocacysystem.core.domain.Lawyer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class LawsuitUpdateDTO {


    @ApiModelProperty("Número do Processo")
    private Long number;

    @ApiModelProperty("Descrição do Processo")
    private String description;

    @ApiModelProperty("Advogado do Processo")
    private Long lawyerId;

    public Lawsuit toLawsuit(Long id) {
        var lawsuit = Lawsuit.builder()
                .number(number)
                .description(description)
                .lawyer(new Lawyer(lawyerId))
                .build();
        lawsuit.setId(id);
        return lawsuit;
    }
}
