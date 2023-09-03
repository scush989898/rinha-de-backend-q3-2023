package com.rinhadebackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity(name = "stacks")
@Table(name = "stacks")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "nome"})
@Builder
@NoArgsConstructor
public class Stack {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false, length = 32)
    private String nome;


    public static class StackAdapter {

        public static Set<Stack> fromSetStringToSetStack(Set<String> listOfStack) {

            Set<Stack> stack = new HashSet<Stack>();

            for (String newStack : listOfStack) {
                stack.add(

                        Stack.builder(
                        ).nome(newStack).build()
                );
            }
            return stack;

        }

        public static Set<String> fromSetStackToSetString(Set<Stack> setOfStack) {

            Set<String> stack = new HashSet<String>();

            for (Stack newStack : setOfStack) {
                stack.add(
                        newStack.getNome()
                );
            }
            return stack;
        }

    }


}


