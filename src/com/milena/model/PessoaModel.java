package com.milena.model;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PessoaModel {

    // Lista observável que a TableView vai usar diretamente
    private ObservableList<Pessoa> pessoas = FXCollections.observableArrayList();

    // Listeners para notificar mudanças (mesmo padrão da aula)
    public interface ModelListener {
        void onModelChanged();
    }

    private List<ModelListener> listeners = new ArrayList<>();

    public void addListener(ModelListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (ModelListener listener : listeners) {
            listener.onModelChanged();
        }
    }

    // Adiciona uma nova pessoa e notifica a View
    public void adicionarPessoa(Pessoa pessoa) {
        pessoas.add(pessoa);
        notifyListeners();
    }

    // Retorna a lista observável (a View usa isso pra preencher a TableView)
    public ObservableList<Pessoa> getPessoas() {
        return pessoas;
    }
}