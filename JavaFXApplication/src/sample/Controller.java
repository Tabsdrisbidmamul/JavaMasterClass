package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class Controller {
    @FXML
    private Label label;
    @FXML
    private Button button4;
    @FXML
    private GridPane gridPane;
    @FXML
    private WebView webView;

    public void initialize() {
        button4.setEffect(new DropShadow());
    }

    @FXML
    public void handleMouseEnter() {
        label.setScaleX(2.0);
        label.setScaleY(2.0);
    }

    @FXML
    public void handleMouseExit() {
        label.setScaleX(1.0);
        label.setScaleY(1.0);
    }

    @FXML
    public void handleClick() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                /*
                * FileChooser: instantiates an object that allows the user to open the file explorer and select a
                * single file (depending on the method passes, they can choose from one to multiple files)
                *
                * Both will open the file explorer, the 1st opens a file and the 2nd saves a file to a directory
                *   showOpenDialog(gridPane.getScene().getWindow())
                *   showSaveDialog(gridPane.getScene().getWindow())
                *
                * We can even do: this allows the user to select multiple files
                *   showOpenMultipleDialog(gridPane.getScene().getWindow())
                *
                * This returns a list of file types
                *
                * Always make sure to pass in the parent window that called them, or else the user can open multiple
                * file explorer windows or even close the application down and the file explorer will still persist
                *
                * DirectoryChoose: instantiates an object that allows the user to open the file explorer and select a
                * directory
                *
                * Opens the file explorer to open a directory
                *   showDialog(gridPane.getScene().getWindow())
                *
                * File Extensions are very powerful as they can limit the number of files a user can see when they
                * use the opnDialog, but also can be useful in preventing unsupported files when they come to save it
                *  in the saveDialog - but of course the user can still remove the supported extensions and place
                * their own ones - there we would gracefully catch that exception
                *
                * How it works?
                * The FileChoose.ExtensionFilter() accepts 2 params:
                *   1 param: description: what the user will see when they come to save or open the dialog (so if we
                *   put ("tEXt", "*.txt") what they will see when they click the combo box to see the dropdown for
                *   supported files is tEXt -> .txt
                *   2 param: the file extension itself; you usually want to put *.extension as this says we want all
                *   files of this extension regardless of the name
                *
                * For all files we can do FileChooser.ExtensionFilter("All Files", "*.*"); this will allow all file
                * types
                *
                * We also not limited to one file extension, we can do
                * FileChooser.ExtensionFilter("Images", "*.jpg", "*.png", "*.gif"); and this will allow these image
                * files with these extensions
                *
                * */
                new FileChooser.ExtensionFilter("Text", "*.txt"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

//        DirectoryChooser chooser = new DirectoryChooser();
        List<File> files = chooser.showOpenMultipleDialog(gridPane.getScene().getWindow());
        if(files != null) {
            for(File file: files) {
                System.out.println(file.getPath());
            }
        } else {
            System.out.println("Choose was cancelled");
        }
    }

    @FXML
    public void handleLinkClick() {
//        System.out.println("The link was clicked");
        /*
        * There are 2 ways to get the hyperlink to work
        *   1. Open the weblink in the systems default browser
        *   2. use a web dialog within JavaFX to do the same
        *
        * 1. default browser
        * the module-info.java should look like this:
        *   module JavaFXApplication {
        *       requires javafx.controls;
        *       requires javafx.fxml;
        *       requires java.desktop;
        *
        *       opens sample;
        *   }
        *
        * This Java that we are going to be using a library called desktop, which allows us to access method within
        * it, and be able to open the systems default browser
        * */
//        try {
//            Desktop.getDesktop().browse(new URI("http://www.javafx.com"));
//        } catch (URISyntaxException | IOException e) {
//            e.printStackTrace();
//        }
        WebEngine webEngine = webView.getEngine();
        webEngine.load("http://www.javafx.com");
    }

}
