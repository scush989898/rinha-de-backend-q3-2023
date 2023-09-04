package com.rinhadebackend.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rinhadebackend.model.Person;
import com.rinhadebackend.model.Stack;

import java.time.LocalDate;
import java.util.Set;

public record PersonResponse(
        @JsonProperty("id") String id,
        @JsonProperty("apelido") String nickName,
        @JsonProperty("nome") String name,
        @JsonProperty("nascimento") LocalDate dateOfBirth,
        @JsonProperty("stack") Set<String> stack) {

    public static PersonResponse fromPerson(Person person) {


        Set<String> newStack = Stack.StackAdapter.fromSetStackToSetString(person.getStacks());


        return new PersonResponse(

                person.getId().toString(),
                person.getNickName(),
                person.getName(),
                person.getDateOfBirth(),
                newStack

        );

    }
}