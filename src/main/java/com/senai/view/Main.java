package com.senai.view;

import com.senai.model.*;
import com.senai.model.dao.json.CoordenadorDAO;
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
        switch (usuario.getTipo()) {
            case "Coordenador" -> menuCoordenador((Coordenador) usuario);
            case "AQV" -> menuAQV((AQV) usuario);
            case "Professor" -> menuProfessor((Professor) usuario);
            case "Aluno" -> menuAluno((Aluno) usuario);
            default -> System.out.println("Tipo de usuário desconhecido.");
        }
    }

    private static void menuCoordenador(Coordenador coordenador) {
    }

    private static void menuAQV(AQV aqv) {
        AlunoView alunoView = new AlunoView();
        ProfessorView professorView = new ProfessorView();
        OcorrenciaView ocorrenciaView = new OcorrenciaView();
        JustificativaView justificativaView = new JustificativaView();

        System.out.printf("Bem vind@ %s \n", aqv.getNome());
        executarMenu("""               
                    ===== MENU AQV =====
                    1. Gerenciar Aluno
                    2. Gerenciar Professor
                    3. Listar Ocorrência
                    4. Aceitar Ocorrência
                    5. Listar Justificativa
                    6. Aceitar Justificativa
                    7. Deslogar
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
                        case "7" -> logar();
                        case "0" -> {
                            System.out.println("Saindo...");
                            System.exit(0);
                        }
                        default  -> System.out.println("Opção inválida.");
                    }
                });
    }

    private static void menuProfessor(Professor professor) {
    }

    private static void menuAluno(Aluno aluno) {
        OcorrenciaView ocorrenciaView = new OcorrenciaView();
        JustificativaView justificativaView = new JustificativaView();

        System.out.printf("Bem vind@ %s \n",aluno.getNome());
        executarMenu("""
                    ===== MENU ALUNO =====
                    1. Gerenciar Ocorrência
                    2. Gerenciar Justificativa
                    3. Deslogar
                    0. Sair
                    """,
                opcao -> {
                    switch (opcao) {
                        case "1" -> ocorrenciaView.menu();
                        case "2" -> justificativaView.menu();
                        case "3" -> logar();
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
