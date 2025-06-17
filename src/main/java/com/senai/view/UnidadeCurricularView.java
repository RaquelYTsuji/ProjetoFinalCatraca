package com.senai.view;

import com.senai.controller.ProfessorController;
import com.senai.controller.UnidadeCurricularController;
import com.senai.model.Professor;
import com.senai.model.SubTurma;
import com.senai.model.Professor;
import com.senai.model.UnidadeCurricular;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UnidadeCurricularView {
    //Essas duas classes estão estáticas para serem acessadas no código por completo.
    static Scanner scanner = new Scanner(System.in);
    static UnidadeCurricularController Controller = new UnidadeCurricularController();
    static ProfessorController professorController = new ProfessorController();

    public static void main(String[] args) {
        String opcao;
        //Menu para interação com o usuário, onde ele escolhe o que deseja fazer.
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
        ProfessorController professorController = new ProfessorController();

        String idUC = scannerPrompt("Digite o id da sua Unidade Curricular: ");
        String nomeUC = scannerPrompt("Digite o nome da Unidade Curricular: ");
        String disciplina = scannerPrompt("Digite as disciplinas presentes da UC: ");
        System.out.print("Professores: \n");
        professorController.listarProfessores().forEach(System.out::println);
        List<Professor> professores = new ArrayList<>();
        int idProfessor = 0;
        do{
            System.out.print("Insira o id do professor a adicionar (ou sair com -1): ");
            idProfessor = scanner.nextInt();
            Professor professor = professorController.procurarProfessorPorID(idProfessor);
            professores.add(professor);
        } while(idProfessor != -1);
        scanner.nextLine();
        String cargaHoraria = scannerPrompt("Carga horária: ");

        Controller.cadastrarUC(Integer.parseInt(idUC), nomeUC, disciplina, professores, cargaHoraria);
    }

    //No atualizar, o uso de Menu, foi escolhido para que o usuário escolha o que ele quer editar.
    private static void atualizar() {
        String nomeUC = scannerPrompt("Digite o nome da UC que deseja atualizar: ");
        ProfessorController professorController = new ProfessorController();

        String opcao;
        String opcoesAtt = """
                \n Escolha o que deseja atualizar:
                1 - Nome da UC
                2 - Disciplinas
                3 - Professor Responsável
                0 - Voltar
                """;

        do {
            System.out.print(opcoesAtt);
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    Controller.atualizarNomeUC(nomeUC, scannerPrompt("Novo nome: "));
                    break;
                case "2" :
                    Controller.atualizarDisciplina(nomeUC, scannerPrompt("Nova disciplina: "));
                    break;
                case "3" :
                    System.out.print("Professores: \n");
                    professorController.listarProfessores().forEach(System.out::println);
                    List<Professor> professores = new ArrayList<>();
                    int idProfessor = 0;
                    do{
                        System.out.print("Insira o id do professor a adicionar (ou sair com -1): ");
                        idProfessor = scanner.nextInt();
                        Professor professor = professorController.procurarProfessorPorID(idProfessor);
                        professores.add(professor);
                    } while(idProfessor != -1);
                    scanner.nextLine();
                    break;
                case "0" :
                    System.out.println("Voltando...");
                    break;
                default :
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (!opcao.equals("0"));
    }

    private static boolean remover() {
        String nome = scannerPrompt("Digite o nome da UC a remover: ");
        String confirmacao = scannerPrompt("Tem certeza que deseja remover \"" + nome + "\"? (s/n): ");
        boolean sucesso = Controller.removerUC(nome, confirmacao);

        if (sucesso) {
            System.out.println("UC removida com sucesso.");
            return true;
        } else {
            System.out.println("Remoção cancelada ou UC não encontrada.");
            return false;
        }
    }

    private static void listar() {
        List<UnidadeCurricular> listaUC = Controller.listarUC();
        if (listaUC.isEmpty()) {
            System.out.println("Nenhuma UC cadastrada.");
        } else {
            listaUC.forEach(System.out::println);
        }
    }

    private static String scannerPrompt(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
