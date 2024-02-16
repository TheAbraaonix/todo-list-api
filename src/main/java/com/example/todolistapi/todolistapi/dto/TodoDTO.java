package com.example.todolistapi.todolistapi.dto;

public record TodoDTO(

        String nome,
        String descricao,
        boolean realizada,
        int prioridade

) {
}