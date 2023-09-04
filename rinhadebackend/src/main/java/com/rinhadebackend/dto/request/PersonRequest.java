package com.rinhadebackend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rinhadebackend.model.Person;
import com.rinhadebackend.model.Stack;

import java.time.LocalDate;
import java.util.Set;


public record PersonRequest(
        @JsonProperty("apelido") String nickName,
        @JsonProperty("nome") String name,
        @JsonProperty("nascimento") LocalDate dateOfBirth,
        @JsonProperty("stack") Set<String> stack) {

    public Person toPerson() {


        Set<Stack> newstack = Stack.StackAdapter.fromSetStringToSetStack(stack);

        return Person.builder()
                .nickName(this.nickName)
                .name(this.name)
                .dateOfBirth(this.dateOfBirth)
                .stacks(newstack)
                .build();

    }


}