package com.senai.model.justificativa;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JustificativaDao {
    private static final String FILE_PATH = "justificativas.json"; // Caminho para o arquivo JSON

    // Metodo para salvar uma justificativa
    public void salvar(Justificativa justificativa) {
        List<Justificativa> justificativas = lerDoArquivo();

        // Se o ID for 0, atribui um novo ID (simulando o comportamento de um banco de dados)
        if (justificativa.getId() == 0) {
            int novoId = justificativas.size() + 1;  // Gera um novo ID incremental
            justificativa.setId(novoId);
        }

        justificativas.add(justificativa);
        escreverNoArquivo(justificativas);
    }

    // Metodo para buscar uma justificativa pelo ID
    public Justificativa buscarPorId(int id) {
        List<Justificativa> justificativas = lerDoArquivo();
        for (Justificativa justificativa : justificativas) {
            if (justificativa.getId() == id) {
                return justificativa;
            }
        }
        return null; // Não encontrado
    }

    // Metodo para listar todas as justificativas
    public List<Justificativa> listarTodas() {
        return lerDoArquivo();
    }

    // Metodo para deletar uma justificativa pelo ID
    public boolean deletar(int id) {
        List<Justificativa> justificativas = lerDoArquivo();
        Justificativa justificativa = buscarPorId(id);
        if (justificativa != null) {
            justificativas.remove(justificativa);
            escreverNoArquivo(justificativas);
            return true;
        }
        return false;
    }

    // Metodo para atualizar uma justificativa
    public boolean atualizar(Justificativa justificativa) {
        List<Justificativa> justificativas = lerDoArquivo();
        Justificativa existente = buscarPorId(justificativa.getId());
        if (existente != null) {
            existente.setTipo(justificativa.getTipo());
            existente.setDescricao(justificativa.getDescricao());
            existente.setStatus(justificativa.getStatus());
            existente.setCancelar(justificativa.isCancelar());
            escreverNoArquivo(justificativas);
            return true;
        }
        return false;
    }

    // Metodo para ler o arquivo JSON usando Gson
    private List<Justificativa> lerDoArquivo() {
        Gson gson = new Gson();
        File file = new File(FILE_PATH);

        try {
            if (file.exists()) {
                FileReader fileReader = new FileReader(file);
                Type type = new TypeToken<List<Justificativa>>(){}.getType();
                return gson.fromJson(fileReader, type);
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Metodo para escrever no arquivo JSON usando Gson
    private void escreverNoArquivo(List<Justificativa> justificativas) {
        Gson gson = new Gson();
        File file = new File(FILE_PATH);

        try {
            FileWriter fileWriter = new FileWriter(file);
            gson.toJson(justificativas, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

