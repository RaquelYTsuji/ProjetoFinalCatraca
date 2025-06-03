package com.senai.model.dao.mysql;
import com.senai.model.Professor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfessorDAO {
    public void inserir(Professor professor) {
        String sql = "INSERT INTO aluno (nome, login, senha, id, unidadeCurricular ) VALUES (?, ?,?,?, ?)";//Define os valores dos ? na SQL, pegando dados do objeto aluno.
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getLogin());
            stmt.setString(3, professor.getSenha());
            stmt.setString(4, String.valueOf(professor.getIdProfessor()));
            stmt.setString(5, professor.getUnidadeCurricular());
            stmt.executeUpdate();//Executa a inserção no banco
        } catch (SQLException e) {
            e.printStackTrace();//Captura e imprime qualquer erro de SQL.
        }
    }
    public void atualizar(Professor professor) {
        String sql = "UPDATE aluno SET nome = ?,login = ?,senha = ?, idProfessor = ?, unidadeCurricular = ? WHERE idProfessor = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getLogin());
            stmt.setString(3, professor.getSenha());
            stmt.setString(4, professor.getUnidadeCurricular());
            stmt.setInt(5, professor.getIdProfessor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int idProfessor) {
        String sql = "DELETE FROM Professor WHERE idProfessor = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProfessor);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Professor> buscarPorId(int idAluno) {
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


    public List<Professor> listarTodos() {
        List<Professor> lista = new ArrayList<>();
        String sql = "SELECT * FROM Professor";
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

    private Professor mapResultSet(ResultSet rs) throws SQLException {
        return new Professor(
                rs.getString("nome"),
                rs.getString("login"),
                rs.getString("senha"),
                rs.getInt("idProfessor"),
                rs.getString("unidadeCurricular")
        );
    }
}
