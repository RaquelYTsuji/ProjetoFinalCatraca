package com.senai.view;

import com.senai.controller.CursoController;
import com.senai.controller.SubTurmaController;
import com.senai.controller.TurmaController;
import com.senai.model.Curso;
import com.senai.model.SubTurma;
import com.senai.model.Turma;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TurmaView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TurmaController turmaController = new TurmaController();
        int opcao = 0;

        String menu = """
                \nMenu 
                    1 - Cadastrar Turma
                    2 - Exibir Turma
                    3 - Atualizar Turma
                    4 - Deletar Turma
                    5 - Sair
                """;

        do {
            System.out.println(menu);
            System.out.print("Digite o número:");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao){
                case 1:
                    System.out.println("\nPreencha os dados a seguir: ");
                    System.out.print("Id: ");
                    int idCadastrar = scanner.nextInt();
                    scanner.nextLine();
                    Turma turmaCadastrar = dados();
                    turmaCadastrar.setId(idCadastrar);

                    if(turmaController.cadastrarTurma(turmaCadastrar)){
                        System.out.println("Turma cadastrada com sucesso");
                    } else {
                        System.out.println("Nao foi possivel cadastrar");
                    }

                    break;
                case 2:
                    turmaController.listarTurmas().forEach(System.out::println);
                    break;
                case 3:
                    turmaController.listarTurmas().forEach(System.out::println);
                    System.out.print("\nEscolha uma turma para atualizar (id): ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Atualize as seguintes informações: ");
                    Turma turmaAtualizar = dados();
                    turmaAtualizar.setId(idAtualizar);

                    if(turmaController.atualizarTurmas(turmaAtualizar)){
                        System.out.println("Turma atualizada com sucesso");
                    } else {
                        System.out.println("Nao foi possivel atualizar");
                    }
                    break;
                case 4:
                    turmaController.listarTurmas().forEach(System.out::println);
                    System.out.print("\nEscolha uma turma para deletar (id): ");
                    int idDeletar = scanner.nextInt();
                    scanner.nextLine();
                    if(turmaController.deletarTurma(idDeletar)){
                        System.out.println("Turma deletada com sucesso");
                    } else {
                        System.out.println("Nao foi possivel deletar");
                    }
                    break;
                case 5:
                    System.out.println("Saindo do sistema");
                    scanner.close();
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }while (opcao != 5);
    }

    public static Turma dados(){
        Scanner scanner = new Scanner(System.in);
        SubTurmaController subTurmaController = new SubTurmaController();
        CursoController cursoController = new CursoController();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        cursoController.listarCurso().forEach(System.out::println);
        System.out.print("Curso: ");
        int idCursoCadastrar = scanner.nextInt();
        scanner.nextLine();
        Curso cursoCadastrar = cursoController.procurarCursos(idCursoCadastrar);

        System.out.print("SubTurmas: \n");
        subTurmaController.listarSubTurmas().forEach(System.out::println);
        List<SubTurma> subTurmas = new ArrayList<>();
        int idSubTurmaCadastrar = 0;
        do{
            System.out.print("Insira o id da subTurma a adicionar (ou sair com -1): ");
            idSubTurmaCadastrar = scanner.nextInt();
            SubTurma subTurma = subTurmaController.procurarSubTurmas(idSubTurmaCadastrar);
            subTurmas.add(subTurma);
        } while(idSubTurmaCadastrar != -1);
        scanner.nextLine();

        System.out.println("Data: ");
        System.out.print("Dia: ");
        int dia = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Mes: ");
        int mes = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Quantidade de semestres: ");
        int semestres = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Horario de entrada: ");
        System.out.print("Hora: ");
        int hora = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Minuto: ");
        int minuto = scanner.nextInt();
        scanner.nextLine();

        Turma turma = new Turma(0, nome, cursoCadastrar, subTurmas, LocalDate.of(ano, mes, dia), semestres, LocalTime.of(hora, minuto));
        return turma;
    }
}