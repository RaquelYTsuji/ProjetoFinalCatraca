package com.senai.model.dao.mysql;
import com.senai.model.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CursoDAO {
    public void inserir(Curso curso) {
        String sql = "INSERT INTO Curso (titulo,unidadesCurriculares,cargaHoraria, tipo, tolerancia, id) VALUES (?, ?,?,?,?,?)";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curso.getTitulo());
            stmt.setString(2, String.valueOf(curso.getUnidadeCurriculares()));
            stmt.setInt(3, curso.getCargaHoraria());
            stmt.setBoolean(4, curso.isTipo());
            stmt.setInt(5, curso.getTolerancia());
            stmt.setString(6, String.valueOf(curso.getId()));
            stmt.executeUpdate();//Executa a inserção no banco
        } catch (SQLException e) {
            e.printStackTrace();//Captura e imprime qualquer erro de SQL.
        }
    }
    public void atualizar(Curso curso) {
        String sql = "UPDATE Curso SET titulo = ?,unidadesCurriculares = ?,cargaHoraria = ?, tipo = ?, tolerancia = ? WHERE id = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curso.getTitulo());
            stmt.setString(2, String.valueOf(curso.getUnidadeCurriculares()));
            stmt.setInt(3, curso.getCargaHoraria());
            stmt.setBoolean(4, curso.isTipo());
            stmt.setInt(5, curso.getTolerancia());
            stmt.setString(6, String.valueOf(curso.getId()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM Curso WHERE id = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Curso> buscarPorId(int id) {
        String sql = "SELECT * FROM Curso WHERE id = ?";
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


    public List<Curso> listarTodos() {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT * FROM Curso";
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

    private Curso mapResultSet(ResultSet rs) throws SQLException {
        return new Curso(
                rs.getString("titulo"),
                rs.getString("unidadesCurriculares"),
                rs.getInt("cargaHoraria"),
                rs.getBoolean("tipo"),
                rs.getInt("tolerancia"),
                rs.getInt("id")
        );
    }
}
