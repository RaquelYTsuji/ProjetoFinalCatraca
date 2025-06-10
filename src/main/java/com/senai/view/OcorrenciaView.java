package com.senai.view;

import com.senai.controller.OcorrenciaController;
import com.senai.model.Ocorrencia;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class OcorrenciaView {
    final OcorrenciaController controller = new OcorrenciaController();
    static Scanner scanner = new Scanner(System.in);

    public void menu() {
        String opcao;
        String menuOcorrencia = """
                --- MENU DE OCORRÊNCIAS ---
                
                    1. Cadastrar Ocorrência
                    2. Atualizar Ocorrência
                    3. Remover Ocorrência
                    4. Listar Ocorrência
                    0. Voltar
                    
                """;
        do {
            System.out.print(menuOcorrencia);
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> cadastrar();
                case "2" -> atualizar();
                case "3" -> remover();
                case "4" -> listar();
                case "0" -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (!opcao.equals("0"));
    }

    private void cadastrar() {
        int id = scannerPromptInt("ID: ");
        int idAluno = scannerPromptInt("ID Aluno: ");
        LocalDateTime dataHora = scannerPromptHora("DATA e HORA de início dd/MM/yyyy HH:mm: ");
        String tipo = scannerPromptString("Tipo (Entrada/Saída): ");
        String descricao = scannerPromptString("Descrição: ");
        Ocorrencia ocorrencia = new Ocorrencia(id, idAluno, tipo, descricao, dataHora);
        System.out.println(controller.cadastrarOcorrencias(ocorrencia));
    }

    private void atualizar() {
        int id = scannerPromptInt("ID: ");
        int idAluno = scannerPromptInt("ID Aluno: ");
        LocalDateTime dataHora = scannerPromptHora("Nova DATA e HORA de início dd/MM/yyyy HH:mm: ");
        String tipo = scannerPromptString("Novo tipo de ocorrencia (Entrada/Saída): ");
        String descricao = scannerPromptString("Nova Descrição: ");
        Ocorrencia ocorrencia = new Ocorrencia(id, idAluno, tipo, descricao, dataHora );
        System.out.println(controller.atualizarOcorrencias(ocorrencia));
    }

    private void remover() {
        int id = scannerPromptInt("ID: ");
        System.out.println(controller.deletarOcorrencias(id));
    }

    public void listar() {
        for (Ocorrencia h : controller.listarOcorrencias()) {
            System.out.printf("ID: %d | ID Aluno: %d | Tipo: %s | Descrição: %s | dataHora: %s\n",
                    h.getId(), h.getIdAluno(), h.getTipo(), h.getDescricao(), h.getDataHora());
        }
    }

    public void aceitar() {
        int id = scannerPromptInt("ID: ");
        System.out.println(controller.aceitarOcorrencias(id));
    }

    private String scannerPromptString(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private int scannerPromptInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }

    private LocalDateTime scannerPromptHora(String msg) {
        System.out.print(msg);
        String entrada = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); //Converte a string para LocalDateTime usando o formato
        LocalDateTime dataHora = LocalDateTime.parse(entrada, formatter);
        return dataHora;
    }
}