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

public class AmbienteDAO {
    private List<Ambiente> ambientes;
    private final String FILE_PATH = "ambientes.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private List<Ambiente> carregar(){
        try (FileReader reader = new FileReader(FILE_PATH)){
            Type listType = new TypeToken<ArrayList<Ambiente>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public AmbienteDAO(){
        ambientes = carregar();
    }

    public void salvarJson(){
        try (FileWriter writer = new FileWriter(FILE_PATH)){
            gson.toJson(ambientes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salvar(Ambiente ambiente){
        ambientes.add(ambiente);
        salvarJson();
    }

    public List<Ambiente> listar(){
        return ambientes;
    }

    public void atualizar(Ambiente ambiente){
        ambientes.forEach(a -> {
            if(a.getId() == ambiente.getId()){
                a.setId(ambiente.getId());
                a.setNome(ambiente.getNome());
            }
        });
    }

    public boolean deletar(int id){
        Iterator<Ambiente> iterator = ambientes.iterator();
        while (iterator.hasNext()){
            Ambiente a = iterator.next();
            if(a.getId() == id){
                iterator.remove();
                salvarJson();
                return true;
            }
        }
        return false;
    }

    public Ambiente procurar(int id){
        Ambiente ambiente = null;
        for (Ambiente a : ambientes) {
            if (a.getId() == id) {
                ambiente = new Ambiente(a.getId(), a.getNome());
                break;
            }
        }
        return ambiente;
    }
}
