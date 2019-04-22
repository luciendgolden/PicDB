module at.technikum.swei {
    requires javafx.controls;
    requires javafx.fxml;

    opens at.technikum.swei to javafx.fxml;
    exports at.technikum.swei;
}