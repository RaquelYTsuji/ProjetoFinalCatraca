package com.senai.view;

import com.senai.controller.JustificativaController;
import com.senai.model.Justificativa;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class JustificativaView {
    private final Scanner scanner = new Scanner(System.in);
    private final JustificativaController controller = new JustificativaController();

    public void menu() {
        String opcao;
        String menuJustificativa = """
                ---- MENU DE JUSTIFICATIVA ----
                
                    1. Cadastrar justificativa
                    2. Atualizar justificativa
                    3. Remover justificativa
                    4. Listar justificativas
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
        int id = scannerPromptInt("ID: ");
        int idAluno = scannerPromptInt("ID Aluno: ");
        String tipo = scannerPrompt("Tipo: ");
        String descricao = scannerPrompt("Descrição: ");
        int quantidadeDias = scannerPromptInt("Quantidade de dias: ");
        int prazoAceite = scannerPromptInt("Prazo de aceite: ");
        String anexo = scannerPrompt("Anexo (caminho ou nome): ");
        String status = scannerPrompt("Status: ");
        boolean cancelar = scannerPrompt("Cancelar? (true/false): ").equalsIgnoreCase("true");

        System.out.println(controller.cadastrarJustificativa(
                id, idAluno, tipo, descricao, quantidadeDias, prazoAceite, anexo, status, cancelar
        ));
    }

    private void atualizar() {
        int id = scannerPromptInt("ID da justificativa: ");
        int idAluno = scannerPromptInt("ID Aluno: ");
        String tipo = scannerPrompt("Novo tipo: ");
        String descricao = scannerPrompt("Nova descrição: ");
        LocalDateTime dataHora = LocalDateTime.now(); // Pode ser editável se quiser
        int quantidadeDias = scannerPromptInt("Nova quantidade de dias: ");
        int prazoAceite = scannerPromptInt("Novo prazo de aceite: ");
        String anexo = scannerPrompt("Novo anexo: ");
        String status = scannerPrompt("Novo status: ");
        boolean cancelar = scannerPrompt("Cancelar? (true/false): ").equalsIgnoreCase("true");

        System.out.println(controller.atualizarJustificativa(
                id, idAluno, tipo, descricao, dataHora, quantidadeDias, prazoAceite, anexo, status, cancelar
        ));
    }

    private void remover() {
        int id = scannerPromptInt("ID da justificativa para remover: ");
        System.out.println(controller.removerJustificativa(id));
    }

    public void listar() {
        List<Justificativa> lista = controller.listarJustificativas();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma justificativa cadastrada.");
        } else {
            for (Justificativa j : lista) {
                System.out.printf("""
                        -----------------------------
                        ID: %d
                        ID Aluno: %d
                        Tipo: %s
                        Descrição: %s
                        Data e hora: %s
                        Dias: %d
                        Prazo de aceite: %d
                        Anexo: %s
                        Status: %s
                        Cancelada: %s
                        -----------------------------
                        """,
                        j.getId(), j.getIdAluno(), j.getTipo(), j.getDescricao(), j.getDataHoraJustificatida(),
                        j.getQuantidadeDias(), j.getPrazoDeAceite(), j.getAnexo(), j.getStatus(),
                        j.isCancelar() ? "Sim" : "Não"
                );
            }
        }
    }

    public void aceitar() {
        int id = scannerPromptInt("ID da justificativa para aceitar: ");
        System.out.println(controller.aceitarJustificativa(id, "aceito"));
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
