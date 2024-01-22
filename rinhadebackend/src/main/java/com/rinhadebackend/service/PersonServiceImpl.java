package com.rinhadebackend.service;

import com.rinhadebackend.dto.request.PersonRequest;
import com.rinhadebackend.dto.response.PersonResponse;
import com.rinhadebackend.exception.custom.EntityNotFoundException;
import com.rinhadebackend.model.Person;
import com.rinhadebackend.model.Stack;
import com.rinhadebackend.repository.PersonRepository;
import com.rinhadebackend.repository.StackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor

public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final StackRepository stackRepository;

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public PersonResponse createPerson(PersonRequest personRequest) {
        Person entity = personRequest.toPerson();

        Map<String, Stack> stackMap = new HashMap<>();

        for (Stack stack : entity.getStacks()) {

            Optional<Stack> currentStack = stackRepository.findByNameIgnoreCase(stack.getName());

            currentStack.ifPresentOrElse(
                    value -> stackMap.put(stack.getName(), value),
                    () -> stackMap.put(stack.getName(), stackRepository.save(stack))
            );
        }

        entity.getStacks().clear();
        entity.getStacks().addAll(stackMap.values());

        Person person = personRepository.save(entity);
        return PersonResponse.fromPerson(person);
    }


    @Override
    public PersonResponse getPersonById(UUID id) throws EntityNotFoundException {

        Optional<Person> person = personRepository.findById(id);

        if (person.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return PersonResponse.fromPerson(person.get());
    }

    @Override
    public List<PersonResponse> getPersonsBySearchTerm(String searchTerm) {
        List<Person> listOfPersons = personRepository.getPersonBySearchTerm("%" + searchTerm + "%");
        return listOfPersons.stream().map(PersonResponse::fromPerson).toList();
    }

    @Override
    public int getPersonsCount() {
        return personRepository.findAll().size();
    }
}
