package com.advocacy.advocacysystem.entrypoint.dto.customer;

import com.advocacy.advocacysystem.core.domain.Contact;
import com.advocacy.advocacysystem.core.domain.enums.TypeContact;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ContactCreateDTO {

    @ApiModelProperty("Contato")
    private String value;

    @ApiModelProperty("Descrição")
    private String description;

    @ApiModelProperty("Tipo do contato")
    private TypeContact typeContact;

    public Contact toContact(){
        return new Contact(null, this.value, this.description, this.typeContact, null);
    }
}
