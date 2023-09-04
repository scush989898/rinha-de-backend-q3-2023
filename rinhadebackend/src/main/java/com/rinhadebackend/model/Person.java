package com.rinhadebackend.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity(name="persons")
@Table(name="persons")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Builder
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, length = 32, nullable = false)
    private String nickName;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column(nullable = true)
    @ManyToMany
    private Set<Stack> stacks;

}
