package com.senai.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.senai.util.LocalDateAdapter;
import com.senai.util.LocalTimeAdapter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TurmaDAO {
    private List<Turma> turmas;
    private final String FILE_PATH = "turmas.json";
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
            .create();

    private List<Turma> carregar(){
        try (FileReader reader = new FileReader(FILE_PATH)){
            Type listType = new TypeToken<ArrayList<Turma>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public TurmaDAO(){
        turmas = carregar();
    }

    public void salvarJson(){
        try (FileWriter writer = new FileWriter(FILE_PATH)){
            gson.toJson(turmas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salvar(Turma turma){
        turmas.add(turma);
        salvarJson();
    }

    public List<Turma> listar(){
        return turmas;
    }

    public void atualizar(Turma turma){
        turmas.forEach(t -> {
            if(t.getId() == turma.getId()){
                t.setId(turma.getId());
                t.setNome(turma.getNome());
                t.setCurso(turma.getCurso());
                t.setSubTurmas(turma.getSubTurmas());
                t.setDataInicio(turma.getDataInicio());
                t.setQuantidadeSemestre(turma.getQuantidadeSemestre());
                t.setHorarioEntrada(turma.getHorarioEntrada());
                salvarJson();
            }
        });
    }

    public boolean deletar(int id){
        Iterator<Turma> iterator = turmas.iterator();
        while (iterator.hasNext()){
            Turma t = iterator.next();
            if(t.getId() == id){
                iterator.remove();
                salvarJson();
                return true;
            }
        }
        return false;
    }
}
