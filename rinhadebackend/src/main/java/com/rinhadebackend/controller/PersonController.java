package com.rinhadebackend.controller;

import com.rinhadebackend.dto.request.PersonRequest;
import com.rinhadebackend.dto.response.PersonResponse;
import com.rinhadebackend.exception.custom.EntityNotFoundException;
import com.rinhadebackend.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PersonController {


    private final PersonService personService;

    @PostMapping("/pessoas")
    public ResponseEntity<PersonResponse> createPerson(@RequestBody PersonRequest personRequest,
                                                       UriComponentsBuilder uriBuilder) {

        PersonResponse personResponse = personService.createPerson(personRequest);
        var uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(personResponse.id()).toUri();
        return ResponseEntity.created(uri).body(personResponse);

    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<PersonResponse> getPersonById(@PathVariable UUID id) throws EntityNotFoundException {

        PersonResponse personResponse = personService.getPersonById(id);
        return ResponseEntity.ok(personResponse);

    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<PersonResponse>> getPersonById(@RequestParam(name = "t") String searchTerm) {

        List<PersonResponse> persons = personService.getPersonsBySearchTerm(searchTerm);
        return ResponseEntity.ok(persons);

    }

    @GetMapping("/contagem-pessoas")
    public ResponseEntity<String> getPersonsCount() {

        return ResponseEntity.ok(String.valueOf(personService.getPersonsCount()));

    }

}
