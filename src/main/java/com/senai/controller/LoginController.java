package com.senai.controller;

import com.senai.model.AlunoDAO;
import com.senai.model.CoordenadorDAO;
import com.senai.model.ProfessorDAO;
import com.senai.model.Usuario;
import com.senai.util.CriptografiaUtil;

import java.util.Optional;

public class LoginController {
    private final ProfessorDAO professorDAO = new ProfessorDAO();
    private final CoordenadorDAO coordenadorDAO = new CoordenadorDAO();
    private final AlunoDAO alunoDAO = new AlunoDAO();

    public Optional<Usuario> autenticar(String login, String senha) {
        // Criptografa a senha fornecida para comparação
        String senhaCriptografada = CriptografiaUtil.hash(senha);

        // Verifica no banco de dados para o Professor
        Optional<? extends Usuario> prof = professorDAO.buscarPorLogin(login);
        if (prof.isPresent() && prof.get().getSenha().equals(senhaCriptografada)) {
            return Optional.of(prof.get());
        }

        // Verifica no banco de dados para o Coordenador
        Optional<? extends Usuario> cood = coordenadorDAO.buscarPorLogin(login);
        if (cood.isPresent() && cood.get().getSenha().equals(senhaCriptografada)) {
            return Optional.of(cood.get());
        }

        // Verifica no banco de dados para o Aluno
        Optional<? extends Usuario> aluno = alunoDAO.buscarPorLogin(login);
        if (aluno.isPresent() && aluno.get().getSenha().equals(senhaCriptografada)) {
            return Optional.of(aluno.get());
        }

        // Se não encontrar nenhum usuário ou as senhas não coincidirem
        return Optional.empty();

    }
}
    /*private final AlunoDAO alunoDAO = new AlunoDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();
    private final CoordenadorDAO coordenadorDAO = new CoordenadorDAO();

    public Optional<Usuario> autenticar(String login, String senha) {

        Optional<? extends Usuario> prof = professorDAO.buscarPorLogin(login);
        if (prof.isPresent() && prof.get().getSenha().equals(senha)) return Optional.of(prof.get());

        Optional<? extends Usuario> cood = coordenadorDAO.buscarPorLogin(login);
        if (cood.isPresent() && cood.get().getSenha().equals(senha)) return Optional.of(cood.get());

        Optional<? extends Usuario> aluno = alunoDAO.buscarPorLogin(login);
        if (aluno.isPresent() && aluno.get().getSenha().equals(senha)) return Optional.of(aluno.get());

        return Optional.empty();
    }*/

