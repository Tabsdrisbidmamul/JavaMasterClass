package music.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import music.db.Datasource;


public class Main extends Application {

    private boolean exceptionInit = false;

    @Override
    public void start(Stage primaryStage) throws Exception{
        if(exceptionInit) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Database Error: FATAL ERROR");
            alert.setContentText("Could not connect to Database");
            alert.showAndWait();

            Platform.exit();
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.listArtists();
//        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        primaryStage.setTitle("Music Database");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public void init() throws Exception {
        super.init();
        if(!Datasource.getInstance().open()) {
            exceptionInit = true;
        }

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Datasource.getInstance().close();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
