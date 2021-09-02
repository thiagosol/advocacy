package com.advocacy.advocacysystem.entrypoint.controller;

import com.advocacy.advocacysystem.core.domain.Lawyer;
import com.advocacy.advocacysystem.entrypoint.dto.user.UserLoginRequestDTO;
import com.advocacy.advocacysystem.entrypoint.dto.user.UserLoginResponseDTO;
import com.advocacy.advocacysystem.infrastructure.config.JwtTokenConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RestController("UserController")
@RequestMapping("/v1/users")
@Api("Usuários")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenConfig jwtTokenConfig;

    @ApiOperation(value = "Autenticação de usuário", response = Lawyer.class)
    @PostMapping("login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {
        try {

            var usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(userLoginRequestDTO.getUsername(), userLoginRequestDTO.getPassword());
            var authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            var userDetails = (UserDetails) authenticate.getPrincipal();

            /*return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtTokenConfig.generateAccessToken(user)
                    )
                    .body(userViewMapper.toUserView(user));*/

            return ResponseEntity.status(HttpStatus.CREATED).body(new UserLoginResponseDTO(jwtTokenConfig.generateToken(userDetails)));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
