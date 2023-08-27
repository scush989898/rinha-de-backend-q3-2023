package com.rinhadebackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import java.util.UUID;

@Entity(name="stacks")
@Table(name="stacks")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Stack {

    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(unique = true, nullable = false, length = 32)
    private String nome;


}
