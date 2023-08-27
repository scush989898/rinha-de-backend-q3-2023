package com.rinhadebackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import java.util.Set;
import java.util.UUID;

@Entity(name="pessoas")
@Table(name="pessoas")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Pessoa {

    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(unique = true, length = 32, nullable = false)
    private String apelido;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(nullable = false)
    @Embedded
    private Nascimento nascimento;

    @Column(nullable = true)
    @ManyToMany
    private Set<Stack> stacks;



}
