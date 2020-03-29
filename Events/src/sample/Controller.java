package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField nameField;
    @FXML
    private Button helloButton;
    @FXML
    private Button byeButton;
    @FXML
    private CheckBox ourCheckBox;
    @FXML
    private Label ourLabel;

    @FXML
    public void initialize() {
        disableButtons(true);
    }

    private void disableButtons(boolean isDisableButtons) {
        helloButton.setDisable(isDisableButtons);
        byeButton.setDisable(isDisableButtons);
    }

    @FXML
    public void onButtonClicked(ActionEvent e) {
        if(e.getSource().equals(helloButton)) {
            System.out.println("Hello, " + nameField.getText());
        } else if(e.getSource().equals(byeButton)) {
            System.out.println("Bye, " + nameField.getText());
        }
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try{
                    String s = Platform.isFxApplicationThread() ? "Thread" : "Background Thread";
                    System.out.println("I'm going to sleep on the: " + s);
                    Thread.sleep(10000);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            String s = Platform.isFxApplicationThread() ? "Thread" : "Background Thread";
                            System.out.println("I'm updating the label on the: " + s);
                            ourLabel.setText("We did something!");
                        }
                    });
                } catch (InterruptedException ex) {
                    // we don't care what happens here
                    ;
                }
            }
        };

        new Thread(task).start();

        if(ourCheckBox.isSelected()) {
            nameField.clear();
            disableButtons(true);
        }
    }

    @FXML
    public void onKeyReleased() {
        String text = nameField.getText();
        boolean disableButtons = text.isBlank();
        disableButtons(disableButtons);
    }

    @FXML
    public void handleChange() {
        System.out.println("The checkbox is " + (ourCheckBox.isSelected() ? "checked" : "not selected"));
    }
}
