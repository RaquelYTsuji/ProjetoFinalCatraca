package com.senai.model.dao.mysql;

import com.senai.model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlunoDAO {
    public void inserir(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, login, senha, idAluno,id_acesso) VALUES (?, ?,?,?, ?)";//Define os valores dos ? na SQL, pegando dados do objeto aluno.

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getLogin());
            stmt.setString(3, aluno.getSenha());
            stmt.setString(4, String.valueOf(aluno.getIdAluno()));
            stmt.setString(5, aluno.getIdDeAcesso());

            stmt.executeUpdate();//Executa a inserção no banco
        } catch (SQLException e) {
            e.printStackTrace();//Captura e imprime qualquer erro de SQL.
        }
    }
    public void atualizar(Aluno aluno) {
        String sql = "UPDATE aluno SET nome = ?,login = ?,senha = ?, idAluno = ? WHERE idAluno = ?";//Similar ao inserir, mas com stmt.setInt(5,
        // aluno.getId()) no final para identificar o aluno a ser atualizado.
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getLogin());
            stmt.setString(3, aluno.getSenha());
            stmt.setString(4, aluno.getIdDeAcesso());
            stmt.setInt(5, aluno.getIdAluno());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int idAluno) {
        String sql = "DELETE FROM aluno WHERE idAluno = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Aluno> buscarPorId(int idAluno) {
        String sql = "SELECT * FROM aluno WHERE idAluno = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Aluno> buscarPorRfid(String idAluno) {
        String sql = "SELECT * FROM aluno WHERE id_cartao = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idAluno);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Aluno> listarTodos() {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM aluno";
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

    private Aluno mapResultSet(ResultSet rs) throws SQLException {
        return new Aluno(
                rs.getString("nome"),
                rs.getString("login"),
                rs.getString("senha"),
                rs.getInt("id")
        );
    }
}
