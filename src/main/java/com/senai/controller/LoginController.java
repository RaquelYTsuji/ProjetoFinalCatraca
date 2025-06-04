package com.senai.controller;

import com.senai.model.AlunoDAO;
import com.senai.model.ProfessorDAO;
import com.senai.model.Usuario;

import java.util.Optional;

public class LoginController {
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();

    public Optional<Usuario> autenticar(String login, String senha) {

        Optional<? extends Usuario> prof = professorDAO.buscarPorLogin(login);
        if (prof.isPresent() && prof.get().getSenha().equals(senha)) return Optional.of(prof.get());

        Optional<? extends Usuario> aluno = alunoDAO.listar(login);
        if (aluno.isPresent() && aluno.get().getSenha().equals(senha)) return Optional.of(aluno.get());

        return Optional.empty();
    }
}
