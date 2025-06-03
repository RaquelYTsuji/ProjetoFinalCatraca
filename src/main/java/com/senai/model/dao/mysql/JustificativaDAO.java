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
            stmt.setInt(4, justificativa.getDataHoraJustificatida());
            stmt.setInt(5, justificativa.getQuantidadeDias());
            stmt.setString(2, justificativa.getTipo());


            stmt.executeUpdate();//Executa a inserção no banco
        } catch (SQLException e) {
            e.printStackTrace();//Captura e imprime qualquer erro de SQL.
        }
    }
    public void atualizar(Coordenador coordenador) {
        String sql = "UPDATE Coordenador SET nome = ?,login = ?,senha = ? WHERE id = ?";//Similar ao inserir, mas com stmt.setInt(5,
        // aluno.getId()) no final para identificar o aluno a ser atualizado.
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, coordenador.getNome());
            stmt.setString(2, coordenador.getLogin());
            stmt.setString(3, coordenador.getSenha());
            stmt.setInt(4, coordenador.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM Coordenador WHERE id = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Coordenador> buscarPorId(int id) {
        String sql = "SELECT * FROM Coordenador WHERE id = ?";
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


    public List<Coordenador> listarTodos() {
        List<Coordenador> lista = new ArrayList<>();
        String sql = "SELECT * FROM Coordenador";
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

    private Coordenador mapResultSet(ResultSet rs) throws SQLException {
        return new Coordenador(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("login"),
                rs.getString("senha")

        );
    }
}
