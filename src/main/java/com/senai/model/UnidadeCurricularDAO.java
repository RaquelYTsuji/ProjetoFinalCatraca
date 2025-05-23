package com.senai.model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
}

