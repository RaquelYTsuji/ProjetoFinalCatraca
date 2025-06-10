package com.senai.controller;

import com.senai.model.Aluno;
import com.senai.model.Professor;
import com.senai.model.dao.json.AlunoDAO;
import com.senai.model.dao.json.ProfessorDAO;
import com.senai.util.CriptografiaUtil;

import java.time.LocalDate;
import java.util.List;

public class UsuarioController {
    private final com.senai.model.dao.json.AlunoDAO alunoDAO = new AlunoDAO();
    private final com.senai.model.dao.json.ProfessorDAO professorDAO = new ProfessorDAO();

    public String cadastrarUsuario(String tipo, String nome, String unidadeCurricular, String login, String senha) {
        if (tipo.equals("1")) {//0, nome, dadoExtra,login, CriptografiaUtil.hash(senha)
            alunoDAO.salvar(new Aluno(nome,login,CriptografiaUtil.hash(senha),0, "", LocalDate.now()));
            return "Aluno cadastrado com sucesso.";
        } else if (tipo.equals("2")) {
            professorDAO.salvar(new Professor(nome,login,CriptografiaUtil.hash(senha),0,unidadeCurricular));
            return "Professor cadastrado com sucesso.";
        } else {
            return "Tipo inválido.";
        }
    }

    public String atualizarUsuario(String tipo, int id, String nome, String unidadeCurricular, String login, String senha) {
        if (tipo.equals("1")) {
            alunoDAO.atualizar(new Aluno(nome,login,CriptografiaUtil.hash(senha),0, "", LocalDate.now()));
            return "Aluno atualizado.";
        } else if (tipo.equals("2")) {
            professorDAO.atualizar(new Professor(nome,login,CriptografiaUtil.hash(senha),0,unidadeCurricular));
            return "Professor atualizado.";
        } else {
            return "Tipo inválido.";
        }
    }

    public String removerUsuario(String tipo, int id) {
        if (tipo.equals("1")) {
            alunoDAO.deletar(id);
            return "Aluno removido.";
        } else if (tipo.equals("2")) {
            professorDAO.deletar(id);
            return "Professor removido.";
        } else {
            return "Tipo inválido.";
        }
    }

    public List<Aluno> listarAlunos() {
        return alunoDAO.listar();
    }

    public List<Professor> listarProfessores() {
        return professorDAO.listar();
    }
}
