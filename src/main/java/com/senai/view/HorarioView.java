package com.senai.view;

import com.senai.controller.HorarioController;
import com.senai.controller.TurmaController;
import com.senai.model.Aluno;
import com.senai.model.Horario;
import com.senai.model.Turma;

import java.time.LocalTime;
import java.util.Optional;
import java.util.Scanner;

public class HorarioView {
    private final Scanner scanner = new Scanner(System.in);
    private final HorarioController controller = new HorarioController();
    private final TurmaController turmaController = new TurmaController();

    public void menu() {
        String opcao;
        String menuHorario = """
                --- MENU DE HORÁRIOS ---
                
                    1. Cadastrar horário
                    2. Atualizar horário
                    3. Remover horário
                    4. Listar horários
                    0. Voltar
                    
                """;
        do {
            System.out.print(menuHorario);
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
        int idAluno = scannerPromptInt("ID do aluno: ");
        int idProfessor = scannerPromptInt("ID do professor: ");
        Optional<Turma> turma = turmaController.buscarTurmaDoAluno(idAluno);
        if(turma.isPresent()){
            System.out.println(controller.cadastrarHorario(idAluno, idProfessor, turma.get().getHorarioEntrada()));
        }
    }

    private void atualizar() {
        int id = scannerPromptInt("ID do horário: ");
        int idAluno = scannerPromptInt("Novo ID do aluno: ");
        int idProfessor = scannerPromptInt("Novo ID do professor: ");
        Optional<Turma> turma = turmaController.buscarTurmaDoAluno(idAluno);
        if(turma.isPresent()){
            System.out.println(controller.cadastrarHorario(idAluno, idProfessor, turma.get().getHorarioEntrada()));
        }
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

    public void listarDoAluno(Aluno aluno) {
        for (Horario h : controller.listarHorariosDoAluno(aluno.getId())) {
            System.out.printf("ID: %d | Aluno ID: %d | Professor ID: %d | Início: %s\n",
                    h.getId(), h.getIdAluno(), h.getIdProfessor(), h.getHoraInicio());
        }
    }

    private int scannerPromptInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }

    private LocalTime scannerPromptHora(String msg) {
        System.out.print(msg);
        return LocalTime.parse(scanner.nextLine());
    }
}

