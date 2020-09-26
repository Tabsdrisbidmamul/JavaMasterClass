package music.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import music.core.Artist;
import music.db.Datasource;

public class DialogController {
    @FXML
    private Label ID;
    @FXML
    private TextField name;

    public String editResults(Artist artist) {
        String name = this.name.getText().trim();
        Datasource.getInstance().updateArtistById(artist.getId(), name);
        return name;
    }

    public void setDataToForm(Artist artist) {
        ID.setText(Integer.toString(artist.getId()));
        name.setText(artist.getName());
    }
}
