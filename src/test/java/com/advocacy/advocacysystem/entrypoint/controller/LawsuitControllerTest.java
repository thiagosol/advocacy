package com.advocacy.advocacysystem.entrypoint.controller;

import com.advocacy.advocacysystem.core.domain.Customer;
import com.advocacy.advocacysystem.core.domain.Lawsuit;
import com.advocacy.advocacysystem.core.domain.Lawyer;
import com.advocacy.advocacysystem.entrypoint.dto.lawsuit.LawsuitCreateDTO;
import com.advocacy.advocacysystem.entrypoint.dto.lawsuit.LawsuitUpdateDTO;
import com.advocacy.advocacysystem.entrypoint.dto.user.UserLoginRequestDTO;
import com.advocacy.advocacysystem.entrypoint.dto.user.UserLoginResponseDTO;
import com.advocacy.advocacysystem.infrastructure.repository.CustomerRepository;
import com.advocacy.advocacysystem.infrastructure.repository.LawyerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Objects;
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
class LawsuitControllerTest {

    private static final String USER_NAME_ADMIN = "admin";
    private static final String PASSWORD_ADMIN = "12345";

    private static final String NAME = "Cliente 1";
    private static final String CPF_CNPJ = "00000000001";

    private static final String NAME_LAWYER = "Advogado 1";
    private static final String CPF_LAWYER = "00000000002";

    private static final Long NUMBER = 123l;
    private static final String DESCRIPTION = "Descrição";

    private static final String LOCAL_HOST = "localhost";

    @LocalServerPort
    private int port;
    private TestRestTemplate testRestTemplate = new TestRestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LawyerRepository lawyerRepository;

    private static HttpHeaders headers = new HttpHeaders();
    private static Long idCreated;
    private static Customer customerCreated;
    private static Customer customerAddCreated;
    private static Lawyer lawyerCreated;

    @BeforeEach
    void setUp(){
        if(Objects.isNull(customerCreated)){
            customerCreated = new Customer();
            customerCreated.setName(NAME);
            customerCreated.setCpfCnpj(CPF_CNPJ);
            customerRepository.save(customerCreated);
        }

        if(Objects.isNull(customerAddCreated)){
            customerAddCreated = new Customer();
            customerAddCreated.setName(NAME);
            customerAddCreated.setCpfCnpj(CPF_CNPJ);
            customerRepository.save(customerAddCreated);
        }

        if(Objects.isNull(lawyerCreated)){
            lawyerCreated = new Lawyer();
            lawyerCreated.setName(NAME_LAWYER);
            lawyerCreated.setCpf(CPF_LAWYER);
            lawyerRepository.save(lawyerCreated);
        }
    }

    @Test
    @Order(1)
    void createTest() {
        login();
        LawsuitCreateDTO lawsuitCreateDTO = new LawsuitCreateDTO(NUMBER, DESCRIPTION, lawyerCreated.getId(), Set.of(customerCreated.getId()));
        HttpEntity entity = new HttpEntity<>(lawsuitCreateDTO, headers);
        ResponseEntity<Lawsuit> responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/v1/lawsuits"), HttpMethod.POST, entity, Lawsuit.class);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody().getNumber().equals(NUMBER));
        assertTrue(responseEntity.getBody().getDescription().equals(DESCRIPTION));
        idCreated = responseEntity.getBody().getId();
    }

    @Test
    @Order(2)
    void updateTest() {
        login();
        LawsuitUpdateDTO lawsuitUpdateDTO = new LawsuitUpdateDTO(1234l, "Derscrição Update", lawyerCreated.getId());
        HttpEntity entity = new HttpEntity<>(lawsuitUpdateDTO, headers);
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/v1/lawsuits/"+idCreated), HttpMethod.PUT, entity, Void.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Order(3)
    void getByIdTest() throws JsonProcessingException {
        login();
        HttpEntity entity = new HttpEntity<>(null, headers);
        ResponseEntity<Lawsuit> responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/v1/lawsuits/" + idCreated), HttpMethod.GET, entity, Lawsuit.class);
        assertNotNull(responseEntity.getBody());
        assertEquals(idCreated, responseEntity.getBody().getId());
    }

    @Test
    @Order(4)
    void getAllTest() throws JsonProcessingException {
        login();
        HttpEntity entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/v1/lawsuits"), HttpMethod.GET, entity, String.class);
        var page = objectMapper.readValue(responseEntity.getBody(), ObjectNode.class);
        var lawsuits = (List<Lawsuit>) objectMapper.readValue(page.get("content").toString(), ArrayList.class);
        assertNotNull(lawsuits);
        assertFalse(lawsuits.isEmpty());
        assertEquals(1, lawsuits.size());
    }

    @Test
    @Order(5)
    void addCustomersTest() throws JsonProcessingException {
        login();
        HttpEntity entity = new HttpEntity<>(Set.of(customerAddCreated.getId()), headers);
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/v1/lawsuits/"+idCreated+"/add-customers"), HttpMethod.POST, entity, Void.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Order(6)
    void getAllLawsuitsByCustomerTest() throws JsonProcessingException {
        login();
        HttpEntity entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/v1/lawsuits/customer/"+customerAddCreated.getId()), HttpMethod.GET, entity, String.class);
        var page = objectMapper.readValue(responseEntity.getBody(), ObjectNode.class);
        var lawsuits = (List<Lawsuit>) objectMapper.readValue(page.get("content").toString(), ArrayList.class);
        assertNotNull(lawsuits);
        assertFalse(lawsuits.isEmpty());
        assertEquals(1, lawsuits.size());
    }

    @Test
    @Order(7)
    void removeCustomersTest() throws JsonProcessingException {
        login();
        HttpEntity entity = new HttpEntity<>(Set.of(customerAddCreated.getId()), headers);
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/v1/lawsuits/"+idCreated+"/remove-customers"), HttpMethod.POST, entity, Void.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Order(8)
    void getAllLawsuitsByCustomerVerifyRemoveTest() throws JsonProcessingException {
        login();
        HttpEntity entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/v1/lawsuits/customer/"+customerAddCreated.getId()), HttpMethod.GET, entity, String.class);
        var page = objectMapper.readValue(responseEntity.getBody(), ObjectNode.class);
        var lawsuits = (List<Lawsuit>) objectMapper.readValue(page.get("content").toString(), ArrayList.class);
        assertNotNull(lawsuits);
        assertTrue(lawsuits.isEmpty());
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