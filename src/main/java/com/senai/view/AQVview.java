package com.senai.view;

import com.senai.controller.AQVcontroller;
import com.senai.model.AQV;
import com.senai.util.CriptografiaUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AQVview {
    private AQVcontroller controller = new AQVcontroller();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        AQVview view = new AQVview();
        view.exibirMenu();
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== Menu AQV ===");
            System.out.println("1 - Adicionar");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Deletar");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> adicionar();
                case 2 -> listar();
                case 3 -> buscar();
                case 4 -> atualizar();
                case 5 -> deletar();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void adicionar() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Login: ");
        String login = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        controller.adicionarAQV(id, nome, login, CriptografiaUtil.hash(senha));

        System.out.println("AQV adicionado com sucesso!");
    }

    private void listar() {
        List<AQV> lista = controller.listarAQV();
        for (AQV c : lista) {
            System.out.println(c);
        }
    }

    private void buscar() {
        System.out.print("ID do(a) AQV: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        AQV c = controller.buscarAQV(id);
        if (c != null) {
            System.out.println(c);
        } else {
            System.out.println("AQV não encontrado.");
        }
    }

    private void atualizar() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();

        System.out.print("Novo login: ");
        String login = scanner.nextLine();

        System.out.print("Nova senha: ");
        String senha = scanner.nextLine();

        controller.atualizarAQV(id, nome, login, CriptografiaUtil.hash(senha));
    }

    private void deletar() {
        System.out.print("ID a deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
    }

        private void verificarEAvisarSeAtrasado() {
            System.out.print("ID do AQV responsável: ");
            int idAQV = scanner.nextInt();
            scanner.nextLine();

            AQV aqv = controller.buscarAQV(idAQV);
            if (aqv == null) {
                System.out.println("AQV não encontrado.");
                return;
            }

            System.out.print("Nome do aluno: ");
            String nomeAluno = scanner.nextLine();

            System.out.print("Horário de chegada (HH:mm): ");
            String chegadaStr = scanner.nextLine();

            System.out.print("Horário limite (HH:mm): ");
            String limiteStr = scanner.nextLine();

            try {
                LocalTime horarioChegada = LocalTime.parse(chegadaStr, DateTimeFormatter.ofPattern("HH:mm"));
                LocalTime horarioLimite = LocalTime.parse(limiteStr, DateTimeFormatter.ofPattern("HH:mm"));

                controller.verificarEAvisarSeAtrasado(nomeAluno, horarioChegada, horarioLimite, aqv);
            } catch (Exception e) {
                System.out.println("Erro ao interpretar os horários. Use o formato HH:mm.");
            }
        }
        }






