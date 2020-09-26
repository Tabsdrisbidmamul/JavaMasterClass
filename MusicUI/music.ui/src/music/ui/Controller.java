package music.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.StageStyle;
import music.core.Album;
import music.core.Artist;
import music.db.Datasource;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private TableView artistTable;
    @FXML
    private BorderPane mainWindow;
    @FXML
    private ProgressBar progressBar;

    @FXML
    public void listArtists() {
        Task<ObservableList<Artist>> task = new GetAllArtistsTask();
        artistTable.itemsProperty().bind(task.valueProperty());
        progressBar.progressProperty().bind(task.progressProperty());
        progressBar.setVisible(true);

        task.setOnSucceeded(e -> progressBar.setVisible(false));
        task.setOnFailed(e -> progressBar.setVisible(false));

        new Thread(task).start();
    }

    @FXML
    public void listAlbumsByArtist() {
        Artist artist = null;
        try {
            artist = (Artist) artistTable.getSelectionModel().getSelectedItem();
        } catch (ClassCastException e) {
            makeAlert(Alert.AlertType.ERROR, "Album Error", "Album Selection", "An Album was selected and not artist");
            return;
        }

        if(artist == null) {
            makeAlert(Alert.AlertType.INFORMATION,
                    "Information", "No Artist Selected", "Please select an Artist from the list before clicking \"Show Albums\" button");
            return;
        }

        final Artist finalArtist = artist;
        Task<ObservableList<Album>> task = new Task<ObservableList<Album>>() {
            @Override
            protected ObservableList<Album> call() throws Exception {
                return FXCollections.observableArrayList(
                        Datasource.getInstance().queryAlbumForArtistId(finalArtist.getId()));
            }
        };
        artistTable.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }

    @FXML
    public void updateArtist() {
        if(artistTable.getSelectionModel().getSelectedItem() == null) {
            makeAlert(Alert.AlertType.ERROR, "Error Window", "Artist not selected",
                    "Artist was not selected");
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainWindow.getScene().getWindow());
        dialog.initStyle(StageStyle.UTILITY);

        Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();

        dialog.setTitle("Edit an existing Artist");
        dialog.setHeaderText(String.format("Edit an existing Artist Name: %s", artist.getName()));

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("dialogPane.fxml"));

        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            System.out.println("Could not load the Dialog");
            e.printStackTrace();

            makeAlert(Alert.AlertType.ERROR, "Error Window", "Could not load the Artist", "Artist was not able to be " +
                    "loaded");

            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        DialogController dialogController = loader.getController();

        dialogController.setDataToForm(artist);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            String name = dialogController.editResults(artist);
            ((Artist) artistTable.getSelectionModel().getSelectedItem()).setName(name);
            artistTable.refresh();
        }
    }



    private void makeAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert= new Alert(alertType);
        alert.initOwner(mainWindow.getScene().getWindow());
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.initOwner(mainWindow.getScene().getWindow());
        alert.showAndWait();
    }

}

class GetAllArtistsTask extends Task {
    @Override
    protected ObservableList<Artist> call() {
        return FXCollections.observableArrayList(
                Datasource.getInstance().queryArtist(Datasource.Order.ORDER_BY_NONE)
        );
    }
}

//class GetAlbumByArtist extends Task {
//    @Override
//    protected ObservableList<Album> call() throws Exception {
//        return FXCollections.observableArrayList(Datasource.getInstance().queryAlbumForArtistId());
//    }
//}