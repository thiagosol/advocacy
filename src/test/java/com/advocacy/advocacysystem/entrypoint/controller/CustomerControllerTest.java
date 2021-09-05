package com.advocacy.advocacysystem.entrypoint.controller;

import com.advocacy.advocacysystem.core.domain.Customer;
import com.advocacy.advocacysystem.core.domain.enums.TypeContact;
import com.advocacy.advocacysystem.entrypoint.dto.customer.ContactCreateDTO;
import com.advocacy.advocacysystem.entrypoint.dto.customer.CustomerCreateDTO;
import com.advocacy.advocacysystem.entrypoint.dto.customer.CustomerUpdateDTO;
import com.advocacy.advocacysystem.entrypoint.dto.user.UserLoginRequestDTO;
import com.advocacy.advocacysystem.entrypoint.dto.user.UserLoginResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@SpringBootTest(properties = {"spring.profiles.active=test"},
                webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerControllerTest {

    private static final String USER_NAME_ADMIN = "admin";
    private static final String PASSWORD_ADMIN = "12345";

    private static final String NAME = "Cliente 1";
    private static final String CPF_CNPJ = "00000000001";
    private static final Set<ContactCreateDTO> CONTACTS = Set.of(new ContactCreateDTO("email@email.com", "Email", TypeContact.EMAIL));

    private static final String NAME_UPDATE = "Cliente 1 Update";
    private static final String CPF_CNPJ_UPDATE = "00000000002";

    private static final String LOCAL_HOST = "localhost";


    @LocalServerPort
    private int port;
    private TestRestTemplate testRestTemplate = new TestRestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    private static HttpHeaders headers = new HttpHeaders();
    private static Long idCreated;

    @Test
    @Order(1)
    void createTest() {
        login();
        CustomerCreateDTO customerCreateDTO = new CustomerCreateDTO(NAME, CPF_CNPJ, CONTACTS);
        HttpEntity entity = new HttpEntity<>(customerCreateDTO, headers);
        ResponseEntity<Customer> responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/v1/customers"), HttpMethod.POST, entity, Customer.class);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody().getName().equals(NAME));
        assertTrue(responseEntity.getBody().getCpfCnpj().equals(CPF_CNPJ));
        assertFalse(responseEntity.getBody().getContacts().isEmpty());
        idCreated = responseEntity.getBody().getId();
    }

    @Test
    @Order(2)
    void updateTest() {
        login();
        CustomerUpdateDTO customerUpdateDTO = new CustomerUpdateDTO(NAME_UPDATE, CPF_CNPJ_UPDATE);
        HttpEntity entity = new HttpEntity<>(customerUpdateDTO, headers);
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/v1/customers/"+idCreated), HttpMethod.PUT, entity, Void.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Order(3)
    void getByIdTest() throws JsonProcessingException {
        login();
        HttpEntity entity = new HttpEntity<>(null, headers);
        ResponseEntity<Customer> responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/v1/customers/" + idCreated), HttpMethod.GET, entity, Customer.class);
        assertNotNull(responseEntity.getBody());
        assertEquals(idCreated, responseEntity.getBody().getId());
    }

    @Test
    @Order(4)
    void getAllTest() throws JsonProcessingException {
        login();
        HttpEntity entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/v1/customers"), HttpMethod.GET, entity, String.class);
        var page = objectMapper.readValue(responseEntity.getBody(), ObjectNode.class);
        var customers = (List<Customer>) objectMapper.readValue(page.get("content").toString(), ArrayList.class);
        assertNotNull(customers);
        assertFalse(customers.isEmpty());
        assertEquals(1, customers.size());
    }

    @Test
    @Order(5)
    void addContactsTest() {
        login();
        ContactCreateDTO  contactCreateDTO = new ContactCreateDTO("11999999999", "WhatsApp", TypeContact.TELEPHONE);
        HttpEntity entity = new HttpEntity<>(contactCreateDTO, headers);
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/v1/customers/"+idCreated+"/add-contact"), HttpMethod.POST, entity, Void.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    private String createURLWithPort(String uri)
    {
        return String.format("http://%s:%s%s", LOCAL_HOST, port, uri);
    }

    private void login() {
        if(!headers.containsKey("Authorization")){
            UserLoginRequestDTO userLoginRequestDTO = new UserLoginRequestDTO(USER_NAME_ADMIN, PASSWORD_ADMIN);
            HttpEntity entity = new HttpEntity<>(userLoginRequestDTO, headers);
            ResponseEntity<UserLoginResponseDTO> responseEntity = testRestTemplate.exchange(createURLWithPort(
                    "/v1/users/login"), HttpMethod.POST, entity, UserLoginResponseDTO.class);
            assertNotNull(responseEntity.getBody().getToken());
            this.headers.add("Authorization", responseEntity.getBody().getToken());
        }
    }
}