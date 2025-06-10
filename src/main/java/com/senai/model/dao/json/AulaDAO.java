package com.senai.model.dao.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.senai.model.Aula;
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

public class AulaDAO {
    private List<Aula> aulas;
    private final String FILE_PATH = "aulas.json";
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
            .create();

    private List<Aula> carregar() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Aula>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public AulaDAO() {
        aulas = carregar();
    }

    public void salvarJson() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(aulas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salvar(Aula aula) {
        aulas.add(aula);
        salvarJson();
    }
    public List <Aula> listar (){
        return aulas;
    }

    public void atualizar(Aula aulaAtualizada) {
        if (aulaAtualizada == null) return;
        for (Aula aula : aulas) {
            if (aula.getId() == aulaAtualizada.getId()) {
                aula.setAulaCurricular(aulaAtualizada.getAulaCurricular());
                aula.setProfessores(aulaAtualizada.getProfessores());
                aula.setUnidadeCurriculares(aulaAtualizada.getUnidadeCurriculares());
                aula.setUnidadeHorario(aulaAtualizada.getUnidadeHorario());
                salvarJson();
                break;  // Encerra o loop após encontrar e atualizar a aula
            }
        }
    }

    public boolean deletar(int id){
        Iterator<Aula> iterator = aulas.iterator();
        while (iterator.hasNext()){
            Aula a = iterator.next();
            if(a.getId() == id){
                iterator.remove();
                salvarJson();
                return true;
            }
        }
        return false;

    }
}
