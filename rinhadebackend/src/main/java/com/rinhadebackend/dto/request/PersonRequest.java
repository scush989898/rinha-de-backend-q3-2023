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

        if (!isPersonValid()) {
            throw new IllegalArgumentException();
        }

        Set<Stack> newStack = Stack.StackAdapter.fromSetStringToSetStack(this.stack);

        return Person.builder()
                .nickName(this.nickName)
                .name(this.name)
                .dateOfBirth(this.dateOfBirth)
                .stacks(newStack)
                .build();
    }

    public boolean isPersonValid() {
        return isPersonNameValid() && isPersonStacksValid();
    }

    public boolean isPersonNameValid() {
        try {
            Integer.parseInt(this.name);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public boolean isPersonStacksValid() {

        if (this.stack == null) {
            return true;
        }
        for (String item : this.stack) {
            try {
                Integer.parseInt(item);
                return false;
            } catch (NumberFormatException ignored) {
            }
        }
        return true;
    }

}