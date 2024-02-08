package com.example.todolistapi.todolistapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String descricao;
    private boolean isRealizada;
    private int prioridade;

    public Todo() {
    }

    public Todo(long id, String nome, String descricao, boolean isRealizada, int prioridade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.isRealizada = isRealizada;
        this.prioridade = prioridade;
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

    public boolean isRealizada() {
        return isRealizada;
    }

    public void setRealizada(boolean isRealizada) {
        this.isRealizada = isRealizada;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
}
