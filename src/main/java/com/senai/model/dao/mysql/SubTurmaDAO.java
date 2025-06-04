package com.senai.model.dao.mysql;
import com.senai.model.Aluno;
import com.senai.model.SubTurma;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubTurmaDAO {
    public void inserir(SubTurma subTurma) {
        String sql = "INSERT INTO SubTurma (nome, id) VALUES (?, ?)";
        String sqlAluno = "INSERT INTO Aluno (nome, login, senha) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             PreparedStatement stmtAluno= conn.prepareStatement(sqlAluno)) {
            stmt.setString(1, subTurma.getNome());
            stmt.setString(2, String.valueOf(subTurma.getId()));

            for (Aluno a : subTurma.getAlunos()) {
                stmtAluno.setString(1, a.getNome());
                stmtAluno.setString(2, a.getLogin());
                stmtAluno.setString(3, a.getSenha());
                stmtAluno.executeUpdate();
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void atualizar(SubTurma subTurma) {
        String sql = "UPDATE SubTurma SET nome = ?, id = ? WHERE id = ?";
        String sqlAluno = "INSERT INTO Aluno (nome, login, senha) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             PreparedStatement stmtAluno = conn.prepareStatement(sqlAluno)) {

            stmt.setString(1, subTurma.getNome());
            stmt.setInt(2, subTurma.getId());

            for (Aluno a : subTurma.getAlunos()) {
                stmtAluno.setString(1, a.getNome());
                stmtAluno.setString(2, a.getLogin());
                stmtAluno.setString(3, a.getSenha());
                stmtAluno.executeUpdate();
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM SubTurma WHERE id = ?";
        String sqlAluno = "DELETE FROM Aluno WHERE id = ?";

        try (Connection conn = ConexaoMySQL.conectar();

             PreparedStatement stmtAluno = conn.prepareStatement(sqlAluno);
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
        int idAluno = rs.getInt("id");
        return new SubTurma(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getInt(idAluno));

        );
    }
    private List<Aluno> buscarAlunoPorId(int idAluno) throws SQLException {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM Aluno WHERE idAluno = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Aluno a = new Aluno();
                    a.setIdAluno(rs.getInt("id"));
                    a.setNome(rs.getString("nome"));
                    a.setLogin(rs.getString("login"));
                    a.setSenha(rs.getString("senha"));
                    alunos.add(a);
                }
            }
        }
        return alunos;
    }
}
