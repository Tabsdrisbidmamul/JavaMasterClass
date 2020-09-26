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
        primaryStage.setScene(new Scene(root, 300, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

/*
*
* In JavaFX when we want to perform a background task - we use a class that implements the Worker interface pr a
* class that extends the Task class
*
* Task Class
* This class implements the Runnable interface and we can use task objects whenever or wherever we use Runnable Objects
*
* When creating a class that extends Task, we have to override the call() method and that's what we did in our
* initialize() method in the Controller.java
*
* Controller.java
*
*
*   import javafx.collections.ObservableList;
*   import javafx.concurrent.Task;
*
*   private Task<ObservableList<String>> task;

    public void initialize() {
        task = new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws Exception {
                Thread.sleep(1000);
                return FXCollections.observableArrayList(
                        "Idris Khan",
                        "Bill Rogers",
                        "Jack Jill",
                        "Joan Andrews",
                        "Mary Johnson",
                        "Bob McDonald"
                );
            }
        };
    }
*
* Now we are going to pretend we are going to fetch a list of employee names from the DB, hence we pass the
* parameterised type as an ObservableList<String> of String - and in the return type for call its an
* ObservableList<String>

* In the example above we inserted hard-coded values for the array list to simulate the data, and we put the Thread
* to sleep for 1 second to also simulate a DB fetch
*
* We add a button the fxml file and its onAction attribute is set to this
*
*
*   @FXML
    public void buttonPressed() {
        new Thread(task).start();
    }
*
*
* We define the action what task is going to at the initialize() method so we simply pass task as an argument and
* start() the thread, but this won't show anything at the moment
*
* This is because all Threads that are written on JavaFX must run on the main JavaFXApplication Thread
*
* So we are going to be using a ListView to output the results in the UI
*
* we use the Platform.runLater() method - this method is the main to go when wanting to run bespoke threads on the
* MainUI Thread.
*
* This method accepts a Runnable object, and runs it on the UI Thread
* Now our Task is a Runnable (implements the Runnable interface) but we don't want to run the task on the UI Thread,
* we want to update the ListView when the tasks complete.
*
* We will call runLater() when the tasks wakes up from sleeping (fetching data from DB), We wil have to create our
* ObservableList earlier rather than the return statement, so that we can use it to set the ListView
*
*
* Updated initialize()
*     public void initialize() {
        task = new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws Exception {
                Thread.sleep(1000);

                final ObservableList<String> employees = FXCollections.observableArrayList(
                        "Idris Khan",
                        "Bill Rogers",
                        "Jack Jill",
                        "Joan Andrews",
                        "Mary Johnson",
                        "Bob McDonald");

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        listView.setItems(employees);
                    }
                });

                return employees;
            }
        };
    }
*
* Now when running this, the button will then update the ListView - but this is not the recommended way - and it is
* not good practice to tie a task to the UI in this way - if we ever change the UI, we would also have to change the
* task so in general the UI code and the code that processes data should be kept separate
*
* So there is a better way to update the ListView and this is the recommended way to do it - so instead of using
* Platform.runLater() we can use what is called data binding
*
* So in the Task Class there is property called value - which is the value returned by the task (we return employees
* in the call() method)
* The ListView has a property called items and that contains the items that the ListView is displaying.
*
* What we are going to do is bind the ListView items property in the task's value property, so whenever the value
* property changed the items property (ListView) will update accordingly
*
* Binding
* With every task object they all will have properties (messages, running, visible or user defined etc...0 we want to
* use JavaFX data binding in this scenario where if one task's value has been updated then the respected Control in
* which has their property bind (linked) to the task.
* Then that property in the Control will read in that value and write it into their property - basically allowing us
* to then display it to the UI etc..
*
*
* Service
* This is call which you normally want to extend from and this basically allows us to run a task in - so essentially
* we keep all the task functionality within the class that extends from Service - and the method that it must
* override is
* - createTask()
*
* This allows us to move the task() functionality away from the initialise() method and instead simply instantiate
* the Service class and use the service from there - as it allows us to run tasks we can start() it like we would
* with a thread
* - service.start();
*
* This is the more general and recommended approach when coding UI components and Threads
*
*
* */