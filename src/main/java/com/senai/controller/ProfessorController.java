package com.senai.controller;

import com.senai.model.Professor;
import com.senai.model.dao.json.ProfessorDAO;

import java.util.List;

public class ProfessorController {
    private ProfessorDAO professorDAO = new ProfessorDAO(); // Permite que a ProfessorController use os métodos de acesso ao banco de dados

    public List<Professor> listarProfessores() {
        // Método público que retorna uma lista de professores (List<Professor>).
        // Ele chama o método listar() da ProfessorDAO e retorna o resultado.
        return professorDAO.listar();
    }

    public boolean cadastrarProfessor(Professor professor) {
        if (professor != null) { // Verifica se o objeto professor não está vazio
            professorDAO.salvar(professor);
            return true;
        }
        return false;
    }

    public boolean atualizarProfessor(Professor professor) {
        if (professor != null) {
            professorDAO.atualizar(professor);
            return true;
        }
        return false;
    }

    public boolean deletarProfessorPorID(int id) {
        if (id >= 0) { // Detecta se o id é maior ou igual a zero
            professorDAO.deletar(id);
            return true;
        }
        return false;
    }

    // Método para procurar um professor pelo ID
    public Professor procurarProfessorPorID(int id) {
        if (id >= 0) { // Verifica se o ID fornecido é válido
            return professorDAO.procurar(id); // Chama o método procurar da ProfessorDAO
        }
        return null; // Retorna null se o ID não for válido
    }
    public Professor procurarProfessores(int id){
        return professorDAO.procurar(id);
    }
}

