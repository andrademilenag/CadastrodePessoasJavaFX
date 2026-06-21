package com.milena;

import com.milena.controller.PessoaController;
import com.milena.model.PessoaModel;
import com.milena.view.PessoaView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        PessoaModel model = new PessoaModel();
        PessoaView view = new PessoaView();
        new PessoaController(model, view);

        Scene scene = new Scene(view.getRoot(), 520, 650);
        stage.setTitle("Cadastro de Pessoas - MVC");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}