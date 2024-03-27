package com.example.todolistapi.todolistapi.entity;

import com.example.todolistapi.todolistapi.dto.TodoRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    private boolean realizada;
    private int prioridade;

    public Todo() {
    }

    public Todo(long id, String nome, String descricao, boolean realizada, int prioridade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.realizada = realizada;
        this.prioridade = prioridade;
    }

    public Todo(String nome, String descricao, boolean realizada, int prioridade) {
        this.nome = nome;
        this.descricao = descricao;
        this.realizada = realizada;
        this.prioridade = prioridade;
    }

    public Todo(TodoRequest dto) {
        this.nome = dto.nome();
        this.descricao = dto.descricao();
        this.realizada = dto.realizada();
        this.prioridade = dto.prioridade();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getRealizada() {
        return realizada;
    }

    public void setRealizada(boolean isRealizada) {
        this.realizada = isRealizada;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
}
