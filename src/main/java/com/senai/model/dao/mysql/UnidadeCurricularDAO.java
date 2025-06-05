package com.senai.model.dao.mysql;

import com.senai.model.SubTurma;
import com.senai.model.Turma;
import com.senai.model.UnidadeCurricular;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UnidadeCurricularDAO {
    public void inserir(UnidadeCurricular unidadeCurricular) {
        String sql = "INSERT INTO UnidadeCurricular(nome, id, curso, disciplina, professorResponsavel, cargaHoraria, metodoAvaliacao) VALUES (?,?,?,?,?,?)";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, unidadeCurricular.getNome());
            stmt.setString(2, String.valueOf(unidadeCurricular.getId()));
            stmt.setString(3, unidadeCurricular.getDisciplina());
            stmt.setString(4, unidadeCurricular.getProfessorResponsavel());
            stmt.setString(5, unidadeCurricular.getCargaHoraria());
            stmt.setString(6, String.valueOf(unidadeCurricular.getMetodoAvaliacao()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void atualizar(UnidadeCurricular unidadeCurricular) {
        String sql = "UPDATE UnidadeCurricular SET nome = ?, id= ? WHERE id= ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, unidadeCurricular.getNome());
            stmt.setString(2, String.valueOf(unidadeCurricular.getId()));
            stmt.setString(3, unidadeCurricular.getDisciplina());
            stmt.setString(4, unidadeCurricular.getProfessorResponsavel());
            stmt.setString(5, unidadeCurricular.getCargaHoraria());
            stmt.setString(6, String.valueOf(unidadeCurricular.getMetodoAvaliacao()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM UnidadeCurricular WHERE id = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<UnidadeCurricular> buscarPorId(int id) {
        String sql = "SELECT * FROM UnidadeCurricular WHERE id = ?";
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

    public List<UnidadeCurricular> listarTodos() {
        List<UnidadeCurricular> lista = new ArrayList<>();
        String sql = "SELECT * FROM UnidadeCurricular";
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

    private UnidadeCurricular mapResultSet(ResultSet rs) throws SQLException {
        return new UnidadeCurricular(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("disciplina"),
                rs.getString("professorResponsavel"),
                rs.getString("cargaHoraria"),
                rs.getString("metodoAvaliacao")
        );
    }
}
