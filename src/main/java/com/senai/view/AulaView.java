package com.senai.view;

import com.senai.controller.*;
import com.senai.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AulaView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AulaController aulaController = new AulaController();
        int opcao;

        String menu = """
                \nMenu 
                    1 - Cadastrar Aula
                    2 - Exibir Aula
                    3 - Atualizar Aula
                    4 - Deletar Aula
                    5 - Sair
                """;

        do {
            System.out.println(menu);
            System.out.print("Digite o número: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao){
                case 1 -> {
                    System.out.println("\nPreencha os dados a seguir: ");
                    System.out.print("Id: ");
                    int idCadastrar = scanner.nextInt();
                    scanner.nextLine();
                    Aula aulaCadastrar = dados();
                    aulaCadastrar.setId(idCadastrar);

                    if (aulaController.cadastarAula(aulaCadastrar)) {
                        System.out.println("Aula cadastrada com sucesso");
                    } else {
                        System.out.println("Não foi possível cadastrar");
                    }
                }
                case 2 -> aulaController.listarAulas().forEach(System.out::println);
                case 3 -> {
                    aulaController.listarAulas().forEach(System.out::println);
                    System.out.print("\nEscolha uma aula para atualizar (id): ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Atualize as seguintes informações: ");
                    Aula aulaAtualizar = dados();
                    aulaAtualizar.setId(idAtualizar);

                    if (aulaController.atualizarAulas(aulaAtualizar)) {
                        System.out.println("Aula atualizada com sucesso");
                    } else {
                        System.out.println("Não foi possível atualizar");
                    }
                }
                case 4 -> {
                    aulaController.listarAulas().forEach(System.out::println);
                    System.out.print("\nEscolha uma aula para deletar (id): ");
                    int idDeletar = scanner.nextInt();
                    scanner.nextLine();
                    if (aulaController.deletarAula(idDeletar)) {
                        System.out.println("Aula deletada com sucesso");
                    } else {
                        System.out.println("Não foi possível deletar");
                    }
                }
                case 5 -> {
                    System.out.println("Saindo do sistema");
                    scanner.close();
                }
                default -> System.out.println("Opção inválida");
            }
        } while (opcao != 5);
    }

    public static Aula dados() {
        Scanner scanner = new Scanner(System.in);
        ProfessorController professorController = new ProfessorController();
        UnidadeCurricularController unidadeCurricularController = new UnidadeCurricularController();

        System.out.print("Nome da Aula: ");
        String nome = scanner.nextLine();

        // Coletando professor
        professorController.listarProfessores().forEach(System.out::println);
        System.out.print("Escolha o Professor (id): ");
        int idProfessorCadastrar = scanner.nextInt();
        scanner.nextLine();
        Professor professorCadastrar = professorController.procurarProfessorPorID(idProfessorCadastrar);
        List<Professor> professores = new ArrayList<>();
        if (professorCadastrar != null) {
            professores.add(professorCadastrar);
        }

        // Coletando UCs
        List<UnidadeCurricular> unidadesCurriculares = new ArrayList<>();
        System.out.println("Unidades Curriculares disponíveis:");
        unidadeCurricularController.listarUC().forEach(System.out::println);
        int idUcCadastrar;
        do {
            System.out.print("Insira o ID da unidade curricular a adicionar (ou -1 para encerrar): ");
            idUcCadastrar = scanner.nextInt();
            if (idUcCadastrar != -1) {
                UnidadeCurricular uc = unidadeCurricularController.procurarUCPorId(idUcCadastrar);
                if (uc != null) {
                    unidadesCurriculares.add(uc);
                    System.out.println("Adicionada: " + uc);
                } else {
                    System.out.println("Unidade Curricular não encontrada.");
                }
            }
        } while (idUcCadastrar != -1);
        scanner.nextLine(); // consumir quebra de linha pendente

        // Coletar data (opcional se quiser usar em outra lógica depois)
        System.out.print("Data (dd/MM/yyyy): ");
        String data = scanner.nextLine();
        LocalDate localDate = formatDate(data); // caso deseje guardar depois


        // Coletando horário
        System.out.print("Horário da Aula (HH:mm): ");
        String hora = scanner.nextLine();
        LocalTime localTime = formatTime(hora);

        // Criando a Aula
        return new Aula(0, nome, professores, unidadesCurriculares, localTime);
    }

    private static LocalDate formatDate(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(data, formatter);
    }

    private static LocalTime formatTime(String hora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(hora, formatter);
    }
}

