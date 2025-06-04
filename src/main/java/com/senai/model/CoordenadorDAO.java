package com.senai.model;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoordenadorDAO {
    private final String ARQUIVO = "coordenadores.json";
    private List<Coordenador> coordenadores = new ArrayList<>();

    public CoordenadorDAO() {
        carregar();
    }

    public void criar(Coordenador coordenador) {
        coordenadores.add(coordenador);
        salvar();
    }

    public List<Coordenador> listarTodos() {
        if (coordenadores.isEmpty()) {
            System.out.println("Não há coordenadores cadastrados.");
        }
        return coordenadores;
    }

    public Coordenador buscarPorId(int id) {
        for (Coordenador c : coordenadores) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public void atualizar(Coordenador coordenadorAtualizado) {
        for (int i = 0; i < coordenadores.size(); i++) {
            if (coordenadores.get(i).getId() == coordenadorAtualizado.getId()) {
                coordenadores.set(i, coordenadorAtualizado);
                salvar();
                return;
            }
        }
    }

    public void deletar(int id) {
        Coordenador c = buscarPorId(id);
        if (c != null) {
            coordenadores.remove(c);
            salvar();
            System.out.println("Coordenador deletado.");
        }
    }

    private void salvar() {
        try (FileWriter writer = new FileWriter(ARQUIVO)) {
            new Gson().toJson(coordenadores, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void carregar() {
        try (FileReader reader = new FileReader(ARQUIVO)) {
            coordenadores = new Gson().fromJson(reader, new TypeToken<List<Coordenador>>() {
            }.getType());
            if (coordenadores == null) {
                coordenadores = new ArrayList<>();
            }
        } catch (Exception e) {
            coordenadores = new ArrayList<>();
        }
    }

    public Optional<Coordenador> buscarPorLogin(String login) {
        return coordenadores.stream().filter(a -> a.getLogin().equals(login)).findFirst();
    }
}