package com.example.todolistapi.todolistapi.dto;

public record TodoResponse (
        Long id,
        String nome,
        String descricao,
        boolean realizada,
        int prioridade
) {
}
