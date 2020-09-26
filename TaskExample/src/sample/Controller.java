package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

public class Controller {
    private Task<ObservableList<String>> task;
    @FXML
    private ListView listView;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressLabel;

    private Service<ObservableList<String>> service;

    public void initialize() {
//        task = new Task<ObservableList<String>>() {
//            @Override
//            protected ObservableList<String> call() throws Exception {
//                String[] names = {"Idris Khan",
//                        "Bill Rogers",
//                        "Jack Jill",
//                        "Joan Andrews",
//                        "Mary Johnson",
//                        "Bob McDonald"};
//
//                final ObservableList<String> employees = FXCollections.observableArrayList();
//
//                for (int i=0; i<names.length; i++) {
//                    employees.add(names[i]);
//                    updateMessage("Added " + names[i] + " to the list");
//                    updateProgress(i+1, names.length);
//                    Thread.sleep(200);
//                }
//                updateMessage("All Employees were fetched");
//
////                Platform.runLater(new Runnable() {
////                    @Override
////                    public void run() {
////                        listView.setItems(employees);
////                    }
////                });
//
//                return employees;
//            }
//        };

        service = new EmployeeService();
        progressBar.visibleProperty().bind(service.runningProperty());
        progressBar.progressProperty().bind(service.progressProperty());
        progressLabel.visibleProperty().bind(service.runningProperty());
        progressLabel.textProperty().bind(service.messageProperty());
        listView.itemsProperty().bind(service.valueProperty());

//        progressBar.visibleProperty().bind(task.runningProperty());
//        progressBar.progressProperty().bind(task.progressProperty());
//        progressLabel.textProperty().bind(task.messageProperty());
//        listView.itemsProperty().bind(task.valueProperty());

        /*
        * These Event Handlers are exactly the same as the visibleProperty() - and as you can see that there is much
        * less code to work with the visible property - however with the Event Handlers we can add extra
        * functionality to it if we want - much more than the visibleProperty offers
        *
        * */
//        service.setOnRunning(new EventHandler<WorkerStateEvent>() {
//            @Override
//            public void handle(WorkerStateEvent workerStateEvent) {
//                progressBar.setVisible(true);
//                progressLabel.setVisible(true);
//            }
//        });
//
//        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
//            @Override
//            public void handle(WorkerStateEvent workerStateEvent) {
//                progressBar.setVisible(false);
//                progressLabel.setVisible(false);
//            }
//        });
//
//        progressBar.setVisible(false);
//        progressBar.setVisible(false);



    }

    @FXML
    public void buttonPressed() {
//        new Thread(task).start();
        if (service.getState() == Service.State.SUCCEEDED) {
            service.reset();
            service.start();
        } else if (service.getState() == Service.State.READY) {
            service.start();
        }
    }
}
