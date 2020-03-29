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
* Procedural Driven Programs
* The program that we have been writing in the past in this course have all been procedural, meaning they run in the
* console and they have little to know user interaction - this can also ben seen that the launch from the entry point
* of the application (mainly Main) to the last line of code. Another case is that no matter how many methods or
* conditional statements, we know roughly what order the code is going to run in
*
* Event Driven Programs
* But UI applications are event driven, and that is because the user dictates what code will be run and when and that
* is by clicking on particular controls on the screen
*
* Typically the lifecycle of a UI, is that it will run the initialisation code, it wil then build the main user
* interface and it will then wait for user input when the user does something (i.e pressing a button or typing
* something into a field) the application will run the code that handles that particular event based on what the user
* has actually done, what they have clicked/ interacted with the controller on the screen. So it will run the event
* handler for that particular event
*
* So when the user clicks to close the window or terminate the program, any cleanup code will run and the application
* will then exit at that point.
*
* JavaFX UI Thread
* In JavaFX, there is a UI application Thread, which waits for user input
* So when the user interacts with a control on the screen, an event is raised to the UI thread, so in other words the
* UI thread notices that the user's done something and then check to see if any part of the application has expresses
* handling whatever the user has done
*
* So going back to "Say Hello" button, are first step is to write an Event Handler - and we actually do this in the
* Controller.java file, and remember that JavaFX was patten to use the MVC model, the Controller.java is the part
* that deals with user input
*
* Controller.java
*   public void onButtonClicked() {
        System.out.println("Hello");
    }
*
* We added this method, but when we run the program, nothing happens when we click the button, this is because we
* have not associated the button to this method. To do so we go back to our .fmxl file
*
* .fxml
*   - <Button text="Say Hello" GridPane.rowIndex="0" GridPane.columnIndex="0" onAction="#onButtonClicked"/>
*
* We access the onAction attribute within Button, and to tell JavaFX which method we want to link it with, we first
* write "#" + methodName
*   - "#onButtonClicked"
*
* So now when we run this, when ww click the button we get the String "Hello" appear in the console
*
* So when we actually click the button, the UI thread is listening and will see if there is an event handler
* interested with dealing with that user interaction, and if so - the UI thread will dispatch the load to the
* eventHandler.
*
* In general we can say that the EventHandlers can also be seen as EventListeners - as they are "listening" to the UI
* thread and seeing if there is any work for them do to
*
* Now lets create a TextField, so that it will retrieve the user input from the field and we can concatenate the user
* input String with our String literal
*
* to do this we have do 2 things
*   1. Create the TextField in the .fxml file, and create an fx:id to it, so that we can associate to our
*      Controller.java
*   2. Create a private Instance variable of the TextField, making sure that the name of the variable is the exact
*      same to the the fx:id we made in the TextField in the .fxml file
*
*   - private TextField nameField;
*   - <TextField fx:id="nameField" GridPane.rowIndex="0" GridPane.columnIndex="0"/>
*
* Now lets retrieve that data from the instance variable and concatenate it to our own String, by using the getText()
* from the TextField Class
*   public void onButtonClicked() {
        System.out.println("Hello, " + nameField.getText());
    }

* When we run this code, we actually get a NullPointerException, this is because we have not actually made a link to
* the fx:id and to the private instance variable - in fact they are completely separate things. So to make the
* connection between the two, we have to use the "@" decoration in the Controller.java file like so:
*   @FXML
*   private TextField nameField;
*
* Now thi creates the link between the Controller.java instance variable and to the TextField fx:id, now when we the
* run the code, we get no errors and the program works as expected
*
* It is also good practice to add the annotation to the method that also access input from a controller that is
* associated to an fx:id like so
*
*   @FXML
    public void onButtonClicked() {
        System.out.println("Hello, " + nameField.getText());
    }

* Now EventHandlers can accept ActionEvent parameters, these params allow us to be smarted with how we code our
* EventHandler, so for example, we can write an EventHandler which handles 2 or more controls and we use the
* ActionEvent to determine which control it was, by doing so we can have one EventHandler handle many control
* interactions at once
*
* Controller.java
* public class Controller {
    @FXML
    private TextField nameField;
    @FXML
    private Button helloButton;
    @FXML
    private Button byeButton;

    @FXML
    public void onButtonClicked(ActionEvent e) {
        if(e.getSource().equals(helloButton)) {
            System.out.println("Hello, " + nameField.getText());
        } else if(e.getSource().equals(byeButton)) {
            System.out.println("Bye, " + nameField.getText());
        }
    }
* }
*
* .fxml
* <GridPane fx:controller="sample.Controller"
          xmlns:fx="http://javafx.com/fxml" alignment="center" hgap="10" vgap="10">
    <TextField fx:id="nameField" GridPane.rowIndex="0" GridPane.columnIndex="0"/>
    <Button fx:id="helloButton" text="Say Hello" GridPane.rowIndex="1" GridPane.columnIndex="0"
            onAction="#onButtonClicked"/>
    <Button fx:id="byeButton" text="Say Bye" GridPane.rowIndex="1" GridPane.columnIndex="1"
            onAction="#onButtonClicked"/>
