package com.senai.model;

import com.senai.model.dao.json.TurmaDAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class Aluno extends Usuario {
    int idAluno;
    private String idCartaoRfid;
    private LocalDate dataNascimento;

    public Aluno(String nome, String login, String senha, int idAluno, String idCartaoRfid, LocalDate dataNascimento) {
        super(nome, login, senha);
        this.idAluno = idAluno;
        this.idCartaoRfid = idCartaoRfid;
        this.dataNascimento = dataNascimento;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getIdCartaoRfid() {
        return idCartaoRfid;
    }

    public void setIdCartaoRfid(String idCartaoRfid) {
        this.idCartaoRfid = idCartaoRfid;
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
                "ID=" + idAluno +
                ", Nome='" + getNome() +  '\'' +
                ", Login='" + getLogin() + '\'' +
                ", Senha='" + getSenha() + '\'' +
                ", IdCartaoRfid='" + getIdCartaoRfid() + '\'' +
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
