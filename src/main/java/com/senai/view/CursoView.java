package com.senai.view;

import com.senai.controller.CursoController;
import com.senai.model.Curso;
import com.senai.model.UnidadeCurricular;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CursoView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CursoController cursoController = new CursoController();
        int opcao = 0;

        String menu = """
                \nMenu 
                    1 - Cadastrar Curso
                    2 - Exibir Curso
                    3 - Atualizar Curso
                    4 - Deletar Curso
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
                    Curso cursoCadastrar = dados();
                    cursoCadastrar.setId(idCadastrar);

                    if(cursoController.cadastrarCurso(cursoCadastrar)){
                        System.out.println("Curso cadastrado com sucesso");
                    } else {
                        System.out.println("Nao foi possivel cadastrar");
                    }

                    break;
                case 2:
                    cursoController.listarCurso().forEach(System.out::println);
                    break;
                case 3:
                    cursoController.listarCurso().forEach(System.out::println);
                    System.out.print("\nEscolha um curso para atualizar (id): ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Atualize as seguintes informações: ");
                    Curso cursoAtualizar = dados();
                    cursoAtualizar.setId(idAtualizar);

                    if(cursoController.atualizarCurso(cursoAtualizar)){
                        System.out.println("Curso atualizado com sucesso");
                    } else {
                        System.out.println("Nao foi possivel atualizar");
                    }
                    break;
                case 4:
                    cursoController.listarCurso().forEach(System.out::println);
                    System.out.print("\nEscolha um curso para deletar (id): ");
                    int idDeletar = scanner.nextInt();
                    scanner.nextLine();
                    if(cursoController.deletarCurso(idDeletar)){
                        System.out.println("Curso deletado com sucesso");
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

    public static Curso dados(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();

        //TODO: lista unidade curriculares
        System.out.print("UC: ");
        int idUC = scanner.nextInt();
        scanner.nextLine();

        List<UnidadeCurricular> unidadeCurriculares = new ArrayList<>();

        System.out.print("Carga Horaria: ");
        int cargaHoraria = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Tipo (CAI=true, Tecnico=false): ");
        boolean tipo = scanner.nextBoolean();

        System.out.print("Tolerancia: ");
        int tolerancia = scanner.nextInt();
        scanner.nextLine();

        Curso curso = new Curso(0, titulo, unidadeCurriculares, cargaHoraria, tipo, tolerancia);
        return curso;
    }
}