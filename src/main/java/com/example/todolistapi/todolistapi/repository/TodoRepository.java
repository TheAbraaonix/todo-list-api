package com.example.todolistapi.todolistapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todolistapi.todolistapi.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
