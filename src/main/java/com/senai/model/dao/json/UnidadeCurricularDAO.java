package com.senai.model.dao.json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.senai.model.UnidadeCurricular;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UnidadeCurricularDAO {
    //Final, já que não ocorrerão alterações de variáveis
    private final String caminhoArquivo = "unidadesCurriculares.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    //Aqui ele cria o caminho/procura se já existe
    public UnidadeCurricularDAO() {
        File file = new File(caminhoArquivo);
        if (!file.exists()) salvarUC(new ArrayList<>());
    }

    public List<UnidadeCurricular> carregarUC() {
        try (Reader reader = new FileReader(caminhoArquivo)) {
            Type tipoLista = new TypeToken<List<UnidadeCurricular>>() {}.getType();
            return gson.fromJson(reader, tipoLista);
        } catch (IOException e) {
            System.out.println("Erro ao carregar as UCs: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    //Aqui ele salva as informações que o usuário passar
    public boolean salvarUC(List<UnidadeCurricular> listaUC) {
        try (Writer writer = new FileWriter(caminhoArquivo)) {
            gson.toJson(listaUC, writer);
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao salvar as informações: " + e.getMessage()); //mensagem caso não for possivel salvar
        }
        return false;
    }

    public UnidadeCurricular procurar(int id, List<UnidadeCurricular> listaUC){
        UnidadeCurricular unidadeCurricular = null;
        for (UnidadeCurricular u : listaUC) {
            if (u.getId() == id) {
                unidadeCurricular = new UnidadeCurricular(u.getId(), u.getNome(), u.getDisciplina(), u.getIdProfessor(), u.getCargaHoraria());
                break;
            }
        }
        return unidadeCurricular;
    }

    public List<UnidadeCurricular> listarUC() {
        return carregarUC(); // Retorna a lista de unidades curriculares carregada do arquivo
    }

}

