module HelloWorldFX {
    // add these 2 always when configuring JavaFX
    requires javafx.fxml;
    requires javafx.controls;

    // package name here
    opens sample;
}