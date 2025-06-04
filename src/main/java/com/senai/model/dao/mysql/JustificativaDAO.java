package com.senai.model.dao.mysql;
import com.senai.model.Justificativa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JustificativaDAO {
    public void inserir(Justificativa justificativa) {
        String sql = "INSERT INTO Justificativa (id, tipo, descricao, dataHoraJustificada, quantidadeDias, prazoDeAceite, anexo, status, cancelar ) VALUES (?, ?,?,?, ?, ?. ?,?,? )";//Define os valores dos ? na SQL, pegando dados do objeto aluno.
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, justificativa.getId());
            stmt.setString(2, justificativa.getTipo());
            stmt.setString(3, justificativa.getDescricao());
            stmt.setTimestamp(4, Timestamp.valueOf(justificativa.getDataHoraJustificatida()));
            stmt.setInt(5, justificativa.getQuantidadeDias());
            stmt.setInt(6, justificativa.getPrazoDeAceite());
            stmt.setString(7, justificativa.getAnexo());
            stmt.setString(8, justificativa.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void atualizar(Justificativa justificativa) {
        String sql = "UPDATE Justificativa SET tipo = ? WHERE id = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, justificativa.getId());
            stmt.setString(2, justificativa.getTipo());
            stmt.setString(3, justificativa.getDescricao());
            stmt.setTimestamp(4, Timestamp.valueOf(justificativa.getDataHoraJustificatida()));
            stmt.setInt(5, justificativa.getQuantidadeDias());
            stmt.setInt(6, justificativa.getPrazoDeAceite());
            stmt.setString(7, justificativa.getAnexo());
            stmt.setString(8, justificativa.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void remover(int id) {
        String sql = "DELETE FROM Justificativa WHERE id = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Optional<Justificativa> buscarPorId(int id) {
        String sql = "SELECT * FROM justificativa WHERE id = ?";
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
    public List<Justificativa> listarTodos() {
        List<Justificativa> lista = new ArrayList<>();
        String sql = "SELECT * FROM Justificativa";
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
    private Justificativa mapResultSet(ResultSet rs) throws SQLException {
        return new Justificativa(
                rs.getInt("id"),
                rs.getString("tipo"),
                rs.getString("descricao"),
                rs.getTimestamp("dataHoraJustificada").toLocalDateTime(),
                rs.getInt("quantidadeDeDias"),
                rs.getInt("prazoDeAceite"),
                rs.getString("anexo"),
                rs.getString("Status"),
                rs.getBoolean("cancelar")
        );
    }
}
