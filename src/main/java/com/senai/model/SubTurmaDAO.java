package com.senai.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SubTurmaDAO {
    private List<SubTurma> subTurmas;
    private final String FILE_PATH = "subTurmas.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private List<SubTurma> carregar(){
        try (FileReader reader = new FileReader(FILE_PATH)){
            Type listType = new TypeToken<ArrayList<SubTurma>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public SubTurmaDAO(){
        subTurmas = carregar();
    }

    public void salvarJson(){
        try (FileWriter writer = new FileWriter(FILE_PATH)){
            gson.toJson(subTurmas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salvar(SubTurma subTurma){
        subTurmas.add(subTurma);
        salvarJson();
    }

    public List<SubTurma> listar(){
        return subTurmas;
    }

    public void atualizar(SubTurma subTurma){
        subTurmas.forEach(s -> {
            if(s.getId() == subTurma.getId()){
                s.setId(subTurma.getId());
                s.setNome(subTurma.getNome());
                s.setAlunos(subTurma.getAlunos());
                salvarJson();
            }
        });
    }

    public boolean deletar(int id){
        Iterator<SubTurma> iterator = subTurmas.iterator();
        while (iterator.hasNext()){
            SubTurma s = iterator.next();
            if(s.getId() == id){
                iterator.remove();
                salvarJson();
                return true;
            }
        }
        return false;
    }
}
