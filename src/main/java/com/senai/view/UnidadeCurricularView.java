package com.senai.view;

import com.senai.controller.UnidadeCurricularController;

import java.util.Scanner;

public class UnidadeCurricularView {
    static Scanner scanner = new Scanner(System.in); //scanner estatico para ser visivel as todas pates do codigo.
    static UnidadeCurricularController unidadeCurricularController = new UnidadeCurricularController();
    public static void main(String[] args) { //Main para o codigo rodar
        //Aqui o professor/coodernandor cosegue se indentificar para
        String login = scannerPrompt("Digite o seu login: ");
        System.out.println(login);

        String opcao; //Opção em string para que entre todas os tipos de dados

        //Menu e suas opção, o nome de menuUC para fácil identificação
        String menuUC = """
                \n Opções:
                1 - Adicionar Unidade Curricular
                2 - Remover Unidade Curricular
                3 - Mudar Unidade Curricular 
                4 - Listar unidades
                0 - Retorna á tela anterior
                """;
//Switch com o menu
        do {
            System.out.print(menuUC);
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> cadastrar();
                case "2" -> remover();
                case "3" -> atualizar();
                case "5" -> listar();
                case "0" -> System.out.println("Retornando");
                default -> throw new IllegalArgumentException("Opção invalida"); //Aqui caso a pessoa coloque uma opçao que não o existe o codigo quebra e a pessoa volta ao começo do menu.
            }
        } while (!opcao.equals("0"));
    }

    private static void cadastrar(){
        String nomeUC = scannerPrompt("Digite o nome da nova Unidade");
        String disciplina = scannerPrompt("Digite as disciplinas:");
        String professorResponsavel = scannerPrompt("Qual é o professor responsavel por está UC?");
        String cargaHoraria = scannerPrompt("Qual é a carga horaria desta Unidade Curricular?");
        String metodoAvaliacao = scannerPrompt("Qual será o metodo de avaliação?");
      //  System.out.println(unidadeCurricularController.cadastarUC()(;nomeUC, disciplina, professorResponsavel, cargaHoraria, metodoAvaliacao;)
    }

    private static void atualizar(){
        String questionamento = scannerPrompt("O que deseja atualizar?");


    }

    private static void remover(){

    }

    private static void listar(){

    }

    private static String scannerPrompt(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private static int scannerPromptInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }
}
