package com.senai.model.justificativa;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.senai.util.LocalDateTimeAdapter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JustificativaDao {

    private List<Justificativa> justificativas;
    private final String FILE_PATH = "justificativas.json";
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    public JustificativaDao() {
        justificativas = carregar();
    }

    private List<Justificativa> carregar() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Justificativa>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void salvar(Justificativa justificativa) {
        justificativas.add(justificativa);
        salvarJson();
    }

    public void salvarJson() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(justificativas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Justificativa> listar() {
        return justificativas;
    }

    public void atualizar(Justificativa justificativa) {
        for (Justificativa j : justificativas) {
            if (j.getId() == justificativa.getId()) {
                j.setTipo(justificativa.getTipo());
                j.setDescricao(justificativa.getDescricao());
                j.setDataHoraJustificatida(justificativa.getDataHoraJustificatida());
                j.setQuantidadeDias(justificativa.getQuantidadeDias());
                j.setPrazoDeAceite(justificativa.getPrazoDeAceite());
                j.setAnexo(justificativa.getAnexo());
                j.setStatus(justificativa.getStatus());
                j.setCancelar(justificativa.isCancelar());
                salvarJson();
                break;
            }
        }
    }

    public boolean deletar(int id) {
        Iterator<Justificativa> iterator = justificativas.iterator();
        while (iterator.hasNext()) {
            Justificativa j = iterator.next();
            if (j.getId() == id) {
                iterator.remove();
                salvarJson();
                return true;
            }
        }
        return false;
    }
}



