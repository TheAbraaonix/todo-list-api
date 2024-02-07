package com.example.todolistapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todolistapi.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
