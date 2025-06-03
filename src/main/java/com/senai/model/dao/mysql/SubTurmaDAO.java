package com.senai.model.dao.mysql;
import com.senai.model.SubTurma;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubTurmaDAO {
    public void inserir(SubTurma subTurma) {
        String sql = "INSERT INTO SubTurma (nome, id, alunos) VALUES (?, ?,?)";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, subTurma.getNome());
            stmt.setString(2, String.valueOf(subTurma.getId()));
            stmt.setString(3, subTurma.getAlunos());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void atualizar(SubTurma subTurma) {
        String sql = "UPDATE aluno SET nome = ?, id= ? WHERE i= ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, subTurma.getNome());
            stmt.setString(2, String.valueOf(subTurma.getId()));
            stmt.setString(3, subTurma.getAlunos());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM SubTurma WHERE id = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<SubTurma> buscarPorId(int id) {
        String sql = "SELECT * FROM SubTurma WHERE id = ?";
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

    public List<SubTurma> listarTodos() {
        List<SubTurma> lista = new ArrayList<>();
        String sql = "SELECT * FROM SubTurma";
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

    private SubTurma mapResultSet(ResultSet rs) throws SQLException {
        return new SubTurma(
                rs.getInt("idProfessor"),
                rs.getString("nome"),
                rs.getString("alunos")
        );
    }
}
