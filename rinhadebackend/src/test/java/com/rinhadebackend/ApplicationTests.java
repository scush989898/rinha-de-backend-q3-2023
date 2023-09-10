package com.rinhadebackend;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rinhadebackend.dto.request.PersonRequest;
import com.rinhadebackend.dto.response.PersonResponse;
import com.rinhadebackend.model.Person;
import com.rinhadebackend.repository.PersonRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.rinhadebackend.mock.PersonMock;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.liquibase.enabled=false",
        "spring.flyway.enabled=false"
})
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationTests {


    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private PersonRepository personRepository;


    @Test
    @Order(1)
    void testShouldBeAbleToCreatePersons() throws Exception {

        for (PersonRequest currentPersonRequest : PersonMock.personRequestList) {

            MvcResult currentPersonResult = mockMvc.perform(
                            post("/pessoas")
                                    .contentType("application/json")
                                    .content(objectMapper.writeValueAsString(currentPersonRequest)))
                    .andExpect(status().isCreated()).andReturn();

            PersonResponse currentPersonResponse = objectMapper.readValue(
                    currentPersonResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                    PersonResponse.class
            );

            assertEquals("name", currentPersonRequest.name(), currentPersonResponse.name());
            assertEquals("nickName", currentPersonRequest.nickName(), currentPersonResponse.nickName());
            assertEquals("dateOfBirth", currentPersonRequest.dateOfBirth(), currentPersonResponse.dateOfBirth());
            assertEquals("stack", currentPersonRequest.stack(), currentPersonResponse.stack());
        }

        assertEquals("Size of the persons List",
                PersonMock.personRequestList.size(), personRepository.findAll().size());

    }


    @Test
    @Order(2)
    void testShouldNotBeAbleToCreateAPersonWhenNicknameIsAlreadyUsed() throws Exception {

        mockMvc.perform(
                        post("/pessoas")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(PersonMock.getRandomPersonRequest())))
                .andExpect(status().isUnprocessableEntity());


    }


    @Test
    @Order(3)
    void testShouldNotBeAbleToCreateAPersonWithInvalidNameFormat() throws Exception {

        mockMvc.perform(
                        post("/pessoas")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(PersonMock.personNameCantBeNull)))
                .andExpect(status().isUnprocessableEntity());


        mockMvc.perform(
                        post("/pessoas")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(PersonMock.personNameShouldBeAValidString)))
                .andExpect(status().isBadRequest());


    }


    @Test
    @Order(4)
    void testShouldNotBeAbleToCreateAPersonWithInvalidNickNameFormat() throws Exception {

        mockMvc.perform(
                        post("/pessoas")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(PersonMock.personNicknameCantBeNull)))
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
    @Order(5)
    void testShouldNotBeAbleToCreateAPersonWithInvalidStackFormat() throws Exception {

        mockMvc.perform(
                        post("/pessoas")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(PersonMock.personStacksShouldBeAValidStringArray)))
                .andExpect(status().isBadRequest());


    }


    @Test
    @Order(6)
    void testShouldBeAbleToRetrieveAPersonById() throws Exception {

        Person person = personRepository.findAll().get(0);

        MvcResult personResult = mockMvc.perform(
                        get("/pessoas/{id}", person.getId())
                                .contentType("application/json"))
                .andExpect(status().isOk()).andReturn();


        PersonResponse personResponse = objectMapper.readValue(
                personResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                PersonResponse.class
        );

        assertEquals("persons name", person.getName(), personResponse.name());
        assertEquals("persons nickName", person.getNickName(), personResponse.nickName());
        assertEquals("persons dateOfBirth", person.getDateOfBirth(), personResponse.dateOfBirth());
        assertEquals("persons id", person.getId().toString(), personResponse.id());

    }


    @Test
    @Order(7)
    void testShouldNotBeAbleToRetrieveAPersonWithNonRegisteredId() throws Exception {

        mockMvc.perform(
                        get("/pessoas/{id}", UUID.randomUUID().toString())
                                .contentType("application/json"))
                .andExpect(status().isNotFound());

    }

    @Test
    @Order(8)
    void testShouldBeAbleToRetrieveAPersonListBySearchTerm() throws Exception {

        MvcResult firstResult =
                mockMvc.perform(
                                get("/pessoas")
                                        .param("t", "node")
                                        .contentType("application/json"))
                        .andExpect(status().isOk()).andReturn();


        List<PersonResponse> firstResponse = objectMapper.readValue(
                firstResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<>() {
                });

        assertEquals("Size of the persons List", firstResponse.size(), 2);


        MvcResult secondResult =
                mockMvc.perform(
                                get("/pessoas")
                                        .param("t", "Python")
                                        .contentType("application/json"))
                        .andExpect(status().isOk()).andReturn();


        List<PersonResponse> secondResponse = objectMapper.readValue(
                secondResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<>() {
                });

        assertEquals("Size of the persons List", secondResponse.size(), 0);


        mockMvc.perform(
                        get("/pessoas")
                                .contentType("application/json"))
                .andExpect(status().isBadRequest()).andReturn();


    }


    @Test
    @Order(9)
    void testShouldBeAbleToGetPersonsCounter() throws Exception {

        MvcResult result = mockMvc.perform(
                        get("/contagem-pessoas")
                                .contentType("application/json"))
                .andExpect(status().isOk()).andReturn();


        String response =
                objectMapper.readValue(result.getResponse().
                        getContentAsString(StandardCharsets.UTF_8), String.class);


        assertEquals("Persons count", PersonMock.personRequestList.size(), Integer.parseInt(response));

    }


}
