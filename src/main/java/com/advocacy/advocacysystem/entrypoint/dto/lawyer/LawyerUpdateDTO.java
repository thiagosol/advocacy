package com.advocacy.advocacysystem.entrypoint.dto.lawyer;

import com.advocacy.advocacysystem.core.domain.Lawyer;
import com.advocacy.advocacysystem.core.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class LawyerUpdateDTO {

    @ApiModelProperty("Nome do Advogado")
    private String name;

    @ApiModelProperty("Cpf do Advogadp")
    private String cpf;

    @ApiModelProperty("Se existe alteração no nome de usuário, default = false")
    private boolean flChangeUserName;

    @ApiModelProperty("Se existe alteração na senha do usuário, default = false")
    private boolean flChangeUserPassword;

    @ApiModelProperty("Novo Nome de Usuário, obrigatório se 'flChangeUserName = true'")
    private String userName;

    @ApiModelProperty("Nova Senha do Usuário, obrigatório se 'flChangeUserPassword = true'")
    private String password;

    public Lawyer toLawyer(Long id) {
        User user = new User();

        var lawyer = Lawyer.builder()
                .name(name)
                .cpf(cpf)
                .createdAt(LocalDateTime.now())
                .build();
        lawyer.setId(id);

        if(flChangeUserName) user.setUser(userName.trim());

        if(flChangeUserPassword) user.setPassword(password.trim());

        if(flChangeUserName || flChangeUserPassword) lawyer.setUser(user);

        return lawyer;
    }
}
