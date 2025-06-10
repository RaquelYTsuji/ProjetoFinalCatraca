package com.senai.model.dao.mysql;

import com.senai.model.Professor;
import com.senai.model.UnidadeCurricular;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UnidadeCurricularDAO {
        public void inserir(UnidadeCurricular unidadeCurricular) {
            String sqlUnidadeCurricular = "INSERT INTO UnidadeCurricular (nome, disciplina, cargaHoraria) VALUES (?, ?, ?)";
            String sqlProfessor = "INSERT INTO Professor (nome, login, senha, unidadeCurricular_id) VALUES (?, ?, ?, ?)";

            try (Connection conn = ConexaoMySQL.conectar();
                 PreparedStatement stmtUnidade = conn.prepareStatement(sqlUnidadeCurricular);
                 PreparedStatement stmtProfessor = conn.prepareStatement(sqlProfessor)) {


                stmtUnidade.setString(1, unidadeCurricular.getNome());
                stmtUnidade.setString(2, unidadeCurricular.getDisciplina());
                stmtUnidade.setString(3, unidadeCurricular.getCargaHoraria());
                stmtUnidade.executeUpdate();

                ResultSet generatedKeys = stmtUnidade.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int unidadeCurricularId = generatedKeys.getInt(1);

                    for (Professor professor : unidadeCurricular.getIdProfessor()) {
                        stmtProfessor.setString(1, professor.getNome());
                        stmtProfessor.setString(2, professor.getLogin());
                        stmtProfessor.setString(3, professor.getSenha());
                        stmtProfessor.setInt(4, unidadeCurricularId);
                        stmtProfessor.executeUpdate();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public void atualizar(UnidadeCurricular unidadeCurricular) {
            String sqlUnidade = "UPDATE UnidadeCurricular SET nome = ?, disciplina = ?, cargaHoraria = ? WHERE id = ?";
            String sqlDeleteProfessores = "DELETE FROM Professor WHERE unidadeCurricular_id = ?";
            String sqlProfessor = "INSERT INTO Professor (nome, login, senha) VALUES (?, ?, ?)";

            try (Connection conn = ConexaoMySQL.conectar();
                 PreparedStatement stmtUnidade = conn.prepareStatement(sqlUnidade);
                 PreparedStatement stmtDeleteProfessores = conn.prepareStatement(sqlDeleteProfessores);
                 PreparedStatement stmtProfessor = conn.prepareStatement(sqlProfessor)) {

                stmtUnidade.setString(1, unidadeCurricular.getNome());
                stmtUnidade.setString(2, unidadeCurricular.getDisciplina());
                stmtUnidade.setString(3, unidadeCurricular.getCargaHoraria());
                stmtUnidade.setString(4, unidadeCurricular.getMetodoAvaliacao());
                stmtUnidade.setInt(5, unidadeCurricular.getId());
                stmtUnidade.executeUpdate();

                stmtDeleteProfessores.setInt(1, unidadeCurricular.getId());
                stmtDeleteProfessores.executeUpdate();


                for (Professor professor : unidadeCurricular.getIdProfessor()) {
                    stmtProfessor.setString(1, professor.getNome());
                    stmtProfessor.setString(2, professor.getLogin());
                    stmtProfessor.setString(3, professor.getSenha());
                    stmtProfessor.setInt(4, unidadeCurricular.getId()); // A chave estrangeira para a UnidadeCurricular
                    stmtProfessor.executeUpdate();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public void remover(int id) {
            String sqlDeleteProfessores = "DELETE FROM Professor WHERE unidadeCurricular_id = ?";
            String sqlDeleteUnidadeCurricular = "DELETE FROM UnidadeCurricular WHERE id = ?";

            try (Connection conn = ConexaoMySQL.conectar();
                 PreparedStatement stmtDeleteProfessores = conn.prepareStatement(sqlDeleteProfessores);
                 PreparedStatement stmtDeleteUnidadeCurricular = conn.prepareStatement(sqlDeleteUnidadeCurricular)) {

                stmtDeleteProfessores.setInt(1, id);
                stmtDeleteProfessores.executeUpdate();

                stmtDeleteUnidadeCurricular.setInt(1, id);
                stmtDeleteUnidadeCurricular.executeUpdate();

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
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String disciplina = rs.getString("disciplina");
            String cargaHoraria = rs.getString("cargaHoraria");
            String metodoAvaliacao = rs.getString("metodoAvaliacao");

            List<Professor> professores = buscarProfessoresPorUnidade(id);

            return new UnidadeCurricular(id, nome, disciplina, professores, cargaHoraria, metodoAvaliacao);
        }

        private List<Professor> buscarProfessoresPorUnidade(int unidadeId) throws SQLException {
            List<Professor> professores = new ArrayList<>();
            String sql = "SELECT * FROM Professor WHERE unidadeCurricular_id = ?";
            try (Connection conn = ConexaoMySQL.conectar();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, unidadeId);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Professor professor = new Professor(
                            rs.getString("nome"),
                            rs.getString("login"),
                            rs.getString("senha"),
                            rs.getInt("id") // ID do professor
                    );
                    professores.add(professor);
                }

            }
            return professores;
        }

}
