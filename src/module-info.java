module CadastrodePessoasJavaFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.milena.model to javafx.base;

    exports com.milena;
}