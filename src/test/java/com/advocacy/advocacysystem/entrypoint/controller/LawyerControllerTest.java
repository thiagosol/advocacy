package com.advocacy.advocacysystem.entrypoint.controller;

import com.advocacy.advocacysystem.core.domain.Lawyer;
import com.advocacy.advocacysystem.entrypoint.dto.lawyer.LawyerCreateDTO;
import com.advocacy.advocacysystem.entrypoint.dto.lawyer.LawyerUpdateDTO;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@SpringBootTest(properties = {"spring.profiles.active=test"},
                webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LawyerControllerTest {

    private static final String USER_NAME_ADMIN = "admin";
    private static final String PASSWORD_ADMIN = "12345";

    private static final String NAME = "Advogado 1";
    private static final String NAME_UPDATE = "Advogado 1 Update";
    private static final String CPF = "12345678911";
    private static final String CPF_UPDATE = "12345678911";

    private static final String USER_NAME = "advogado1";
    private static final String USER_NAME_UPDATE = "advogado1up";

    private static final String PASSWORD = "12345";
    private static final String PASSWORD_UPDATE = "1234";

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
        LawyerCreateDTO lawyerCreateDTO = new LawyerCreateDTO(NAME, CPF, USER_NAME, PASSWORD);
        HttpEntity entity = new HttpEntity<>(lawyerCreateDTO, headers);
        ResponseEntity<Lawyer> responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/v1/lawyers"), HttpMethod.POST, entity, Lawyer.class);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody().getName().equals(NAME));
        assertTrue(responseEntity.getBody().getCpf().equals(CPF));
        assertTrue(responseEntity.getBody().getUser().getUsername().equals(USER_NAME));
        idCreated = responseEntity.getBody().getId();
    }

    /*@Test
    @Order(2)
    void updateWithOutUpdateUserTest() {
        login();
        LawyerUpdateDTO lawyerUpdateDTO = new LawyerUpdateDTO(NAME_UPDATE, CPF_UPDATE, false, false, null, null);
        HttpEntity entity = new HttpEntity<>(lawyerUpdateDTO, headers);
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/v1/lawyers/"+idCreated), HttpMethod.PUT, entity, Void.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }*/

    @Test
    @Order(3)
    void updateWithUpdateUserTest() {
        login();
        LawyerUpdateDTO lawyerUpdateDTO = new LawyerUpdateDTO(NAME_UPDATE, CPF_UPDATE, true, true, USER_NAME_UPDATE, PASSWORD_UPDATE);
        HttpEntity entity = new HttpEntity<>(lawyerUpdateDTO, headers);
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/v1/lawyers/"+idCreated), HttpMethod.PUT, entity, Void.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Order(4)
    void getByIdTest() throws JsonProcessingException {
        login();
        HttpEntity entity = new HttpEntity<>(null, headers);
        ResponseEntity<Lawyer> responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/v1/lawyers/" + idCreated), HttpMethod.GET, entity, Lawyer.class);
        assertNotNull(responseEntity.getBody());
        assertEquals(idCreated, responseEntity.getBody().getId());
    }

    @Test
    @Order(5)
    void getAllTest() throws JsonProcessingException {
        login();
        HttpEntity entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createURLWithPort(
                "/v1/lawyers"), HttpMethod.GET, entity, String.class);
        var page = objectMapper.readValue(responseEntity.getBody(), ObjectNode.class);
        var lawyers = (List<Lawyer>) objectMapper.readValue(page.get("content").toString(), ArrayList.class);
        assertNotNull(lawyers);
        assertFalse(lawyers.isEmpty());
        assertEquals(2, lawyers.size());
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