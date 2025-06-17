package com.senai.model.dao.mysql;

import com.senai.model.AQV;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AQVdao {
public void inserir(AQV aqv) {
        String sql = "INSERT INTO AQV (nome, login, senha, id) VALUES (?, ?,?,?)";//Define os valores dos ? na SQL, pegando dados do objeto aluno.

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aqv.getNome());
            stmt.setString(2, aqv.getLogin());
            stmt.setString(3, aqv.getSenha());
            stmt.setString(4, String.valueOf(aqv.getId()));
            stmt.executeUpdate();//Executa a inserção no banco
        } catch (SQLException e) {
            e.printStackTrace();//Captura e imprime qualquer erro de SQL.
        }
    }
    public void atualizar(AQV aqv) {
        String sql = "UPDATE AQV SET nome = ?,login = ?,senha = ? WHERE id = ?";//Similar ao inserir, mas com stmt.setInt(5,
        // aluno.getId()) no final para identificar o aluno a ser atualizado.
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aqv.getNome());
            stmt.setString(2, aqv.getLogin());
            stmt.setString(3, aqv.getSenha());
            stmt.setInt(4, aqv.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM AQV WHERE id = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<AQV> buscarPorId(int id) {
        String sql = "SELECT * FROM AQV WHERE id= ?";
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


    public List<AQV> listarTodos() {
        List<AQV> lista = new ArrayList<>();
        String sql = "SELECT * FROM AQV";
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

    private AQV mapResultSet(ResultSet rs) throws SQLException {
        return new AQV(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("login"),
                rs.getString("senha")

        );
    }

}
