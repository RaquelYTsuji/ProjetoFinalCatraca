package com.senai.model.dao.mysql;
import com.senai.model.Curso;
import com.senai.model.SubTurma;
import com.senai.model.Turma;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TurmaDAO {
    public void inserir(Turma turma) {
        String sqlTurma = "INSERT INTO Turma (id, nome, curso_id, dataInicio, quantidadeSemestre, horarioEntrada) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlSubTurma = "INSERT INTO SubTurma (id, nome, turma_id) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmtTurma = conn.prepareStatement(sqlTurma);
             PreparedStatement stmtSubTurma = conn.prepareStatement(sqlSubTurma)) {

            stmtTurma.setInt(1, turma.getId());
            stmtTurma.setString(2, turma.getNome());
            stmtTurma.setInt(3, turma.getCurso().getId());
            stmtTurma.setDate(4, java.sql.Date.valueOf(turma.getDataInicio()));
            stmtTurma.setInt(5, turma.getQuantidadeSemestre());
            stmtTurma.setTime(6, java.sql.Time.valueOf(turma.getHorarioEntrada()));
            stmtTurma.executeUpdate();

            for (SubTurma st : turma.getSubTurmas()) {
                stmtSubTurma.setInt(1, st.getId());
                stmtSubTurma.setString(2, st.getNome());
                stmtSubTurma.setInt(3, turma.getId()); // FK para Turma
                stmtSubTurma.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Turma> buscarPorId(int id) {
        String sqlTurma = "SELECT * FROM Turma WHERE id = ?";
        String sqlSubTurma = "SELECT * FROM SubTurma WHERE turma_id = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmtTurma = conn.prepareStatement(sqlTurma);
             PreparedStatement stmtSubTurma = conn.prepareStatement(sqlSubTurma)) {

            stmtTurma.setInt(1, id);
            ResultSet rsTurma = stmtTurma.executeQuery();

            if (!rsTurma.next()) {
                return Optional.empty();
            }

            CursoDAO cursoDAO = new CursoDAO();
            Curso curso = cursoDAO.buscarPorId(rsTurma.getInt("curso_id")).orElse(null);

            stmtSubTurma.setInt(1, id);
            ResultSet rsSubTurma = stmtSubTurma.executeQuery();

            List<SubTurma> subTurmas = new ArrayList<>();
            while (rsSubTurma.next()) {
                SubTurma st = new SubTurma();
                st.setId(rsSubTurma.getInt("id"));
                st.setNome(rsSubTurma.getString("nome"));
                // Você pode carregar alunos aqui se quiser
                subTurmas.add(st);
            }

            Turma turma = new Turma(
                    rsTurma.getInt("id"),
                    rsTurma.getString("nome"),
                    curso,
                    subTurmas,
                    rsTurma.getDate("dataInicio").toLocalDate(),
                    rsTurma.getInt("quantidadeSemestre"),
                    rsTurma.getTime("horarioEntrada").toLocalTime()
            );

            return Optional.of(turma);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
