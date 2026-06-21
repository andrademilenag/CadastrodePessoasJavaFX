package com.milena.view;

import com.milena.model.Pessoa;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PessoaView {

    private VBox root;

    private TextField nomeField;
    private TextField cpfField;
    private TextField emailField;
    private TextField telefoneField;

    private Button salvarBtn;
    private Button cancelarBtn;
    private Button listarBtn;

    private TableView<Pessoa> tabela;

    // Runnables: ações que o Controller vai definir (mesmo padrão da aula)
    private Runnable onSalvarButtonClicked;
    private Runnable onCancelarButtonClicked;
    private Runnable onListarButtonClicked;

    public PessoaView() {
        criarComponentes();
    }

    private void criarComponentes() {

        Label titulo = new Label("Cadastro de Pessoas");
        titulo.setFont(new Font("Arial", 28));
        titulo.setTextFill(Color.web("#d63384"));

        // Campos
        nomeField = new TextField();
        nomeField.setPromptText("Nome completo");

        cpfField = new TextField();
        cpfField.setPromptText("CPF");

        emailField = new TextField();
        emailField.setPromptText("E-mail");

        telefoneField = new TextField();
        telefoneField.setPromptText("Telefone");

        // Botões
        salvarBtn = new Button("Salvar");
        cancelarBtn = new Button("Cancelar");
        listarBtn = new Button("Listar");

        String estiloBtn = "-fx-background-color: #ff99cc; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10;";
        salvarBtn.setStyle(estiloBtn);
        cancelarBtn.setStyle(estiloBtn);
        listarBtn.setStyle(estiloBtn);

        salvarBtn.setOnAction(e -> {
            if (onSalvarButtonClicked != null) onSalvarButtonClicked.run();
        });

        cancelarBtn.setOnAction(e -> {
            if (onCancelarButtonClicked != null) onCancelarButtonClicked.run();
        });

        listarBtn.setOnAction(e -> {
            if (onListarButtonClicked != null) onListarButtonClicked.run();
        });

        HBox botoes = new HBox(12, salvarBtn, cancelarBtn, listarBtn);
        botoes.setAlignment(Pos.CENTER);

        // Tabela
        tabela = new TableView<>();

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

        root = new VBox(12, titulo, nomeField, cpfField, emailField, telefoneField, botoes, tabela);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #ffe6f2;");
    }

    public VBox getRoot() {
        return root;
    }

    // Métodos para o Controller ler os dados digitados
    public String getNomeInput() { return nomeField.getText(); }
    public String getCpfInput() { return cpfField.getText(); }
    public String getEmailInput() { return emailField.getText(); }
    public String getTelefoneInput() { return telefoneField.getText(); }

    // Métodos para o Controller registrar as ações dos botões
    public void setOnSalvarButtonClicked(Runnable handler) { this.onSalvarButtonClicked = handler; }
    public void setOnCancelarButtonClicked(Runnable handler) { this.onCancelarButtonClicked = handler; }
    public void setOnListarButtonClicked(Runnable handler) { this.onListarButtonClicked = handler; }

    // Conecta a tabela à lista observável do Model
    public void setItensTabela(ObservableList<Pessoa> pessoas) {
        tabela.setItems(pessoas);
    }

    public void limparCampos() {
        nomeField.clear();
        cpfField.clear();
        emailField.clear();
        telefoneField.clear();
    }
}