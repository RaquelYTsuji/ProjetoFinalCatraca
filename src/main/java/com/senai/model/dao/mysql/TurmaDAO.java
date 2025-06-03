package com.senai.model.dao.mysql;
import com.senai.model.Turma;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TurmaDAO {
    public void inserir(Turma turma) {
        String sql = "INSERT INTO Turma (nome, id, curso, subTurma, dataInicio, quantidadeSemestre, horarioentrada) VALUES (?,?,?,?,?,?)";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, turma.getNome());
            stmt.setString(2, String.valueOf(turma.getId()));
            stmt.setString(3, turma.getCurso());
            stmt.setString(4, String.valueOf(turma.getDataInicio()));
            stmt.setInt(5, turma.getQuantidadeSemestre());
            stmt.setString(6, String.valueOf(turma.getHorarioEntrada()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void atualizar(Turma turma) {
        String sql = "UPDATE Turma SET nome = ?, id= ? WHERE id= ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, turma.getNome());
            stmt.setString(2, String.valueOf(turma.getId()));
            stmt.setString(3, turma.getCurso());
            stmt.setString(4, turma.getDataInicio());
            stmt.setInt(5, turma.getQuantidadeSemestre());
            stmt.setString(6, turma.getHorarioEntrada());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM Turma WHERE id = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Turma> buscarPorId(int id) {
        String sql = "SELECT * FROM Turma WHERE id = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Turma> listarTodos() {
        List<Turma> lista = new ArrayList<>();
        String sql = "SELECT * FROM Turma";
        try (Connection conn = ConexaoMySQL.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Turma mapResultSet(ResultSet rs) throws SQLException {
        return new Turma(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("curso"),
                rs.getString("dataInicio"),
                rs.getInt("quantidadeSemestre"),
                rs.getString("horarioEntrada")
        );
    }
}
