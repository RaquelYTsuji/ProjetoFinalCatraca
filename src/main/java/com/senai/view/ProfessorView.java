package com.senai.view;

import com.senai.model.Professor;
import com.senai.controller.ProfessorController;

import java.util.Scanner;

public class ProfessorView {
    public static void main(String[] args) {
        final ProfessorController controller = new ProfessorController();
        Scanner scanner = new Scanner(System.in);
        menuProfessor(scanner, controller);
    }

    public static void menuProfessor(Scanner scanner, ProfessorController controller) {
        String opcao;
        String menuProfessor = """
                --- MENU DE Professor---

                    1. Cadastrar Professor
                    2. Atualizar Professor
                    3. Remover Professor
                    4. Listar Professor
                    0. Voltar

                """;

        do {
            System.out.print(menuProfessor);
            opcao = scanner.nextLine();
            switch (opcao) {
                case "1" -> cadastrarProfessor(scanner, controller);
                case "2" -> atualizarProfessor(scanner, controller);
                case "3" -> deletarProfessor(scanner, controller);
                case "4" -> listarProfessores(controller);
                case "0" -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (!opcao.equals("0"));
    }

    public static void cadastrarProfessor(Scanner scanner,ProfessorController controller) {
        System.out.println("Digite o nome do professor:");
        String nome = scanner.nextLine();
        System.out.println("Digite o login do professor:");
        String login = scanner.nextLine();
        System.out.println("Digite a senha do professor:");
        String senha = scanner.nextLine();
        System.out.println("Digite o ID do professor:");
        int idProfessor = Integer.parseInt(scanner.nextLine());
        System.out.println("Digite a unidade curricular do professor:");
        String unidadeCurricular = scanner.nextLine();

        Professor professor = new Professor(nome, login, senha,idProfessor, unidadeCurricular);
        if (controller.cadastrarProfessor(professor)) {
            System.out.println("Professor cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar o professor.");
        }
    }

    public static void atualizarProfessor(Scanner scanner, ProfessorController controller) {
        System.out.println("Digite o ID do professor que deseja atualizar:");
        int idProfessor = Integer.parseInt(scanner.nextLine());
        System.out.println("Digite o novo nome do professor:");
        String nome = scanner.nextLine();
        System.out.println("Digite o novo login do professor:");
        String login = scanner.nextLine();
        System.out.println("Digite a nova senha do professor:");
        String senha = scanner.nextLine();
        System.out.println("Digite a nova unidade curricular do professor:");
        String unidadeCurricular = scanner.nextLine();

        Professor professor= new Professor(nome, login, senha, idProfessor,unidadeCurricular);
        if (controller.atualizarProfessor(professor)) {
            System.out.println("Professor atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar o professor.");
        }
    }
    public static void deletarProfessor(Scanner scanner, ProfessorController controller) {
        System.out.println("Digite o ID do professor que deseja remover:");
        int idProfessor = Integer.parseInt(scanner.nextLine());


        if (controller.deletarProfessorPorID(idProfessor)) {
            System.out.println("Professor removido com sucesso!");
        } else {
            System.out.println("Erro ao remover o professor.");
        }
    }


    public static void listarProfessores(ProfessorController controller) {
        System.out.println("Lista de professores:");
        for (Professor professor : controller.listarProfessores()) {
            System.out.println(professor);
        }
    }
}
