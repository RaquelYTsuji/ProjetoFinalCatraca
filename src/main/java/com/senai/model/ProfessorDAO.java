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
import java.util.Optional;

public class ProfessorDAO {
    private List<Professor> professores;
    private final String ARQUIVO = "professores.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private List<Professor> carregar() {
        try (FileReader reader = new FileReader(ARQUIVO)) {
            Type listType = new TypeToken<ArrayList<Professor>>() {
            }.getType();

            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public ProfessorDAO() {
       professores = carregar();
    }

    public void salvar(Professor professor) {
        professores.add(professor);//adiciona um aluno a lista
        salvarJson();//grava a lista atualizada
    }

    public void salvarJson() {
        try (FileWriter writer = new FileWriter(ARQUIVO)) {
            gson.toJson(professores, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Professor> listar() {
        return professores;
    }

    public boolean atualizar(Professor professor) {
        for (int i = 0; i < professores.size(); i++) {
            Professor p = professores.get(i);
            if (p.getIdProfessor() == professor.getIdProfessor()){
                professores.set(i, professor);
                salvarJson();
                return true;
            }
        }
        return false;
    }
    public boolean deletar(int id) {
        for (int i = 0; i < professores.size(); i++) {
            Professor p  = professores.get(i);
            if (p.getIdProfessor() == id) {
                professores.remove(i); // Remove o aluno da lista
                salvarJson(); // Atualiza o arquivo JSON
                return true; // Indica que o aluno foi removido com sucesso
            }
        }
        return false;
    }

    public Optional<Professor> buscarPorId(int id){
        return carregar().stream().filter(p -> p.getIdProfessor() == p.idProfessor).findFirst();
    }

    public Optional<Professor> buscarPorLogin(String login) {
        return professores.stream().filter(a -> a.getLogin().equals(login)).findFirst();
    }
}
