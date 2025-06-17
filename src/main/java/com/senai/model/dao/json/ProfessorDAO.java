package com.senai.model.dao.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.senai.model.Professor;

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

    // Carregar lista de professores do arquivo JSON
    private List<Professor> carregar() {
        try (FileReader reader = new FileReader(ARQUIVO)) {
            Type listType = new TypeToken<ArrayList<Professor>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            // Se ocorrer erro ao carregar, retorna uma lista vazia
            return new ArrayList<>();
        }
    }

    // Construtor: carrega a lista de professores ao inicializar a classe
    public ProfessorDAO() {
        professores = carregar();
    }

    // Método para salvar um novo professor
    public void salvar(Professor professor) {
        professores.add(professor);  // Adiciona o professor à lista
        salvarJson();  // Salva a lista no arquivo JSON
    }

    // Método para atualizar o arquivo JSON
    public void salvarJson() {
        try (FileWriter writer = new FileWriter(ARQUIVO)) {
            gson.toJson(professores, writer);
        } catch (IOException e) {
            e.printStackTrace();  // Log do erro
        }
    }

    // Método para listar todos os professores
    public List<Professor> listar() {
        return professores;
    }

    // Método para atualizar um professor existente
    public boolean atualizar(Professor professor) {
        for (int i = 0; i < professores.size(); i++) {
            Professor p = professores.get(i);
            if (p.getId() == professor.getId()) {
                professores.set(i, professor);  // Substitui o professor existente
                salvarJson();  // Atualiza o arquivo JSON
                return true;  // Retorna true indicando que o professor foi atualizado
            }
        }
        return false;  // Retorna false se o professor não foi encontrado
    }

    // Método para deletar um professor pelo ID
    public boolean deletar(int id) {
        for (int i = 0; i < professores.size(); i++) {
            Professor p = professores.get(i);
            if (p.getId() == id) {
                professores.remove(i);  // Remove o professor da lista
                salvarJson();  // Atualiza o arquivo JSON
                return true;  // Retorna true indicando que o professor foi deletado
            }
        }
        return false;  // Retorna false se o professor não foi encontrado
    }



    public Optional<Professor> buscarPorLogin(String login) {
        return professores.stream().filter(p -> p.getLogin().equals(login)).findFirst();
    }

    public Optional<Professor> buscarPorId(int id) {
        return carregar().stream().filter(p -> p.getId() == id).findFirst();
    }

    public Professor procurar(int id){
        Professor professor = null;
        for (Professor p : professores) {
            if (p.getId() == id) {
                professor = new Professor(p.getNome(), p.getLogin(), p.getSenha(), p.getId(), p.getUnidadeCurricular());
                break;
            }
        }
        return professor;
    }
}
