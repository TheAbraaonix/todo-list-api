package com.example.todolistapi.todolistapi.dto;

public record TodoRequest(

        String nome,
        String descricao,
        boolean realizada,
        int prioridade

) {
}