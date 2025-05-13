package com.senai.model;

public class Coordenador extends Usuario implements Admin{
    int IdCoordenador;

    public Coordenador(String nome, String login, String senha) {
        super(nome, login, senha);
        this.IdCoordenador =IdCoordenador ;
    }
}
