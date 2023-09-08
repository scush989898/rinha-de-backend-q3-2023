package com.rinhadebackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity(name = "stacks")
@Table(name = "stacks")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
@Builder
@NoArgsConstructor
public class Stack {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false, length = 32)
    private String name;


    public static class StackAdapter {

        public static Set<Stack> fromSetStringToSetStack(Set<String> setOfStrings) {

            Set<Stack> stack = new HashSet<Stack>();

            if (setOfStrings == null) {
                return stack;
            }
            for (String currentStack : setOfStrings) {
                stack.add(Stack.builder().name(currentStack).build());
            }
            return stack;

        }

        public static Set<String> fromSetStackToSetString(Set<Stack> setOfStacks) {

            if (setOfStacks.isEmpty()) {
                return null;
            }
            Set<String> stack = new HashSet<String>();

            for (Stack currentStack : setOfStacks) {
                stack.add(currentStack.getName());
            }
            return stack;
        }

    }


}


