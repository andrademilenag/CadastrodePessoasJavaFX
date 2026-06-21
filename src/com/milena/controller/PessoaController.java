package com.milena.controller;

import com.milena.model.Pessoa;
import com.milena.model.PessoaModel;
import com.milena.view.PessoaView;

public class PessoaController {

    private PessoaModel model;
    private PessoaView view;

    public PessoaController(PessoaModel model, PessoaView view) {
        this.model = model;
        this.view = view;

        // Conecta a tabela da View à lista do Model
        view.setItensTabela(model.getPessoas());

        // Conecta as ações dos botões aos métodos do Controller
        view.setOnSalvarButtonClicked(this::handleSalvar);
        view.setOnCancelarButtonClicked(this::handleCancelar);
        view.setOnListarButtonClicked(this::handleListar);

        // Sempre que o Model mudar, a tabela já se atualiza sozinha
        // (porque a TableView está ligada à ObservableList do Model)
        model.addListener(() -> System.out.println("Model atualizado."));
    }

    private void handleSalvar() {
        String nome = view.getNomeInput();
        String cpf = view.getCpfInput();
        String email = view.getEmailInput();
        String telefone = view.getTelefoneInput();

        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome não pode estar vazio.");
            return;
        }

        Pessoa pessoa = new Pessoa(nome, cpf, email, telefone);
        model.adicionarPessoa(pessoa);

        view.limparCampos();
    }

    private void handleCancelar() {
        view.limparCampos();
    }

    private void handleListar() {
        System.out.println("=== LISTA DE PESSOAS ===");
        for (Pessoa p : model.getPessoas()) {
            System.out.println(p.getNome() + " - " + p.getCpf() + " - " + p.getEmail() + " - " + p.getTelefone());
        }
    }
}