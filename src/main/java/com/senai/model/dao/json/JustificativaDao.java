package com.senai.model.dao.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.senai.model.Justificativa;
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

    public List<Justificativa> listarDoAluno(int idAluno) {
        return justificativas.stream().filter(j -> idAluno == j.getIdAluno()).toList();
    }

    public void atualizar(Justificativa justificativa) {
        for (int i = 0; i < justificativas.size(); i++) {
            if (justificativas.get(i).getId() == justificativa.getId()) {
                salvar(justificativa);
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

    public void atualizarDoAluno(Justificativa justificativa) {
        for (int i = 0; i < justificativas.size(); i++) {
            if (justificativas.get(i).getId() == justificativa.getId() && justificativas.get(i).getIdAluno() == justificativa.getIdAluno()) {
                salvar(justificativa);
                break;
            }
        }
    }

    public boolean deletarDoAluno(int id, int idAluno) {
        Iterator<Justificativa> iterator = justificativas.iterator();
        while (iterator.hasNext()) {
            Justificativa j = iterator.next();
            if (j.getId() == id && j.getIdAluno() == idAluno) {
                iterator.remove();
                salvarJson();
                return true;
            }
        }
        return false;
    }

    public void aceitar(int id, String status) {
        for (Justificativa j : justificativas) {
            if (j.getId() == id) {
                j.setStatus(status);
                salvarJson();
                break;
            }
        }
    }
}



