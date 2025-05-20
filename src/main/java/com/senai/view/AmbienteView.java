package com.senai.view;

import com.senai.controller.AmbienteController;
import com.senai.model.Ambiente;

import java.util.Scanner;

public class AmbienteView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AmbienteController ambienteController = new AmbienteController();
        int opcao = 0;

        String menu = """
                \nMenu 
                    1 - Cadastrar Ambiente
                    2 - Exibir Ambiente
                    3 - Atualizar Ambiente
                    4 - Deletar Ambiente
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

                    System.out.print("Nome: ");
                    String nomeCadastrar = scanner.nextLine();

                    if(ambienteController.cadastrarAmbiente(new Ambiente(idCadastrar, nomeCadastrar))){
                        System.out.println("Ambiente cadastrado com sucesso");
                    } else {
                        System.out.println("Nao foi possivel cadastrar");
                    }

                    break;
                case 2:
                    ambienteController.listarAmbientes().forEach(System.out::println);
                    break;
                case 3:
                    ambienteController.listarAmbientes().forEach(System.out::println);
                    System.out.print("\nEscolha um ambiente para atualizar (id): ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Atualize as seguintes informações: ");
                    System.out.print("Nome: ");
                    String nomeAtualizar = scanner.nextLine();

                    if(ambienteController.atualizarAmbiente(new Ambiente(idAtualizar, nomeAtualizar))){
                        System.out.println("Ambiente atualizado com sucesso");
                    } else {
                        System.out.println("Nao foi possivel atualizar");
                    }
                    break;
                case 4:
                    ambienteController.listarAmbientes().forEach(System.out::println);
                    System.out.print("\nEscolha um ambiente para deletar (id): ");
                    int idDeletar = scanner.nextInt();
                    scanner.nextLine();
                    if(ambienteController.deletarAmbiente(idDeletar)){
                        System.out.println("Ambiente deletado com sucesso");
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
}