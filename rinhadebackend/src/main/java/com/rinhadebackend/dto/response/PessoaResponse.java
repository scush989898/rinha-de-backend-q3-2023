package com.rinhadebackend.dto.response;

import com.rinhadebackend.model.Pessoa;
import com.rinhadebackend.model.Stack;

import java.time.LocalDate;
import java.util.Set;

public record PessoaResponse(String id, String apelido, String nome, LocalDate nascimento, Set<String> stack) {

    public static PessoaResponse fromPessoa(Pessoa pessoa){


        Set<String> newStack = Stack.StackAdapter.fromSetStackToSetString(pessoa.getStacks());


        return new PessoaResponse(

                    pessoa.getId().toString(),
                    pessoa.getApelido(),
                    pessoa.getNome(),
                    pessoa.getNascimento(),
                    newStack

            );

    }
}