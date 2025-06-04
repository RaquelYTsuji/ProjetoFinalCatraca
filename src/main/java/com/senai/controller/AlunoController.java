package com.senai.controller;

import com.senai.model.Aluno;
import com.senai.model.dao.json.AlunoDAO;

import java.util.List;

public class AlunoController {
    private AlunoDAO alunoDAO = new AlunoDAO();//permite que a AlunoController use os métodos de acesso ao banco de dados
    // ou à lista onde os dados dos alunos são armazenados.

    public List<Aluno> listarAlunos() {//Método público que retorna uma lista de alunos (List<Aluno>).
        // Ele simplesmente chama o método listar() da AlunoDAO e retorna o resultado.
        return alunoDAO.listar();
    }

    public boolean cadastrarAluno(Aluno aluno){
        if(aluno!= null) {//verifica se o objeto aluno não está vazio
            alunoDAO.salvar(aluno);
            return true;//
        }
        return false;
    }
    public boolean atualizarAluno(Aluno aluno){
        if(aluno!= null){
            alunoDAO.atualizar(aluno);
            return true;
        }
        return false;
    }
    public boolean deletarAlunoPorID(int id){
        if(id >= 0){//detecta se o id é maior ou igual a zero
            alunoDAO.deletar(id);
            return true;
        }
        return false;

    }
}


