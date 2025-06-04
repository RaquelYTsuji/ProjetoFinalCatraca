package com.senai.model.dao.mysql;
import com.senai.model.Curso;
import com.senai.model.UnidadeCurricular;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CursoDAO {
    public void inserir(Curso curso) {
        String sqlCurso = "INSERT INTO Curso (id, titulo, cargaHoraria, tipo, tolerancia) VALUES (?, ?, ?, ?, ?)";
        String sqlUnidade = "INSERT INTO UnidadeCurricular (nome, cargaHoraria, curso_id) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmtCurso = conn.prepareStatement(sqlCurso);
             PreparedStatement stmtUnidade = conn.prepareStatement(sqlUnidade)) {

            stmtCurso.setInt(1, curso.getId());
            stmtCurso.setString(2, curso.getTitulo());
            stmtCurso.setInt(3, curso.getCargaHoraria());
            stmtCurso.setBoolean(4, curso.isTipo());
            stmtCurso.setInt(5, curso.getTolerancia());
            stmtCurso.executeUpdate();

            for (UnidadeCurricular uc : curso.getUnidadeCurriculares()) {//Esse for-each percorre cada objeto UnidadeCurricular (uc) da lista contida no curso
                //A lista é acessada via curso.getUnidadeCurriculares().
                stmtUnidade.setString(1, uc.getNome());//Preenche o primeiro parâmetro do PreparedStatement com o nome da unidade curricular.
                //Isso corresponde à coluna nome da tabela UnidadeCurricular.
                stmtUnidade.setString(2, uc.getCargaHoraria());//Preenche o segundo parâmetro com a carga horária da unidade curricular.
                stmtUnidade.setInt(3, curso.getId()); //Aqui é a chave estrangeira,
                stmtUnidade.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();//Captura e imprime qualquer erro de SQL.
        }
    }
    public void atualizar(Curso curso) {
            String sqlCurso = "UPDATE Curso SET titulo = ?, cargaHoraria = ?, tipo = ?, tolerancia = ? WHERE id = ?";
            String sqlDeleteUC = "DELETE FROM UnidadeCurricular WHERE curso_id = ?";
            String sqlInsertUC = "INSERT INTO UnidadeCurricular (nome, cargaHoraria, curso_id) VALUES (?, ?, ?)";

            try (Connection conn = ConexaoMySQL.conectar();
                 PreparedStatement stmtCurso = conn.prepareStatement(sqlCurso);
                 PreparedStatement stmtDeleteUC = conn.prepareStatement(sqlDeleteUC);
                 PreparedStatement stmtInsertUC = conn.prepareStatement(sqlInsertUC)) {

                // Atualiza dados do curso
                stmtCurso.setString(1, curso.getTitulo());
                stmtCurso.setInt(2, curso.getCargaHoraria());
                stmtCurso.setBoolean(3, curso.isTipo());
                stmtCurso.setInt(4, curso.getTolerancia());
                stmtCurso.setInt(5, curso.getId());
                stmtCurso.executeUpdate();

                // Remove unidades curriculares antigas
                stmtDeleteUC.setInt(1, curso.getId());
                stmtDeleteUC.executeUpdate();

                // Insere as novas
                for (UnidadeCurricular uc : curso.getUnidadeCurriculares()) {
                    stmtInsertUC.setString(1, uc.getNome());
                    stmtInsertUC.setString(2, uc.getCargaHoraria());
                    stmtInsertUC.setInt(3, curso.getId());
                    stmtInsertUC.executeUpdate();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    public void remover(int id) {
        String sqlDeleteUC = "DELETE FROM UnidadeCurricular WHERE curso_id = ?";
        String sqlDeleteCurso = "DELETE FROM Curso WHERE id = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmtUC = conn.prepareStatement(sqlDeleteUC);
             PreparedStatement stmtCurso = conn.prepareStatement(sqlDeleteCurso)) {

            // Remove unidades curriculares primeiro (por causa da FK)
            stmtUC.setInt(1, id);
            stmtUC.executeUpdate();

            stmtCurso.setInt(1, id);
            stmtCurso.executeUpdate();

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
        int cursoId = rs.getInt("id");

        return new Curso(
                cursoId,
                rs.getString("titulo"),
                buscarUnidadesCurricularesPorCursoId(cursoId),
                rs.getInt("tolerancia"),
                rs.getBoolean("tipo"),
                rs.getInt("cargaHoraria")
        );
    }
    private List<UnidadeCurricular> buscarUnidadesCurricularesPorCursoId(int cursoId) throws SQLException {
        //Metodo para buscar a unidade curricular
        List<UnidadeCurricular> unidades = new ArrayList<>();
        String sql = "SELECT * FROM UnidadeCurricular WHERE curso_id = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cursoId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    UnidadeCurricular uc = new UnidadeCurricular();
                    uc.setId(rs.getInt("id"));
                    uc.setNome(rs.getString("nome"));
                    uc.setCargaHoraria(rs.getString("cargaHoraria"));
                    unidades.add(uc);
                }
            }
        }

        return unidades;
    }
}
