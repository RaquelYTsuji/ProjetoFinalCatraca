package com.senai.model.dao.mysql;

import com.senai.model.Ambiente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AmbienteDAO {
    public void inserir(Ambiente ambiente) {
        String sql = "INSERT INTO ambiente (id, nome) VALUES (?, ?)";//Define os valores dos ? na SQL, pegando dados do objeto aluno.

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, String.valueOf(ambiente.getId()));
            stmt.setString(2, ambiente.getNome());

            stmt.executeUpdate();//Executa a inserção no banco
        } catch (SQLException e) {
            e.printStackTrace();//Captura e imprime qualquer erro de SQL.
        }
    }
    public void atualizar(Ambiente ambiente) {
        String sql = "UPDATE ambiente SET id = ?,nome = ?, WHERE id= ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, String.valueOf(ambiente.getId()));
            stmt.setString(2, ambiente.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM ambinete WHERE id = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Ambiente> buscarPorId(int id) {
        String sql = "SELECT * FROM ambiente WHERE id = ?";
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

    public List<Ambiente> listarTodos() {
        List<Ambiente> lista = new ArrayList<>();
        String sql = "SELECT * FROM ambiente";
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

    private Ambiente mapResultSet(ResultSet rs) throws SQLException {
        return new Ambiente(
                rs.getInt("id"),
                rs.getString("Nome")
        );
    }
}
