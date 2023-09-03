package com.rinhadebackend.dto.request;

import com.rinhadebackend.model.Pessoa;
import com.rinhadebackend.model.Stack;

import java.time.LocalDate;
import java.util.Set;


public record PessoaRequest(String apelido, String nome, LocalDate nascimento, Set<String> stack) {

    public Pessoa toPessoa() {


        Set<Stack> newstack = Stack.StackAdapter.fromSetStringToSetStack(stack);


        return Pessoa.builder()
                .apelido(this.apelido)
                .nome(this.nome)
                .nascimento(this.nascimento)
                .stacks(newstack)
                .build();

    }


}