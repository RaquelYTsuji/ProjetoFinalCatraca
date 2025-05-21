package com.senai.model;
import java.util.List;

public class CoordenadorController {

        private CoordenadorDAO dao = new CoordenadorDAO();

        public void adicionarCoordenador(int id, String nome, String email) {
            Coordenador coordenador = new Coordenador(id, nome, email, "padrao123");
            dao.criar(coordenador);
        }

        public List<Coordenador> listarCoordenadores() {
            return dao.listarTodos();
        }

        public Coordenador buscarCoordenador(int id) {
            return dao.buscarPorId(id);
        }

        public void atualizarCoordenador(int id, String nome, String email) {
            Coordenador coordenador = new Coordenador(id, nome, email, "padrao123");
            dao.atualizar(coordenador);
        }

        public void deletarCoordenador(int id) {
            dao.deletar(id);
        }
    }