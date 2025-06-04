package com.senai.model;

import com.senai.model.dao.json.TurmaDAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class Aluno extends Usuario {
    private String idAcesso;
    private LocalDate dataNascimento;

    public Aluno(String nome, String login, String senha, int idAluno, String idAcesso, LocalDate dataNascimento) {
        super(idAluno, nome, login, senha);
        this.dataNascimento = dataNascimento;
        this.idAcesso = idAcesso;
    }
    public String getIdAcesso() {
        return idAcesso;
    }

    public void setIdAcesso(String idAcesso) {
        this.idAcesso = idAcesso;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "Id=" + getId() +
                ", Nome='" + getNome() +  '\'' +
                ", Login='" + getLogin() + '\'' +
                ", Senha='" + getSenha() + '\'' +
                ", IdAcesso='" + getIdAcesso() + '\'' +
                ", DataNascimento='" + getDataNascimento() + '\'' +
                '}';
    }

    @Override
    public String getTipo() {
        return "Aluno";
    }

    public boolean estaAtrasado(int idAluno) {
        TurmaDAO turmaDAO = new TurmaDAO();
        Optional<Turma> turma = turmaDAO.buscarTurmaDoAluno(idAluno);
        return LocalTime.now().isAfter(turma.get().getHorarioEntrada().
                        plusMinutes(turma.get().getCurso().getTolerancia()));
    }
}
