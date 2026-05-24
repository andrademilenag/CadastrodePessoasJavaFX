package com.milena;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private ArrayList<Pessoa> listaPessoas = new ArrayList<>();
    private ObservableList<Pessoa> observablePessoas = FXCollections.observableArrayList();

    @Override
    @SuppressWarnings("exports")
    public void start(Stage stage) {

        Label titulo = new Label("Cadastro de Pessoas");
        titulo.setFont(new Font("Arial", 28));
        titulo.setTextFill(Color.web("#d63384"));

        // CAMPOS
        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome completo");

        TextField cpfField = new TextField();
        cpfField.setPromptText("CPF");

        TextField emailField = new TextField();
        emailField.setPromptText("E-mail");

        TextField telefoneField = new TextField();
        telefoneField.setPromptText("Telefone");

        // BOTÕES
        Button btnSalvar = new Button("Salvar");
        Button btnCancelar = new Button("Cancelar");
        Button btnListar = new Button("Listar");

        String estiloBtn = "-fx-background-color: #ff99cc; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10;";
        btnSalvar.setStyle(estiloBtn);
        btnCancelar.setStyle(estiloBtn);
        btnListar.setStyle(estiloBtn);

        HBox botoes = new HBox(12, btnSalvar, btnCancelar, btnListar);
        botoes.setAlignment(Pos.CENTER);

        // TABELA
        TableView<Pessoa> tabela = new TableView<>();
        tabela.setItems(observablePessoas);

        TableColumn<Pessoa, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Pessoa, String> colCpf = new TableColumn<>("CPF");
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        TableColumn<Pessoa, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Pessoa, String> colTelefone = new TableColumn<>("Telefone");
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        tabela.getColumns().addAll(colNome, colCpf, colEmail, colTelefone);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox layout = new VBox(12,
                titulo,
                nomeField,
                cpfField,
                emailField,
                telefoneField,
                botoes,
                tabela
        );

        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #ffe6f2;");

        // EVENTOS DOS BOTÕES
        btnSalvar.setOnAction(e -> {
            Pessoa p = new Pessoa(
                    nomeField.getText(),
                    cpfField.getText(),
                    emailField.getText(),
                    telefoneField.getText()
            );

            listaPessoas.add(p);
            observablePessoas.add(p);

            limparCampos(nomeField, cpfField, emailField, telefoneField);
        });

        btnCancelar.setOnAction(e -> {
            limparCampos(nomeField, cpfField, emailField, telefoneField);
        });

        btnListar.setOnAction(e -> {
            System.out.println("=== LISTA DE PESSOAS ===");
            for (Pessoa p : listaPessoas) {
                System.out.println(p.getNome() + " - " + p.getCpf() + " - " + p.getEmail() + " - " + p.getTelefone());
            }
        });

        Scene scene = new Scene(layout, 520, 650);
        stage.setTitle("Cadastro de Pessoas");
        stage.setScene(scene);
        stage.show();
    }

    private void limparCampos(TextField nome, TextField cpf, TextField email, TextField tel) {
        nome.clear();
        cpf.clear();
        email.clear();
        tel.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}