package com.senai.controller;

import com.senai.model.Curso;
import com.senai.model.dao.json.CursoDAO;

import java.util.List;

public class CursoController {
    private CursoDAO cursoDAO = new CursoDAO();

    public boolean cadastrarCurso(Curso curso){
        if(curso != null){
            cursoDAO.salvar(curso);
            return true;
        }
        return false;
    }

    public List<Curso> listarCurso(){
        return cursoDAO.listar();
    }

    public boolean atualizarCurso(Curso curso){
        if(curso != null){
            cursoDAO.atualizar(curso);
            return true;
        }
        return false;
    }

    public boolean deletarCurso(int id){
        if(id >= 0){
            cursoDAO.deletar(id);
            return true;
        }
        return false;
    }

    public Curso procurarCursos(int id){
        return cursoDAO.procurar(id);
    }
}
