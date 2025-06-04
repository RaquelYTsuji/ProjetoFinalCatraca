package com.senai.model.dao.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.senai.model.Curso;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CursoDAO {
    private List<Curso> cursos;
    private final String FILE_PATH = "cursos.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private List<Curso> carregar(){
        try (FileReader reader = new FileReader(FILE_PATH)){
            Type listType = new TypeToken<ArrayList<Curso>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public CursoDAO(){
        cursos = carregar();
    }

    public void salvarJson(){
        try (FileWriter writer = new FileWriter(FILE_PATH)){
            gson.toJson(cursos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salvar(Curso curso){
        cursos.add(curso);
        salvarJson();
    }

    public List<Curso> listar(){
        return cursos;
    }

    public void atualizar(Curso curso){
        cursos.forEach(c -> {
            if(c.getId() == curso.getId()){
                c.setId(curso.getId());
                c.setTitulo(curso.getTitulo());
                c.setUnidadeCurriculares(curso.getUnidadeCurriculares());
                c.setCargaHoraria(curso.getCargaHoraria());
                c.setTipo(curso.isTipo());
                c.setTolerancia(curso.getTolerancia());
                salvarJson();
            }
        });
    }

    public boolean deletar(int id){
        Iterator<Curso> iterator = cursos.iterator();
        while (iterator.hasNext()){
            Curso c = iterator.next();
            if(c.getId() == id){
                iterator.remove();
                salvarJson();
                return true;
            }
        }
        return false;
    }

    public Curso procurar(int id){
        Curso curso = null;
        for (Curso c : cursos) {
            if (c.getId() == id) {
                curso = new Curso(c.getId(), c.getTitulo(), c.getUnidadeCurriculares(), c.getCargaHoraria(), c.isTipo(), c.getTolerancia());
                break;
            }
        }
        return curso;
    }
}
