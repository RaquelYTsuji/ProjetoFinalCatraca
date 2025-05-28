package com.senai.model;

public class AQV extends Usuario {
    private int id;

    public AQV(int id, String nome, String login, String senha) {
        super(nome, login, senha);
        this.id = id;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

