package com.advocacy.advocacysystem.entrypoint.dto.lawyer;

import com.advocacy.advocacysystem.core.domain.Lawyer;
import com.advocacy.advocacysystem.core.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class LawyerCreateDTO {


    @ApiModelProperty("Nome do Advogado")
    private String name;

    @ApiModelProperty("Cpf do Advogadp")
    private String cpf;

    @ApiModelProperty("Nome de Usuário")
    private String userName;

    @ApiModelProperty("Senha do Usuário")
    private String password;

    public Lawyer toLawyer(PasswordEncoder passwordEncoder) {
        var user = new User(userName, password, passwordEncoder);
        return Lawyer.builder()
                .name(name)
                .cpf(cpf)
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
