package com.senai.model;

import com.google.gson.annotations.SerializedName;
public class Coordenador extends Usuario {

        public Coordenador(int id, String nome, String login, String senha) {
            super(id, nome, login, senha);
        }

        public Coordenador() {}

        @Override
        public String toString() {
            return "Coordenador{" +
                    "id=" + getId() +
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