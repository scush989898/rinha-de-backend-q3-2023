package com.rinhadebackend.service;

import com.rinhadebackend.dto.request.PersonRequest;
import com.rinhadebackend.dto.response.PersonResponse;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    PersonResponse createPerson(PersonRequest personRequest);

    PersonResponse getPersonById(UUID id) throws Exception;

    List<PersonResponse> getPersonsBySearchTerm(String searchTerm);

    int getPersonsCount();


}
