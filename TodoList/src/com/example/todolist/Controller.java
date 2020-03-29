package com.example.todolist;

import com.example.todolist.datamodel.TodoData;
import com.example.todolist.datamodel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class Controller {
    private List<TodoItem> todoItems;

    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea itemDetailsTextArea;
    @FXML
    private Label deadlineLabel;
    @FXML
    private BorderPane mainBorderPane;

    public void initialize() {
//        TodoItem item1 = new TodoItem("Mail birthday card", "Buy a 30th birthday card for John",
//                LocalDate.of(2020, Month.APRIL, 25));
//
//        TodoItem item2 = new TodoItem("Doctor's Appointment", "See Dr.Smith at 123 Main Street. Bring paperwork",
//                LocalDate.of(2020, Month.MARCH, 15));
//
//        TodoItem item3 = new TodoItem("Finish design proposal for client", "I promised Mike I'd email website mockups" +
//                "  by Friday 22nd April",
//                LocalDate.of(2020, Month.APRIL, 22));
//
//        TodoItem item4 = new TodoItem("Pickup Doug at the train station", "Doug's arriving on March 23 on the 5:00 " +
//                "train",
//                LocalDate.of(2020, Month.MARCH, 23));
//
//        TodoItem item5 = new TodoItem("Pickup dry cleaning", "The clothes should be ready by Wednesday",
//                LocalDate.of(2020, Month.APRIL, 20));
//
//        todoItems = new ArrayList<>();
//        todoItems.add(item1);
//        todoItems.add(item2);
//        todoItems.add(item3);
//        todoItems.add(item4);
//        todoItems.add(item5);

//        TodoData.getInstance().setTodoItems(todoItems);

        /*
        * What have we done here?
        * What we wanted in our application is that when it starts, we want the first item in the ListView to be
        * selected and display its details and deadline - but the method handleClickListView() only worked by
        * listening in on mouseClicks, now when we start program we wrote this line of code
        *   - todoListView.getSelectionModel().selectFirst();
        *
        * This selected the first item in the list; great what we wanted - but it didn't not display the details or
        * deadline.
        *
        * Why?
        * Our EventHandler handleClickListView() will only run, when <ListView> onMouseClick property listened to an
        * actual mouse click, but when we start the program, the user is not going to immediately press the first
        * item, we should be doing that for them.
        *
        * How to get around it?
        * We create what is known as a "Generic Listener", as we don't have any way of forcing a mouse click, how
        * about we make an EventListener ourselves which which will raise the Event to an EventHandler.
        * This will basically simulate the mouseClick, but for every instance basically. But instead the
        * EventListener is listening for when the ListView has an item selected - hence selectedItemProperty()
        *
        * Why does it work?
        * We use a method called addListener() which obviously will add a listener to this property, so whenever an
        * item is selected from the ListView, we want it to run this bit of code handleClickListView() (which we have
        * simulated, and renamed to more properly handleSelectedListView()) but instead of listening for mouseClicks,
        * we make an anonymous class which listens for selectedItems within the ListView
        * */

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observableValue, TodoItem oldValue,
                                TodoItem newValue) {
                if(newValue != null) {
                    handleSelectedListView();
                }
            }
        });

        /*
        * The way we are updating our list in the Main Window is very inefficient simply because the data may change
        * in the save file, but not reflected properly in the application - to mitigate this we will use something
        * called "data binding" - where we use a JavaFX collection which will essentially make sure all changed are
        * reflected.
        *
        * We also used ObservableArrayList in loadTodoItems() and storeTodoItems(), this is not only for the same
        * data binding technique, but also for performance reasons as well - the JavaFXCollections package is nearly
        * identical to java.util.Collections - but the code has been far more optimised for UI
        *
        * How?
        * Our ListView can call an EventHandler multiple times, and UI elements are very expensive - they not only
        * have to paint themselves to the screen but also then deal with Events, ideally we want the JavaFX to deal
        * with only one and one Event for each control
        *
        * In our TodoData Class, we changed "private List<TodoItem> todoItems;" to
        * "private ObservableList<TodoItem> todoItems;"
        *
        * What we have done?
        * We changed the List to an ObservableList (from the JavaFXCollections) this will allow us to bind the data
        * together
        *
        * so now in todoListView, we can use the method setItems() and just get the singleton class instance of
        * TodoData this also means we can get rid of the code which manually adds newItem to the ListView
        *
        * */

        todoListView.setItems(TodoData.getInstance().getTodoItems());
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();


        /*
         * Cell Factories, every item within the ListView is contained within a "cell", and all cells have default
         * values provided to by the parent Class which it descend from.
         *
         * What we want?
         * We want to manipulate the cell factory so that its short description is set to red if the deadline is
         * today's date
         *
         * How to do it?
         * we use the setCellFactory() method which the ListView has, and we pass it the argument "new Callback",
         * Callback from the JavaFX API, and this is what will allow us to do th changes required above
         *
         * Within the the overridden method, we write an anonymous instantiation which will override the updateItem()
         * and we want to keep the Parent Class functionality by calling the super(), we just want to add tad bit
         * extra to it, so we check if it is empty if it is we use the setText() to set it to null else to the
         * todoItem short description and check if its deadline is equal to today's date by using the LocalDate.now()
         *  if so, set the color of the text to red
         *
         * At the end of the anonymous call, we want finish it off with a return cell - the variable which ultimately
         * called this anonymous instantiation
         *
         * The method call() must return the second parameterised generic call in Callback, hence we return cell
         *
         * */
        todoListView.setCellFactory(new Callback<ListView<TodoItem>, ListCell<TodoItem>>() {
            @Override
            public ListCell<TodoItem> call(ListView<TodoItem> todoItemListView) {
                ListCell<TodoItem> cell = new ListCell<>() {
                    @Override
                    protected void updateItem(TodoItem todoItem, boolean empty) {
                        super.updateItem(todoItem, empty);
                        if(empty) {
                            setText(null);
                        } else {
                            setText(todoItem.getShortDescription());
                            if(todoItem.getDeadline().equals(LocalDate.now())) {
                                setTextFill(Color.RED);
                            }
                        }
                    }
                };
                return cell;
            }
        });
    }

    @FXML
    public void showNewItemDialog() {
        // This line here says "make a new window" but within the same stage of course
        Dialog<ButtonType> dialog = new Dialog<>();
        // We tell the dialog pane (sub window) that its owner or parent is the mainWindow where the item list it
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New Todo Item");
        dialog.setHeaderText("Create a new reminder");

        // We do this, which basically creates an instance of the todoItemDialog.fxml, so then we can call methods
        // etc from the controller class DialogController - which will allow us to retrieve data from the sub window
        // back into the Main Window
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));
        try {
            // reference the dialog (sub window) and set its content to the todoItemDialog.fxml file
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        // add "OK" and "CANCEL" buttons to the dialog
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        /*
        * We then get the result from the dialog (sub window) using the showAndWait() - this basically will show
        * (render) the buttons to the sub window, but also will wait till the end until the user has pressed either
        * "OK" OR "CANCEL" - so basically they cannot interact with the Main Window UI till they have finished using
        * the sub window
        * */
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            /*
            * We make an instance of the DialogController and set that equal to the fxmlLoader (which is the
            * todoItemDialog.fxml) so what we have basically done is associated the this class instance of
            * DialogController with the actual .fxml that it is based on. We use the getController() on it, which in
            * the documentation it will return the
            * */

            DialogController controller = fxmlLoader.getController();
            TodoItem newItem = controller.processResults();

//            todoListView.getItems().add(newItem);

            todoListView.getSelectionModel().select(newItem);
        }

    }

    private void handleSelectedListView() {
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        itemDetailsTextArea.setText(item.getDetails());
        deadlineLabel.setText(item.getDeadline().format(DateTimeFormatter.ofPattern("MMMM d, yyyy")));

//        System.out.println("The selected item is " + item);
//        StringBuilder sb = new StringBuilder(item.getDetails());
//        sb.append("\n\n\n\n");
//        sb.append("Due: ");
//        sb.append(item.getDeadline().toString());
//        itemDetailsTextArea.setText(sb.toString());

    }


}
