package com.senai.model.dao.mysql;
import com.senai.model.Aula;
import com.senai.model.Professor;
import com.senai.model.UnidadeCurricular;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AulaDAO {
    public void inserir(Aula aula) {
        String sqlAula = "INSERT INTO Aula (id, aula_curricular, unidadeHorario) VALUES (?, ?, ?)";
        String sqlUnidade = "INSERT INTO UnidadeCurricular (nome, disciplina, cargaHoraria, aula_id) VALUES (?, ?, ?, ?)";
        String sqlProfessor = "INSERT INTO Professor (id, nome, login, senha, aula_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmtAula = conn.prepareStatement(sqlAula);
             PreparedStatement stmtUnidade = conn.prepareStatement(sqlUnidade);
             PreparedStatement stmtProfessor = conn.prepareStatement(sqlProfessor)) {

            stmtAula.setInt(1, aula.getId());
            stmtAula.setString(2, aula.getAulaCurricular());
            stmtAula.setTimestamp(3, Timestamp.valueOf(aula.getUnidadeHorario().toString()));
            stmtAula.executeUpdate();

            for (UnidadeCurricular uc : aula.getListaUC()) {
                stmtUnidade.setString(1, uc.getNome());
                stmtUnidade.setString(2, uc.getCargaHoraria());
                stmtUnidade.setInt(3, aula.getId());
                stmtUnidade.executeUpdate();
            }
            for (Professor p : aula.getProfessores()) {
                stmtProfessor.setInt(1, p.getId());
                stmtProfessor.setString(2, p.getNome());
                stmtProfessor.setString(3, p.getLogin());
                stmtProfessor.setString(4, p.getSenha());
                stmtProfessor.setInt(5, aula.getId());
                stmtProfessor.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void atualizar(Aula aula) {
        String sqlUpdateAula = "UPDATE Aula SET aula_curricular = ?, unidadeHorario = ? WHERE id = ?";
        String sqlDeleteUC = "DELETE FROM UnidadeCurricular WHERE aula_id = ?";
        String sqlDeleteProf = "DELETE FROM Professor WHERE aula_id = ?";
        String sqlInsertUC = "INSERT INTO UnidadeCurricular (nome, disciplina, cargaHoraria, aula_id) VALUES (?, ?, ?, ?)";
        String sqlInsertProf = "INSERT INTO Professor (id, nome, login, senha, aula_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmtUpdateAula = conn.prepareStatement(sqlUpdateAula);
             PreparedStatement stmtDeleteUC = conn.prepareStatement(sqlDeleteUC);
             PreparedStatement stmtDeleteProf = conn.prepareStatement(sqlDeleteProf);
             PreparedStatement stmtInsertUC = conn.prepareStatement(sqlInsertUC);
             PreparedStatement stmtInsertProf = conn.prepareStatement(sqlInsertProf)) {

            stmtUpdateAula.setString(1, aula.getAulaCurricular());
            stmtUpdateAula.setTimestamp(2, Timestamp.valueOf(aula.getUnidadeHorario().toString()));
            stmtUpdateAula.setInt(3, aula.getId());
            stmtUpdateAula.executeUpdate();

            stmtDeleteUC.setInt(1, aula.getId());
            stmtDeleteUC.executeUpdate();

            stmtDeleteProf.setInt(1, aula.getId());
            stmtDeleteProf.executeUpdate();

            for (UnidadeCurricular uc : aula.getListaUC()) {
                stmtInsertUC.setString(1, uc.getNome());
                stmtInsertUC.setString(2, uc.getDisciplina());
                stmtInsertUC.setString(3, uc.getCargaHoraria());
                stmtInsertUC.setInt(4, aula.getId());
                stmtInsertUC.executeUpdate();
            }
            for (Professor p : aula.getProfessores()) {
                stmtInsertProf.setInt(1, p.getId());
                stmtInsertProf.setString(2, p.getNome());
                stmtInsertProf.setString(3, p.getLogin());
                stmtInsertProf.setString(4, p.getSenha());
                stmtInsertProf.setInt(5, aula.getId());
                stmtInsertProf.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        String sqlDeleteUC = "DELETE FROM UnidadeCurricular WHERE aula_id = ?";
        String sqlDeleteProf = "DELETE FROM Professor WHERE aula_id = ?";
        String sqlDeleteAula = "DELETE FROM Aula WHERE id = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmtUC = conn.prepareStatement(sqlDeleteUC);
             PreparedStatement stmtProf = conn.prepareStatement(sqlDeleteProf);
             PreparedStatement stmtAula = conn.prepareStatement(sqlDeleteAula)) {

            stmtUC.setInt(1, id);
            stmtUC.executeUpdate();

            stmtProf.setInt(1, id);
            stmtProf.executeUpdate();


            stmtAula.setInt(1, id);
            stmtAula.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Aula> buscarPorId(int id) {
        String sql = "SELECT * FROM Aula WHERE id = ?";
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

    public List<Aula> listarTodos() {
        List<Aula> lista = new ArrayList<>();
        String sql = "SELECT * FROM Aula";

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

    private Aula mapResultSet(ResultSet rs) throws SQLException {
        int aulaId = rs.getInt("id");

        return new Aula(
                aulaId,
                rs.getString("aula_curricular"),
                buscarProfessoresPorAulaId(aulaId),
                buscarUnidadesCurricularesPorAulaId(aulaId),
                rs.getTimestamp("unidadeHorario").toLocalDateTime().toLocalTime()
        );
    }

    private List<UnidadeCurricular> buscarUnidadesCurricularesPorAulaId(int aulaId) throws SQLException {
        List<UnidadeCurricular> unidades = new ArrayList<>();
        String sql = "SELECT * FROM UnidadeCurricular WHERE aula_id = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, aulaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                UnidadeCurricular uc = new UnidadeCurricular();
                uc.setId(rs.getInt("id"));
                uc.setNome(rs.getString("nome"));
                uc.setDisciplina(rs.getString("disciplina"));
                uc.setCargaHoraria(rs.getString("cargaHoraria"));
                unidades.add(uc);
            }
        }
        return unidades;
    }
    private List<Professor> buscarProfessoresPorAulaId(int aulaId) throws SQLException {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM Professor WHERE aula_id = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, aulaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Professor prof = new Professor();
                prof.setId(rs.getInt("id"));
                prof.setNome(rs.getString("nome"));
                prof.setLogin(rs.getString("login"));
                prof.setSenha(rs.getString("senha"));
                professores.add(prof);
            }
        }
        return professores;
    }

}