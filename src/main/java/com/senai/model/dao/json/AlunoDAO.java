package com.senai.model.dao.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.senai.model.Aluno;
import com.senai.util.LocalDateAdapter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class AlunoDAO {
    private List<Aluno> alunos;//armazena o objeto em aluno
    private final String ARQUIVO = "alunos.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

    private List<Aluno> carregar() { //Lê o arquivo "alunos.json" e converte os dados em uma lista
        try (FileReader reader = new FileReader(ARQUIVO)) {
            Type listType = new TypeToken<ArrayList<Aluno>>() {//informa o Gson o tipo de dado
            }.getType(); //Vai pegar a lista de operadores e dizer para o JSON a estrutura de dados dessa lista,
            // e comparar e converter

            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public AlunoDAO() {
        alunos = carregar();//Quando o AlunoDAO é criado, ele carrega os alunos já salvos no JSON para dentro da lista alunos.
    }

    public void salvar(Aluno aluno) {
        alunos.add(aluno);//adiciona um aluno a lista
        salvarJson();//grava a lista atualizada
    }

    public void salvarJson() {
        try (FileWriter writer = new FileWriter(ARQUIVO)) {//Converte a lista de alunos em JSON
            // e salva no arquivo alunos.json.
            gson.toJson(alunos, writer);//faz a conversão da lista em formato JSON.

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> listar() {
        return alunos;
    }

    public boolean atualizar(Aluno aluno) {
        for (int i = 0; i < alunos.size(); i++) {
            Aluno a = alunos.get(i);
            if (a.getId() == aluno.getId()) {
                alunos.set(i, aluno); // Atualiza o aluno na lista
                salvarJson(); // Grava a lista atualizada no arquivo JSON
                return true; // Indica que a atualização foi bem-sucedida
            }
        }
        return false;
    }
    public boolean deletar(int id) {
        for (int i = 0; i < alunos.size(); i++) {
            Aluno a = alunos.get(i);
            if (a.getId() == id) {
                alunos.remove(i); // Remove o aluno da lista
                salvarJson(); // Atualiza o arquivo JSON
                return true; // Indica que o aluno foi removido com sucesso
            }
        }
        return false;
    }

    public Optional<Aluno> buscarPorLogin(String login) {
        return alunos.stream().filter(a -> a.getLogin().equals(login)).findFirst();
    }

    public Optional<Aluno> buscarPorId(int id) {
        return alunos.stream().filter(a -> a.getId() == id).findFirst();
    }

    public Optional<Aluno> buscarPorIdAcesso(String idAcesso) {
        return alunos.stream().filter(a -> idAcesso.equals(a.getIdAcesso())).findFirst();
    }

}

