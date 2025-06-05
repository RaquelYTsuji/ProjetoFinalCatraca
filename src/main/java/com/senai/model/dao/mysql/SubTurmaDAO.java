package com.senai.model.dao.mysql;
import com.senai.model.Aluno;
import com.senai.model.SubTurma;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubTurmaDAO {
    public void inserir(SubTurma subTurma) {
        String sql = "INSERT INTO SubTurma (id, nome) VALUES (?, ?)";
        String sqlAluno = "INSERT INTO Aluno (nome, login, senha, id_subturma) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             PreparedStatement stmtAluno = conn.prepareStatement(sqlAluno)) {

            stmt.setInt(1, subTurma.getId());
            stmt.setString(2, subTurma.getNome());
            stmt.executeUpdate();

            for (Aluno a : subTurma.getAlunos()) {
                stmtAluno.setString(1, a.getNome());
                stmtAluno.setString(2, a.getLogin());
                stmtAluno.setString(3, a.getSenha());
                stmtAluno.setInt(4, subTurma.getId()); // relacionamento
                stmtAluno.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
}
    public void atualizar(SubTurma subTurma) {
            String sql = "UPDATE SubTurma SET nome = ? WHERE id = ?";
            String sqlAluno = "INSERT INTO Aluno (nome, login, senha, id_subturma) VALUES (?, ?, ?, ?)";

            try (Connection conn = ConexaoMySQL.conectar();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 PreparedStatement stmtAluno = conn.prepareStatement(sqlAluno)) {

                stmt.setString(1, subTurma.getNome());
                stmt.setInt(2, subTurma.getId());
                stmt.executeUpdate();

                for (Aluno a : subTurma.getAlunos()) {
                    stmtAluno.setString(1, a.getNome());
                    stmtAluno.setString(2, a.getLogin());
                    stmtAluno.setString(3, a.getSenha());
                    stmtAluno.setInt(4, subTurma.getId());
                    stmtAluno.executeUpdate();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void remover(int id) {
        String sqlAluno = "DELETE FROM Aluno WHERE id_subturma = ?";
        String sql = "DELETE FROM SubTurma WHERE id = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmtAluno = conn.prepareStatement(sqlAluno);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmtAluno.setInt(1, id);
            stmtAluno.executeUpdate();

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
        int id = rs.getInt("id");
        return new SubTurma(
                id,
                rs.getString("nome"),
                buscarAlunoPorSubTurma(id) // Alunos associados
        );
    }

    private List<Aluno> buscarAlunoPorSubTurma(int idSubTurma) throws SQLException {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM Aluno WHERE id_subturma = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idSubTurma);

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
