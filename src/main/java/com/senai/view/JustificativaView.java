package com.senai.view;

import com.senai.model.justificativa.Justificativa;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class JustificativaView {

    private final Scanner scanner = new Scanner(System.in);
    //private final HorarioController controller = new HorarioController();
    public static void main(String[] args) {
        JustificativaView justificativaView=new JustificativaView();
        justificativaView.menu();
    }
    public void menu() {
        String opcao;
        String menuJustificativa = """
                ---- MENU DE JUSTIFICATIVA ----
                    1. Cadastrar justificativa
                    2. Atualizar Justificativa
                    3. Remover justificativa
                    4. Listar justificativa
                    0. Voltar
                    
                """;
        do {
            System.out.print(menuJustificativa);
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> cadastrar();
                case "2" -> atualizar();
                case "3" -> remover();
                case "4" -> listar();
                case "0" -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (!opcao.equals("0"));
    }

    private void cadastrar() {
        int id = scannerPromptInt("ID do horário: ");
        String tipo = scannerPrompt("ID do aluno: ");
        String descricao = scannerPrompt("ID do professor: ");
        LocalDateTime dataHoraJustificada = scannerPromptHora("Hora de início (HH:mm): ");
        Justificativa justificativa = new Justificativa(id,tipo,descricao,)
        controller.cadastrarHorario(idAluno, idProfessor, hora);
    }

    private void atualizar() {
        int id = scannerPromptInt("ID do horário: ");
        int idAluno = scannerPromptInt("Novo ID do aluno: ");
        int idProfessor = scannerPromptInt("Novo ID do professor: ");
        LocalTime hora = scannerPromptHora("Nova hora de início (HH:mm): ");
        System.out.println(controller.atualizarHorario(id, idAluno, idProfessor, hora));
    }

    private void remover() {
        int id = scannerPromptInt("ID do horário: ");
        System.out.println(controller.removerHorario(id));
    }

    public void listar() {
        for (Horario h : controller.listarHorarios()) {
            System.out.printf("ID: %d | Aluno ID: %d | Professor ID: %d | Início: %s\n",
                    h.getId(), h.getIdAluno(), h.getIdProfessor(), h.getHoraInicio());
        }
    }


    private LocalTime scannerPromptHora(String msg) {
        System.out.print(msg);
        return LocalTime.parse(scanner.nextLine());
    }


    private String scannerPrompt(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private int scannerPromptInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }



}


