package com.senai.controller;
import com.senai.model.Coordenador;
import com.senai.model.dao.json.CoordenadorDAO;
import com.senai.websocket.WebSocketClienteConsole;

import java.util.List;

public class CoordenadorController {

        private CoordenadorDAO dao = new CoordenadorDAO();

        public void adicionarCoordenador(int id, String nome, String login, String senha) {
            Coordenador coordenador = new Coordenador(id, nome, login, senha);
            dao.criar(coordenador);
        }

        public List<Coordenador> listarCoordenadores() {
            return dao.listarTodos();
        }

        public Coordenador buscarCoordenador(int id) {
            return dao.buscarPorId(id);
        }

        public void atualizarCoordenador(int id, String nome, String login,  String senha) {
            Coordenador coordenador = new Coordenador(id, nome, login, senha);
            dao.atualizar(coordenador);
        }

        public void deletarCoordenador(int id) {
            dao.deletar(id);
        }
}