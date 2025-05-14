package com.senai.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class AlunoDAO {
    private List<Aluno> alunos;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public AlunoDAO(){
        alunos = pedirEntrada;
    }
    public void criarOcorrência(){

    }
}
