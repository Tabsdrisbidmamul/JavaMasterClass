module music.ui {
    requires javafx.fxml;
    requires javafx.controls;
    requires music.db;

    exports music.ui to javafx.graphics, javafx.fxml;
    opens music.ui to javafx.fxml;
}