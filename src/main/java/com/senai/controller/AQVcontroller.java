package com.senai.controller;

import com.senai.model.AQV;
import com.senai.model.dao.json.AQVdao;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class AQVcontroller {

    private AQVdao dao = new AQVdao();


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


    public void verificarEAvisarSeAtrasado(String nomeAluno, LocalTime horarioChegada, LocalTime horarioLimite, AQV aqv) {
        dao.verificarEAvisarSeAtrasado(nomeAluno, horarioChegada, horarioLimite, aqv);
    }
}