* </GridPane>
*
* So we created 2 buttons, one with an fx:id "helloButton" and the other with fx:id "byeButton", by doing so it
* allows us to create references in our Controller.java with our instance variables (all links need a @FXML decoration)
* and each button has the onAction attribute set to the methodName in Controller.java (onButtonClicked)
*
* Now we pass a parameter to onButtonClick(), and that being of type ActionEvent, this allows us to basically
* differentiate what control the user has actually interacted with on the current scene - from our scene graph
*
* we use the ActionEvent getSource(), this returns the control type, and if its has an id (that's why we put id's to
* our buttons), its styles class and the actual String representation of the text field contents
*   - Button[id=helloButton, styleClass=button]'Say Hello'
*
* so when we come to our conditional statements, we actually use the getSource().equals() and pass in the instance
* variable of the Button (being helloButton or byeButton) this will check the memory reference, and if they match do
* the code within the conditional statement.
*
*
* Lets go even further with our program, and check keyReleases as well, because when we run our program and if the
* TextField is empty and the user presses one of the two buttons, the String concatenation is with whitespace (our
* program does not crash) and it is best to verify and check the user input to produce a better user experience
* altogether.
*
* Controller.java
*   @FXML
    public void initialize() {
        helloButton.setDisable(true);
        byeButton.setDisable(true);
    }
*
*   @FXML
    public void onKeyReleased() {
        String text = nameField.getText();
        boolean disableButtons = text.isBlank();
        helloButton.setDisable(disableButtons);
        byeButton.setDisable(disableButtons);
    }
*
* .fxml
*   - <TextField fx:id="nameField" GridPane.rowIndex="0" GridPane.columnIndex="0" onKeyReleased="#onKeyReleased"/>
*
* We write these 2 methods, the initialize() is a method that is actually ran when the UI is constructed, this
* methods accepts no parameters and can be overridden to our liking, here we tell it to have both of our buttons
* disabled, this is because when the UI is first constructed there is no text within the TextField. We then create a
* an EventHandler which will handle onKeyReleases - so when the user has let go of the key on the keyboard.
*
* we check if the TextField is blank (so its empty or it contains whitespace) and we pass the boolean value (whether
* it has text or not in the TextField) to setDisabled() to both of our buttons - which allow us to effectively turn
* them on or off depending on the entries within the TextField
*
* To finish it all off, we pass the attribute onKeyRelease the method which we wrote at the top - which will
* basically handle for every Event the user has let go of the key on the keyboard
*
*
* UI Thread
* As I mentioned above, but the UI thread is responsible for dispatching the current I/O event to the an
* EventHandler, but there sometimes when the program may stall - this is because the the UI thread has dispatched the
*  I/O event to an EventHandler - and maybe sometimes the the EventHandler is taking too long to process the Event
* that was passed to it. This can cause a freeze up to the the actual UI and affect the user experience. And no
* matter how many times the user may press buttons, the UI thread will not be listening as the UI thread will only
* start listening again once the EventHandler has finished processing its code or whatever
*
*     public void onButtonClicked(ActionEvent e) {
        if(e.getSource().equals(helloButton)) {
            System.out.println("Hello, " + nameField.getText());
        } else if(e.getSource().equals(byeButton)) {
            System.out.println("Bye, " + nameField.getText());
        }

        try{
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            // we don't care what happens here
            ;
        }

        if(ourCheckBox.isSelected()) {
            nameField.clear();
            disableButtons(true);
        }
    }
*
* here we have placed a try and catch exception here to simulate a freeze up, by making the UI thread sleep for 10
* seconds (10,000 ms) the thread cannot listen to any other I/O Events that occur until the current EventHandler is
* finished with its process
*
* To mitigate this, is to return the control back to the UI thread as quickly as possible or to make a new thread to
* handle this process
*
* We use utilise the processes Threads (this is covered in concurrency), but essentially we will be creating a new
* Thread on this current process and this thread is what we call "Background Thread" this thread will actually handle
* the EventHandling - leaving our UI thread free and available to deal with other user I/O events - we simulate this
*
* Controller.java
*   @FXML
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
*
* .fxml file
*   - <Label fx:id="ourLabel" text="Nothing has happened" GridPane.rowIndex="3" GridPane.columnIndex="0"/>
*
* In our onButtonClicked() we make a new runnable object - this is what we pass to our Background Thread to say do
* this task essentially
*
* These 2 lines allow us to debug and it tells us which Thread we are on
*   - String s = Platform.isFxApplicationThread() ? "Thread" : "Background Thread";
*   - System.out.println("I'm going to sleep on the: " + s);
*
*   - String s = Platform.isFxApplicationThread() ? "Thread" : "Background Thread";
*   - System.out.println("I'm updating the label on the: " + s);
*
* NOTE: we must use Platform.runLater(Runnable) to allow us to use another Thread, in JavaFX all the EventDriven must
* be done on the JavaFX Thread, no new threads are allowed to do any work. So we have to use the runLater() which
* essentially is our Background Thread, its does its processing and then tells the UI Thread "I have done this, I
* will have you to implement it" and the UI Thread will take the finished processed work and output or do the change.
*
* So what we have done is create a new Runnable (Background Thread) which we simulate it to do a lot of work (so
* freezing up) and this leaves our UI Thread free to do other things. So we can still press other buttons, input more
* data and until the that Thread EventHandler has finished processing, it will be passed back to the UI Thread to
* basically finish up the rest.
* This leads to a more pleasant user experience, less freeze ups
*
*
*
* */
