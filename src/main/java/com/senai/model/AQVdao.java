package com.senai.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class AQVdao {
        private final String ARQUIVO = "aqvs.json";
        private List<AQV> aqvs = new ArrayList<>();

        public void AQVdao() {
            carregar();
        }

        public void criar(AQV aqv) {
            aqvs.add(aqv);
            salvar();
        }

        public List<AQV> listarTodos() {
            if (aqvs.isEmpty()) {
                System.out.println("Não há coordenadores cadastrados.");
            }
            return aqvs;
        }

        public AQV buscarPorId(int id) {
            for (AQV c : aqvs) {
                if (c.getId() == id) {
                    return c;
                }
            }
            return null;
        }

        public void atualizar(AQV aqvAtualizado) {
            for (int i = 0; i < aqvs.size(); i++) {
                if (aqvs.get(i).getId() == aqvAtualizado.getId()) {
                    aqvs.set(i, aqvAtualizado);
                    salvar();
                    return;
                }
            }
        }

        public void deletar(int id) {
            AQV c = buscarPorId(id);
            if (c != null) {
                aqvs.remove(c);
                salvar();
                System.out.println("AQV deletado.");
            }
        }

        private void salvar() {
            try (FileWriter writer = new FileWriter(ARQUIVO)) {
                new Gson().toJson(aqvs, writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void carregar() {
            try (FileReader reader = new FileReader(ARQUIVO)) {
                aqvs = new Gson().fromJson(reader, new TypeToken<List<AQV>>() {
                }.getType());
                if (aqvs == null) {
                    aqvs = new ArrayList<>();
                }
            } catch (Exception e) {
                aqvs = new ArrayList<>();
            }
        }
    }

