package com.senai.controller;

import com.senai.model.Aluno;
import com.senai.model.AlunoDAO;
import com.senai.model.Professor;
import com.senai.model.ProfessorDAO;
import com.senai.util.CriptografiaUtil;
import java.util.List;

public class UsuarioController {
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();

    public String cadastrarUsuario(String tipo, String nome, String unidadeCurricular, String login, String senha) {
        if (tipo.equals("1")) {//0, nome, dadoExtra,login, CriptografiaUtil.hash(senha)
            alunoDAO.inserir (new Aluno(nome,login,CriptografiaUtil.hash(senha),0));
            return "Aluno cadastrado com sucesso.";
        } else if (tipo.equals("2")) {
            professorDAO.inserir(new Professor(nome,login,CriptografiaUtil.hash(senha),0,unidadeCurricular));
            return "Professor cadastrado com sucesso.";
        } else {
            return "Tipo inválido.";
        }
    }

    public String atualizarUsuario(String tipo, int id, String nome, String unidadeCurricular, String login, String senha) {
        if (tipo.equals("1")) {
            alunoDAO.atualizar(new Aluno(nome, login, CriptografiaUtil.hash(senha),id));
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
