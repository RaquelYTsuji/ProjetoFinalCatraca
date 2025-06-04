package com.senai.view;

import com.senai.WebSocket.WebSocketClienteConsole;
import com.senai.controller.AlunoController;
import com.senai.controller.ProfessorController;
import com.senai.model.*;
import com.senai.util.CriptografiaUtil;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        CoordenadorDAO coordenadorDAO = new CoordenadorDAO();
        if(coordenadorDAO.listarTodos().isEmpty()){
            criarCoordenador();
        }
        logar();
    }

    public static void logar(){
        Optional<Usuario> usuarioLogado = new LoginView().exibirLogin();
        usuarioLogado.ifPresent(Main::redirecionarMenu);
    }

    public static void criarCoordenador(){
        Coordenador coordenador = new Coordenador(1,"Coordenador","coordenador", CriptografiaUtil.hash("1234"));
        CoordenadorDAO coordenadorDAO = new CoordenadorDAO();
        if(coordenadorDAO.listarTodos().isEmpty()){
            coordenadorDAO.criar(coordenador);
        }
    }

    private static void redirecionarMenu(Usuario usuario) {
        switch (usuario.getLogin()) {
            case "Coordenador" -> menuCoordenador((Coordenador) usuario);
            case "AQV" -> menuAQV((AQV) usuario);
            case "Professor" -> menuProfessor((Professor) usuario);
            case "Aluno" -> menuAluno((Aluno) usuario);
            default -> System.out.println("Tipo de usuário desconhecido.");
        }
    }

    private static void menuCoordenador(Coordenador coordenador) {
        CoordenadorView coordenadorView = new CoordenadorView();
        AlunoView alunoView = new AlunoView();
        AlunoController aController = new AlunoController();
        ProfessorView professorView = new ProfessorView();
        ProfessorController pController = new ProfessorController();
        AQVview aqvView = new AQVview();

        System.out.printf("Bem vind@ %s \n", coordenador.getNome());
        executarMenu("""               
                    ===== MENU AQV =====
                    1. Gerenciar Aluno
                    2. Gerenciar Professor
                    3. Gerenciar AQV
                    4. Deslogar
                    0. Sair
                    """,
                opcao -> {
                    switch (opcao) {
                        case "1" -> alunoView.menuAluno(scanner, aController);
                        case "2" -> professorView.menuProfessor(scanner, pController);
                        case "3" -> aqvView.exibirMenu();
                        case "4" -> logar();
                        case "0" -> {
                            System.out.println("Saindo...");
                            System.exit(0);
                        }
                        default  -> System.out.println("Opção inválida.");
                    }
                });
    }

    public static void menuAQV(AQV aqv){

    }

    private static void menuProfessor(Professor professor) {
        ProfessorView professorView = new ProfessorView();
        System.out.printf("Bem vind@ %s \n", professor.getNome());
        HorarioView horarioView = new HorarioView();
        executarMenu("""
                    ===== MENU PROFESSOR =====
                    1. Gerenciar Horários
                    2. Receber notificações de atraso
                    3. Deslogar
                    0. Sair
                    """,
                opcao -> {
                    switch (opcao) {
                        case "1" -> horarioView.menu();
                        case "2" -> WebSocketClienteConsole.conectar();
                        case "3" -> {
                            WebSocketClienteConsole.desconectar();
                            logar();
                        }
                        case "0" -> {
                            System.out.println("Saindo...");
                            WebSocketClienteConsole.desconectar();
                            System.exit(0);
                        }
                        default  -> System.out.println("Opção inválida.");
                    }
                });
    }

    public static void menuAluno(Aluno aluno){

    }

    private static void executarMenu(String titulo, java.util.function.Consumer<String> acoes) {
        String opcao;
        do {
            System.out.print(titulo);
            opcao = scanner.nextLine();
            acoes.accept(opcao);
        } while (!opcao.equals("0"));
    }
}