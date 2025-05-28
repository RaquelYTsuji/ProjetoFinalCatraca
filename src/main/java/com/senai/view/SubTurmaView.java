package com.senai.view;

import com.senai.controller.SubTurmaController;
import com.senai.model.Aluno;
import com.senai.model.SubTurma;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubTurmaView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SubTurmaController subTurmaController = new SubTurmaController();
        int opcao = 0;

        String menu = """
                \nMenu 
                    1 - Cadastrar SubTurma
                    2 - Exibir SubTurma
                    3 - Atualizar SubTurma
                    4 - Deletar SubTurma
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
                    SubTurma subTurmaCadastrar = dados();
                    subTurmaCadastrar.setId(idCadastrar);

                    if(subTurmaController.cadastrarSubTurma(subTurmaCadastrar)){
                        System.out.println("SubTurma cadastrada com sucesso");
                    } else {
                        System.out.println("Nao foi possivel cadastrar");
                    }

                    break;
                case 2:
                    subTurmaController.listarSubTurmas().forEach(System.out::println);
                    break;
                case 3:
                    subTurmaController.listarSubTurmas().forEach(System.out::println);
                    System.out.print("\nEscolha uma sub turma para atualizar (id): ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Atualize as seguintes informações: ");
                    SubTurma subTurmaAtualizar = dados();
                    subTurmaAtualizar.setId(idAtualizar);

                    if(subTurmaController.atualizarSubTurmas(subTurmaAtualizar)){
                        System.out.println("SubTurma atualizada com sucesso");
                    } else {
                        System.out.println("Nao foi possivel atualizar");
                    }
                    break;
                case 4:
                    subTurmaController.listarSubTurmas().forEach(System.out::println);
                    System.out.print("\nEscolha uma sub turma para deletar (id): ");
                    int idDeletar = scanner.nextInt();
                    scanner.nextLine();
                    if(subTurmaController.deletarSubTurma(idDeletar)){
                        System.out.println("Sub turma deletado com sucesso");
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

    public static SubTurma dados(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        //TODO: lista alunos
        System.out.print("Alunos: ");
        int idAlunoCadastrar = scanner.nextInt();
        scanner.nextLine();

        List<Aluno> alunosCadastrar = new ArrayList<>();

        SubTurma subTurma = new SubTurma(0, nome, alunosCadastrar);
        return subTurma;
    }
}