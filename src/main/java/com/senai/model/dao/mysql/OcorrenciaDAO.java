package com.senai.model.dao.mysql;

import com.senai.model.Justificativa;
import com.senai.model.Ocorrencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OcorrenciaDAO {
    public void inserir(Ocorrencia ocorrencia) {
        String sql = "INSERT INTO Justificativa (id, tipo, descricao, data_hora, status) VALUES (?, ?,?,?)";//Define os valores dos ? na SQL, pegando dados do objeto aluno.
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ocorrencia.getId());
            stmt.setString(2,ocorrencia.getTipo());
            stmt.setString(3, ocorrencia.getDescricao());
            stmt.setTimestamp(4, Timestamp.valueOf(ocorrencia.getDataHora()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void atualizar(Ocorrencia ocorrencia) {
        String sql = "UPDATE Ocorrencia SET tipo = ? WHERE id = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ocorrencia.getId());
            stmt.setString(2,ocorrencia.getTipo());
            stmt.setString(3, ocorrencia.getDescricao());
            stmt.setTimestamp(4, Timestamp.valueOf(ocorrencia.getDataHora()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void remover(int id) {
        String sql = "DELETE FROM Ocorrencia WHERE id = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Optional<Ocorrencia> buscarPorId(int id) {
        String sql = "SELECT * FROM Ocorrencia WHERE id = ?";
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
    public List<Ocorrencia> listarTodos() {
        List<Ocorrencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM ocorrencia";
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
    private Ocorrencia mapResultSet(ResultSet rs) throws SQLException {
        return new Ocorrencia(
                rs.getInt("id"),
                rs.getString("tipo"),
                rs.getString("descricao"),
                rs.getTimestamp("dataHora").toLocalDateTime()
        );

    }
}
