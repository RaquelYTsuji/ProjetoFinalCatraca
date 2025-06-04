package com.senai.view;

import com.senai.WebSocket.WebSocketClienteConsole;
import com.senai.controller.AlunoController;
import com.senai.controller.ProfessorController;
import com.senai.model.*;
import com.senai.util.CriptografiaUtil;

import java.util.Optional;
import java.util.Scanner;

import com.senai.model.*;
import com.senai.model.dao.json.CoordenadorDAO;
import com.senai.util.CriptografiaUtil;

import java.util.Optional;
import java.util.Scanner;

import static com.senai.mqtt.MqttSubscriber.iniciarMqtt;
import static com.senai.websocket.WebSocketSender.iniciarWebSocket;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        iniciarMqtt();
        iniciarWebSocket();
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
        switch (usuario.getTipo()) {
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

    private static void menuAQV(AQV aqv) {
        AlunoView alunoView = new AlunoView();
        ProfessorView professorView = new ProfessorView();
        OcorrenciaView ocorrenciaView = new OcorrenciaView();
        JustificativaView justificativaView = new JustificativaView();
        HorarioView horarioView = new HorarioView();

        System.out.printf("Bem vind@ %s \n", aqv.getNome());
        executarMenu("""               
                    ===== MENU AQV =====
                    1. Gerenciar Aluno
                    2. Gerenciar Professor
                    3. Listar Ocorrência
                    4. Aceitar Ocorrência
                    5. Listar Justificativa
                    6. Aceitar Justificativa
                    7. Gerenciar Horarios
                    8. Deslogar
                    0. Sair
                    """,
                opcao -> {
                    switch (opcao) {
                        case "1" -> alunoView.menuAluno();
                        case "2" -> professorView.menuProfessor();
                        case "3" -> ocorrenciaView.listar();
                        case "4" -> ocorrenciaView.aceitar();
                        case "5" -> justificativaView.listar();
                        case "6" -> justificativaView.aceitar();
                        case "7" -> horarioView.menu();
                        case "8" -> logar();
                        case "0" -> {
                            System.out.println("Saindo...");
                            System.exit(0);
                        }
                        default  -> System.out.println("Opção inválida.");
                    }
                });
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


    private static void menuAluno(Aluno aluno) {
        OcorrenciaView ocorrenciaView = new OcorrenciaView();
        JustificativaView justificativaView = new JustificativaView();
        HorarioView horarioView = new HorarioView();

        System.out.printf("Bem vind@ %s \n",aluno.getNome());
        executarMenu("""
                    ===== MENU ALUNO =====
                    1. Gerenciar Ocorrência
                    2. Gerenciar Justificativa
                    3. Listar Horarios
                    4. Deslogar
                    0. Sair
                    """,
                opcao -> {
                    switch (opcao) {
                        case "1" -> ocorrenciaView.menu();
                        case "2" -> justificativaView.menu();
                        case "3" -> horarioView.listar();
                        case "4" -> logar();
                        case "0" -> {
                            System.out.println("Saindo...");
                            System.exit(0);
                        }
                        default  -> System.out.println("Opção inválida.");
                    }
                });
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