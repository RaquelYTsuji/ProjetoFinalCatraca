package com.senai.view;

import com.senai.controller.UnidadeCurricularController;
import com.senai.model.UnidadeCurricular;

import java.util.List;
import java.util.Scanner;

public class UnidadeCurricularView {
    //Essas duas classes estão estaticas para serem acessadas no codigo por completo.
    static Scanner scanner = new Scanner(System.in);
    static UnidadeCurricularController Controller = new UnidadeCurricularController();

    //PSVM para ser póssivel dar play
    public static void main(String[] args) {
        //Classe do scanner prompt para deixar o código mais clean.
        String login = scannerPrompt("Digite o seu login: ");
        System.out.println("Bem-vindo(a), " + login + "!");

        String opcao;
        String menuUC = """
                \n Opções:
                1 - Adicionar Unidade Curricular
                2 - Remover Unidade Curricular
                3 - Atualizar Unidade Curricular 
                4 - Listar Unidades Curriculares
                0 - Sair
                """;

        do {
            System.out.print(menuUC);
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> cadastrar();
                case "2" -> remover();
                case "3" -> atualizar();
                case "4" -> listar();
                case "0" -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (!opcao.equals("0"));
    }

    private static void cadastrar() {
        String nomeUC = scannerPrompt("Digite o nome da Unidade Curricular que deseja cadastrar: ");
        String disciplina = scannerPrompt("Digite as disciplinas presentes da UC: ");
        String professor = scannerPrompt("Professor responsável pela UC: ");
        String cargaHoraria = scannerPrompt("Carga horária: ");
        String metodoAvaliacao = scannerPrompt("Método de avaliação: ");

        Controller.cadastrarUC(nomeUC, disciplina, professor, cargaHoraria, metodoAvaliacao);
    }

    //Uso do Menu no atualizar para que a pessoa atualize apenas o que deseja.
    private static void atualizar() {
        String nomeUC = scannerPrompt("Digite o nome da Unidade Curricular que deseja atualizar: ");

        String opcao;
        String opcoesAtt = """
                \n Escolha o que deseja atualizar:
                1 - Nome da UC
                2 - Disciplinas
                3 - Professor Responsável
                4 - Método de Avaliação
                0 - Voltar
                """;

        do {
            System.out.print(opcoesAtt);
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> {
                    String novoNome = scannerPrompt("Novo nome da UC: ");
                    Controller.atualizarNomeUC(nomeUC, novoNome);
                }
                case "2" -> {
                    String novaDisciplina = scannerPrompt("Nova disciplina: ");
                    Controller.atualizarDisciplina(nomeUC, novaDisciplina);
                }
                case "3" -> {
                    String novoProfessor = scannerPrompt("Novo professor responsável: ");
                    Controller.atualizarProfessor(nomeUC, novoProfessor);
                }
                case "4" -> {
                    String novoMetodo = scannerPrompt("Novo método de avaliação: ");
                    Controller.atualizarMetodo(nomeUC, novoMetodo);
                }
                case "0" -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (!opcao.equals("0"));
    }

    //uso do boolean para haver algum tipo de confirmação ao remover.
    private static boolean remover() {
        String nomeUC = scannerPrompt("Digite o nome da UC a remover: ");
        boolean sucesso = Controller.removerUC(nomeUC);
        if (sucesso) {
            System.out.println("Unidade Curricular removida com sucesso.");
            return true;
        } else {
            System.out.println("Unidade Curricular não encontrada.");
        }
        return false;
    }

    private static void listar() {
        List<UnidadeCurricular> listaUC = Controller.listarUC();
        //if e else para procurar antes de listar as UCs
        if (listaUC.isEmpty()) {
            System.out.println("Nenhuma UC cadastrada.");
        } else {
            for (UnidadeCurricular uc : listaUC) {
                System.out.println(uc);
            }
        }
    }

    //Metodo para não repetir o scanner.nextline a todo momento
    private static String scannerPrompt(String print) {
        System.out.print(print);
        return scanner.nextLine();
    }
}
