package com.senai.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private List<Aluno> alunos;//armazena o objeto em aluno
    private final String FILE_PATH = "alunos.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private List<Aluno>carregar() { //Lê o arquivo "alunos.json" e converte os dados em uma lista
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Aluno>>() {//informa o Gson o tipo de dado
            }.getType(); //Vai pegar a lista de operadores e dizer para o JSON a estrutura de dados dessa lista,
            // e comparar e converter

            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    public AlunoDAO(){
        alunos=carregar();//Quando o AlunoDAO é criado, ele carrega os alunos já salvos no JSON para dentro da lista alunos.
    }
    public void salvar(Aluno aluno) {
        alunos.add(aluno);//adiciona um aluno a lista
        salvarJson();//grava a lista atualizada
    }
    public void salvarJson() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {//Converte a lista de alunos em JSON
            // e salva no arquivo alunos.json.
            gson.toJson(alunos, writer);//faz a conversão da lista em formato JSON.

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Aluno> listar() {
        return alunos;
    }



}
