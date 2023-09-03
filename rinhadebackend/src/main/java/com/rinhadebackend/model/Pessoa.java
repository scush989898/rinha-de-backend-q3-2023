package com.rinhadebackend.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity(name="pessoas")
@Table(name="pessoas")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Builder
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, length = 32, nullable = false)
    private String apelido;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nascimento;

    @Column(nullable = true)
    @ManyToMany
    private Set<Stack> stacks;

}
