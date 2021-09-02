package com.advocacy.advocacysystem.entrypoint.dto.lawyer;

import com.advocacy.advocacysystem.core.domain.Lawyer;
import com.advocacy.advocacysystem.core.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LawyerCreateDTO {


    @ApiModelProperty("Nome do Advogado")
    private String name;

    @ApiModelProperty("Cpf do Advogadp")
    private String cpf;

    @ApiModelProperty("Nome de Usuário")
    private String userName;

    @ApiModelProperty("Senha do Usuário")
    private String password;

    public Lawyer toLawyer() {
        var user = User.builder().user(userName.trim()).password(password.trim()).createdAt(LocalDateTime.now()).build();
        return Lawyer.builder()
                .name(name)
                .cpf(cpf)
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
