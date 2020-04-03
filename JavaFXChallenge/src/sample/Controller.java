package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    @FXML
    private BorderPane mainWindow;
    @FXML
    private TableView<Contact> tableView;


    public void initialize() {
        tableView.setItems(ContactData.getInstance().getContacts());
        tableView.getSelectionModel().selectFirst();
    }


    @FXML
    public void showNewContactDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainWindow.getScene().getWindow());
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setTitle("Add a new Contact");
        dialog.setHeaderText("Create a new Contact");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("contactDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            Contact newContact = controller.processResults();

            tableView.getSelectionModel().select(newContact);
        }
    }

    @FXML
    public void showEditContactDialog(){
        Contact item = tableView.getSelectionModel().getSelectedItem();
        if(!alertDialog(item)) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initOwner(mainWindow.getScene().getWindow());
            dialog.initStyle(StageStyle.UNDECORATED);

            dialog.setTitle("Edit a Contact");
            dialog.setHeaderText("Edit an existing Contact");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("contactDialog.fxml"));

            try {
                dialog.getDialogPane().setContent(fxmlLoader.load());
            } catch (IOException e) {
                System.out.println("Couldn't load the dialog");
                e.printStackTrace();
                return;
            }

            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

            DialogController controller = fxmlLoader.getController();
            controller.setDataToForm(item);

            Optional<ButtonType> result = dialog.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) {
                controller.processEditResults(item);
            }
        }
        
    }

    @FXML
    public void showDeleteContactDialog(){
        Contact contact = tableView.getSelectionModel().getSelectedItem();
        if(!alertDialog(contact)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Contact from My Contacts Table");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setHeaderText("Delete Contact" + contact.getFirstName() + " " + contact.getLastName());
            alert.setContentText("Are you sure? Press OK to confirm, or cancel to Back out.");

            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) {
                ContactData.getInstance().deleteContact(contact);
            }
        }
    }

    private boolean alertDialog(Contact contact) {
        if(contact == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Contact Selected");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setHeaderText(null);
            alert.setContentText("Please select the contacts you want to edit");
            return true;
        }
        return false;
    }
}
