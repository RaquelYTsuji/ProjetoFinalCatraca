package com.senai.controller;

import com.senai.model.AQV;
import com.senai.model.dao.json.AQVdao;

import java.util.List;

public class AQVcontroller {
    private AQVdao dao = new AQVdao();

    public void adicionarAQV(int id, String nome, String email) {
        AQV aqv = new AQV(id, nome, email, "padrao123");
        dao.criar(aqv);
    }

    public List<AQV> listarAQV() {
        return dao.listarTodos();
    }

    public AQV buscarAQV(int id) {
        return dao.buscarPorId(id);
    }

    public void atualizarAQV(int id, String nome, String email) {
        AQV aqv = new AQV(id, nome, email, "padrao123");
        dao.atualizar(aqv);
    }

    public void deletarAQV(int id) {
        dao.deletar(id);
    }
}

