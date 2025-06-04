package com.senai.controller;

import com.senai.model.Professor;
import com.senai.model.ProfessorDAO;

import java.util.List;

public class ProfessorController {
    private ProfessorDAO professorDAO= new ProfessorDAO();//permite que a AlunoController use os métodos de acesso ao banco de dados
    // ou à lista onde os dados dos alunos são armazenados.

    public List<Professor> listarProfessores() {//Método público que retorna uma lista de alunos (List<Aluno>).
        // Ele simplesmente chama o método listar() da AlunoDAO e retorna o resultado.
        return professorDAO.listar();
    }

    public boolean cadastrarProfessor(Professor professor){
        if(professor!= null) {//verifica se o objeto aluno não está vazio
            professorDAO.salvar(professor);
            return true;//
        }
        return false;
    }
    public boolean atualizarProfessor(Professor professor){
        if(professor!= null){
           professorDAO.atualizar(professor);
            return true;
        }
        return false;
    }
    public boolean deletarProfessorPorID(int id){
        if(id >= 0){//detecta se o id é maior ou igual a zero
            professorDAO.deletar(id);
            return true;
        }
        return false;

    }
}
