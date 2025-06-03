package com.senai.model.dao.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.senai.model.Aluno;
import com.senai.model.Ocorrencia;
import com.senai.util.LocalDateTimeAdapter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class OcorrenciaDAO {
    private List<Ocorrencia> ocorrencias;
    private final String FILE_PATH = "ocorrencias.json";
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();;

    private List<Ocorrencia> carregar() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Ocorrencia>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public OcorrenciaDAO() {
        ocorrencias = carregar();
    }

    public void salvar(Ocorrencia ocorrencia) {
        ocorrencias.add(ocorrencia);
        salvarJson();
    }

    public void salvarJson() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(ocorrencias, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Ocorrencia> listar() {
        return ocorrencias;
    }

    public void atualizar(Ocorrencia ocorrencia) {
        ocorrencias.forEach(o -> {
            if (o.getId() == ocorrencia.getId()) {
                o.setTipo(ocorrencia.getTipo());
                o.setDescricao(ocorrencia.getDescricao());
                o.setDataHora(ocorrencia.getDataHora());
                salvarJson();
            }
        });
    }

    public boolean deletar(int id) {
        Iterator<Ocorrencia> iterator = ocorrencias.iterator();
        while (iterator.hasNext()) {
            Ocorrencia o = iterator.next();
            if (o.getId() == id) {
                iterator.remove();
                salvarJson();
                return true;
            }
        }
        return false;
    }
    public Optional<Ocorrencia> buscarPorStatusAguardando(String status) {
        return ocorrencias.stream().filter(o -> status.equals(o.getStatus())).findFirst();
    }

}



