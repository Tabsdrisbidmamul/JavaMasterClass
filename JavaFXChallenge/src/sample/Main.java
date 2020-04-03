package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
//        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        primaryStage.setTitle("My Contacts");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }

    public void init() {
        ContactData.getInstance().loadContacts();
    }

    public void stop() {
        ContactData.getInstance().saveContacts();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
