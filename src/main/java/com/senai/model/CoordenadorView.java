package com.senai.model;
import java.util.List;
import java.util.Scanner;

public class CoordenadorView {
    private CoordenadorController controller = new CoordenadorController();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CoordenadorView view = new CoordenadorView();
        view.exibirMenu();
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== Menu Coordenador ===");
            System.out.println("1 - Adicionar");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Deletar");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> adicionar();
                case 2 -> listar();
                case 3 -> buscar();
                case 4 -> atualizar();
                case 5 -> deletar();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void adicionar() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        controller.adicionarCoordenador(id, nome, email);

        System.out.println("Coordenador adicionado com sucesso!");
    }

    private void listar() {
        List<Coordenador> lista = controller.listarCoordenadores();
        for (Coordenador c : lista) {
            System.out.println(c);
        }
    }

    private void buscar() {
        System.out.print("ID do coordenador: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Coordenador c = controller.buscarCoordenador(id);
        if (c != null) {
            System.out.println(c);
        } else {
            System.out.println("Coordenador não encontrado.");
        }
    }

    private void atualizar() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();

        System.out.print("Novo email: ");
        String email = scanner.nextLine();

        controller.atualizarCoordenador(id, nome, email);
    }

    private void deletar() {
        System.out.print("ID a deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        controller.deletarCoordenador(id);
    }
}
