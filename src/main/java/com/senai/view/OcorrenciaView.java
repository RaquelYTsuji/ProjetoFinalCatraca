package com.senai.view;

import com.senai.controller.OcorrenciaController;
import com.senai.model.Ocorrencia;

import java.time.LocalTime;
import java.util.Scanner;

public class OcorrenciaView {
    final OcorrenciaController controller = new OcorrenciaController();
    static Scanner scanner = new Scanner(System.in);
    final Scanner Scanner = new Scanner(System.in);
    public static void main(String[] args) {
       menu();
    }

    public static void menu() {
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
        LocalTime hora = scannerPromptHora("Hora de início (HH:mm): ");
        System.out.println(controller.cadastrarOcorrencias(id, hora));
    }

    private void atualizar() {
        int idHora = scannerPromptInt("ID do horário: ");
        int id = scannerPromptInt("Novo ID: ");
        LocalTime hora = scannerPromptHora("Nova hora de início (HH:mm): ");
        System.out.println(controller.atualizarOcorrencias(id, idHora));
    }

    private void remover() {
        int id = scannerPromptInt("ID do horário: ");
        System.out.println(controller.deletarOcorrencias(id));
    }

    public void listar() {
        for (Ocorrencia h : controller.listarOcorrencias()) {
            System.out.printf("ID: %d | Aluno ID: %d | Professor ID: %d | Início: %s\n",
                    h.getId(), h.getTipo(), h.getDataHora(), h.getDescricao());
        }
    }

    private int scannerPromptInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }

    private LocalTime scannerPromptHora(String msg) {
        System.out.print(msg);
        return LocalTime.parse(scanner.nextLine());
    }
}