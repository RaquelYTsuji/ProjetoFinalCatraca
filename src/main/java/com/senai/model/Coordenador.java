package com.senai.model;

import com.google.gson.annotations.SerializedName;
public class Coordenador extends Usuario {
        private int id;

        public Coordenador(int id, String nome, String login, String senha) {
            super(nome, login, senha);
            this.id = id;
        }

        public Coordenador() {}

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Coordenador{" +
                    "id=" + id +
                    ", nome='" + getNome() + '\'' +
                    ", login='" + getLogin() + '\'' +
                    ", senha='" + getSenha() + '\'' +
                    ", ativado=" + isAtivado() +
                    '}';
        }

        @Override
        public String getTipo() {
            return "Coordenador";
        }
    }