package com.senai.view;

import com.senai.model.Aluno;
import com.senai.model.AlunoController;

import java.util.Scanner;

public class AlunoView {
    public static void main(String[] args) {
        final AlunoController controller = new AlunoController();
        Scanner scanner = new Scanner(System.in); // Instanciando o Scanner no main
        menuAluno(scanner, controller); // Passando o scanner e o controller como parâmetros para o menuAluno
    }

    public static void menuAluno(Scanner scanner, AlunoController controller) {
        String opcao;
        String menuAluno = """
                --- MENU DE ALUNO ---

                    1. Cadastrar Aluno
                    2. Atualizar Aluno
                    3. Remover Aluno
                    4. Listar Aluno
                    0. Voltar

                """;

        do {
            System.out.print(menuAluno);
            opcao = scanner.nextLine();
            switch (opcao) {
                case "1" -> cadastrarAluno(scanner, controller);
                case "2" -> atualizarAluno(scanner, controller);
                case "3" -> deletarAluno(scanner, controller);
                case "4" -> listarAlunos(controller);
                case "0" -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (!opcao.equals("0"));
    }

    public static void cadastrarAluno(Scanner scanner, AlunoController controller) {
        System.out.println("Digite o nome do aluno:");
        String nome = scanner.nextLine();
        System.out.println("Digite o login do aluno:");
        String login = scanner.nextLine();
        System.out.println("Digite a senha do aluno:");
        String senha = scanner.nextLine();
        System.out.println("Digite o ID do aluno:");
        int idAluno = Integer.parseInt(scanner.nextLine());

        Aluno aluno = new Aluno(nome, login, senha, idAluno);
        if (controller.cadastrarAluno(aluno)) {
            System.out.println("Aluno cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar o aluno.");
        }
    }

    // Método para atualizar um aluno
    public static void atualizarAluno(Scanner scanner, AlunoController controller) {
        System.out.println("Digite o ID do aluno que deseja atualizar:");
        int idAluno = Integer.parseInt(scanner.nextLine());
        System.out.println("Digite o novo nome do aluno:");
        String nome = scanner.nextLine();
        System.out.println("Digite o novo login do aluno:");
        String login = scanner.nextLine();
        System.out.println("Digite a nova senha do aluno:");
        String senha = scanner.nextLine();

        Aluno aluno = new Aluno(nome, login, senha, idAluno);
        if (controller.atualizarAluno(aluno)) {
            System.out.println("Aluno atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar o aluno.");
        }
    }
    public static void deletarAluno(Scanner scanner, AlunoController controller) {
        System.out.println("Digite o ID do aluno que deseja remover:");
        int idAluno = Integer.parseInt(scanner.nextLine());

        if (controller.deletarAlunoPorID(idAluno)) {
            System.out.println("Aluno removido com sucesso!");
        } else {
            System.out.println("Erro ao remover o aluno.");
        }
    }


    public static void listarAlunos(AlunoController controller) {
        System.out.println("Lista de alunos:");
        for (Aluno aluno : controller.listarAlunos()) {
            System.out.println(aluno);
        }
    }
}
