package com.senai.controller;

import com.senai.model.AQV;
import com.senai.model.dao.json.AQVDAO;

import java.util.List;

public class AQVcontroller {

    private AQVDAO dao = new AQVDAO();


    public void adicionarAQV(int id, String nome, String login, String senha) {
        AQV aqv = new AQV(id, nome, login, senha);
        dao.criar(aqv);
    }


    public List<AQV> listarAQV() {
        return dao.listarTodos();
    }


    public AQV buscarAQV(int id) {
        return dao.buscarPorId(id);
    }



    public void atualizarAQV(int id, String nome, String login, String senha) {
        AQV aqv = new AQV(id, nome, login, senha);
        dao.atualizar(aqv);
    }


    public void deletar(int id) {
        dao.deletar(id);
    }

}
