package com.senai.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senai.model.Professor;
import com.senai.model.UnidadeCurricular;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UnidadeCurricularDAO {
    private final String caminho = "unidadeCurricular.json";
    private final Gson gson = new Gson();
    private final List <UnidadeCurricular> UnidadesCurriculares;

    public UnidadeCurricularDAO(){
        UnidadesCurriculares = carregar();
    }

    private List<UnidadeCurricular> carregar() {
        try (FileReader reader = new FileReader(caminho)) {
            Type listType = new TypeToken<List<UnidadeCurricular>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    //Parte para inserir as novas unidades curriculares dentro da lista.
    public void inserir(UnidadeCurricular unidadeCurricular){
       UnidadesCurriculares.add(unidadeCurricular);
       salvar(UnidadesCurriculares);
    }

    public void salvar (List<UnidadeCurricular> unidadesCurriculares){
        for (int i = 0; i < unidadesCurriculares.size(); i++) {
            if (unidadesCurriculares.get(i).getDisciplina() == unidadesCurriculares.get()) {
                unidadesCurriculares.set(i j);
                break;
            }
        }
        salvar(unidadesCurriculares);
    }
    }

    //public void salvarJson(){
    //    try (FileWriter writer = new FileWriter(FILE_PATH)){
    //       gson.toJson(supervisores, writer);
    //  }catch (IOException e){
    //       e.printStackTrace();
    //   }
    // }


    public void atualizar(){

    }

    public boolean remover(UnidadeCurricular unidadeCurricular, String disciplina){
        Iterator<UnidadeCurricular> iterator = unidadeCurricular.listarUnidades().iterator();
        while (iterator.hasNext());
        UnidadeCurricular uc = iterator.next();
        if (uc.getDisciplina() == disciplina);
        iterator.remove();
        //unidadeCurricular.(Uc -> Uc.() == );
        //salvar(unidadeCurricular);
    }

    public void listarUnidades(){

    }

}
