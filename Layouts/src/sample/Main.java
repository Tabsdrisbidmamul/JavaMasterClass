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
        primaryStage.setScene(new Scene(root, 700, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

/*
* Layouts
* They allow us add controls which are UI components to a container without having to write the code required to
* manage the positioning and also the resizing behaviour of those controls now the layout will manage all of that for
* us which is good for our part
*
* JavaFX has got 8 layouts
*   - GridPane
*   - AnchorPane
*   - StackPane
*   - HBox
*   - VBox
*   - FlowPane
*   - TilePane
*   - BorderPane
*
* Preferred Size
* Every control (so control being Objects like Label with have just seen, so UI components) computes is preferred size
* based on its contents - so preferred size here means the preferred width and preferred height of the control when
* it is displayed
*
* So as an example JavaFX has a got a button, and the button control will size itself so there is enough room to
* display its content whatever is actually in there - and the user can read the text. So if we were working with an
* "OK" button, the button control will size itself so that its border fits around the text "OK"
*
* So when we place controls into a layout, it then becomes a child of that layout, so some layouts prefer that their
* children display at their preferred widths and height and sometimes it would depends on where controllers place
* within the layout
*
* GridPane
* GridPane lays out its children in flexible rows and columns - hence the name GridPane
* Each position in a grid is called a cell - again by default, rows and columns will be sized to fit their content
* (so a row can be as tall as the tallest controller it contains, and column and be as wide as the widest controller
* it contains)
*
* HBox
* HBox lays out its children horizontally in a single row and sizes its children to their preferred widths, now if
* there is any horizontal space left over, its going to stretch itself to fill the excess rather than stretching its
* children. If there is extra height, will depend on the fillHeight property, and when fillHeight is true (it is by
* default) the HBox is going to stretch itself to fill any extra height; and if its false, its going to try to resize
* its children to fill that extra height.
*
* We often use a HBox to set out a layout of buttons in a dialogue, and because all layouts inherit from the same
* layout Class Pane, they share a lot of the same properties
*
* VBox
* Just like the HBox, it will layout its children vertically and generally it will let the children take their
* preferred height
*
* You mainly use these 2 as children within another layout and not on their own
*
* BorderPane
* One of the most commonly used layouts for top-level windows, so when you are using BorderPane, you can place
* controls within 5 positions: TOP, BOTTOM, LEFT, RIGHT and CENTER
*
* ---------------------------------------------------------------------------------------------------------------------
* |                                                      TOP                                                           |
* |                                                                                                                    |
* |                                                                                                                    |
* |--------------------------------------------------------------------------------------------------------------------|
* |             |                                                                                        |             |
* |             |                                                                                        |             |
* |             |                                                                                        |             |
* |             |                                                                                        |             |
* |    LEFT     |                                       CENTER                                           |    RIGHT    |
* |             |                                                                                        |             |
* |             |                                                                                        |             |
* |             |                                                                                        |             |
* |             |                                                                                        |             |
* |             |                                                                                        |             |
* ----------------------------------------------------------------------------------------------------------------------
* |                                                                                                                    |
* |                                                     BOTTOM                                                         |
* |                                                                                                                    |
* ----------------------------------------------------------------------------------------------------------------------
*
* Often you have a
*   - toolbar at the TOP
*   - trees or list to LEFT
*   - data is displayed at the CENTRAL area or allow the user to enter data
*   - a status bar at the BOTTOM
*   - sometimes an information panel or something else to the RIGHT
*
* Children at the TOP and BOTTOM will tend to have their preferred heights and extends the width of the BorderPane
* (not always the case)
* Children at the LEFT and RIGHT will have their preferred widths and extends the full height between the TOP and
* BOTTOM positions
* Children at the CENTER will get whatever space is left over and will fill that entire space
*
* Now we don't have to place something into every position, when a position is empty, no space is allocated to it
* for example - if the RIGHT is empty, then the CENTER will extend to the right edge or the BorderPane (basically
* filling the entire RIGHT side up)
*
* FlowPane
* This Pane is very similar to the HBox and VBox layout, but what differentiates it is that it wraps its children -
* so with a HBox the children are laid horizontally as a single row - now if the window is resized, so there isn't
* enough room then the children will be cut off and that principle applies to the VBox as well (so it lays out its
* children vertically and if there isn't enough height to fill them all, then some children will be cut off)
*
* Now when using a FlowPane, children will not be cut off - unless the user resize the window to not show them all
* (to the bear minimum showing only the close, minimise and drop decorations - so if the orientation of FlowPane is
* set to horizontal and the user resizes the window so all the children cannot fit into a single row, then the layout
* will then actually wrap the children to the next row, that's really the big difference - and of course if the user
* increases the width of the window, then the children will pop back to the previous row
*
* The same applies to the vertical orientation
*
*
* AnchorPane
* Another popular layout, now what this does; it allows us to anchor children to their edges which can influence what
* happens when the pane is resized, for example you might want to anchor a title to the top of the pane so that when
* the window is resized - a bunch of space is not added to the title and the window title bar or we could anchor a
* HBox to the bottom to the Pane
*
* */
