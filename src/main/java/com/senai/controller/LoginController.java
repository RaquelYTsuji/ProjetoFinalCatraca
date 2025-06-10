package com.senai.controller;


import com.senai.model.Usuario;
import com.senai.model.dao.json.AQVDAO;
import com.senai.model.dao.json.AlunoDAO;
import com.senai.model.dao.json.CoordenadorDAO;
import com.senai.model.dao.json.ProfessorDAO;

import java.util.Optional;

public class LoginController {
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();
    private final AQVDAO aqvDAO = new AQVDAO();
    private final CoordenadorDAO coordenadorDAO = new CoordenadorDAO();

    public Optional<Usuario> autenticar(String login, String senha) {
        Optional<? extends Usuario> coordenador = coordenadorDAO.buscarPorLogin(login);
        if (coordenador.isPresent() && coordenador.get().getSenha().equals(senha)) return Optional.of(coordenador.get());

        Optional<? extends Usuario> aqv = aqvDAO.buscarPorLogin(login);
        if (aqv.isPresent() && aqv.get().getSenha().equals(senha)) return Optional.of(aqv.get());

        Optional<? extends Usuario> prof = professorDAO.buscarPorLogin(login);
        if (prof.isPresent() && prof.get().getSenha().equals(senha)) return Optional.of(prof.get());

        Optional<? extends Usuario> aluno = alunoDAO.buscarPorLogin(login);
        if (aluno.isPresent() && aluno.get().getSenha().equals(senha)) return Optional.of(aluno.get());

        return Optional.empty();
    }
}
