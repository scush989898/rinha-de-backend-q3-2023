package com.rinhadebackend.repository;

import com.rinhadebackend.model.Stack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StackRepository extends JpaRepository<Stack, UUID> {
}