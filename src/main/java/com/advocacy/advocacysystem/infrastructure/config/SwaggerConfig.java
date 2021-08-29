package com.advocacy.advocacysystem.infrastructure.config;

import com.advocacy.advocacysystem.entrypoint.dto.PageableDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.advocacy.advocacysystem.entrypoint.controller"))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(Pageable.class, PageableDTO.class);
    }
}
