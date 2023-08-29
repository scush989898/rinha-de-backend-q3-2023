package com.rinhadebackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Entity(name="stacks")
@Table(name="stacks")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Stack {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false, length = 32)
    private String nome;


}
