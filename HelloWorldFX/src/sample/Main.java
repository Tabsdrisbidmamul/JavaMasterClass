package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

/*
* JavaFX
* For every JavaFX application, we must always do these configurations or else the program will not run
* right-click the project folder -> open module settings (right at the bottom)
* -> make sure that both the tab options "project" and "module" are using JDK-11 or else this won't work
* -> Then go to "global libraries" and right click JavaFX-11, and press the option "add to module" - and click ok to
* add this to the current project
*
* Then right click the src folder, and add a new file "module-info.java" this is an option, and inside it you must add
*   // add these 2 always when configuring JavaFX
    requires javafx.fxml;
    requires javafx.controls;

    // package name here
    opens sample;
*
*
* */
