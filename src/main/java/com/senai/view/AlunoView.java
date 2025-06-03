package com.senai.view;

import com.senai.model.Aluno;
import com.senai.controller.AlunoController;
import com.senai.util.CriptografiaUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AlunoView {
    private static final AlunoController controller = new AlunoController();
    private static final Scanner scanner = new Scanner(System.in); // Instanciando o Scanner no main

    public static void menuAluno() {
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
        System.out.println("Digite a data de nascimento (dd/MM/yyyy):");
        String data = scanner.nextLine();
        LocalDate localDate = formatDate(data);
        System.out.println("Digite o RFID:");
        String rfid = scanner.nextLine();

        Aluno aluno = new Aluno(nome, login, CriptografiaUtil.hash(senha), idAluno, rfid, localDate);
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
        System.out.println("Digite a nova data de nascimento (dd/MM/yyyy):");
        String data = scanner.nextLine();
        LocalDate localDate = formatDate(data);
        System.out.println("Digite o novo RFID:");
        String rfid = scanner.nextLine();

        Aluno aluno = new Aluno(nome, login, CriptografiaUtil.hash(senha), idAluno, rfid, localDate);
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

    private static LocalDate formatDate(String data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(data, formatter);
        return localDate;
    }
}
