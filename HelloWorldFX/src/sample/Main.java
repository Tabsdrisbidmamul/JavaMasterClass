package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

//        GridPane root = new GridPane();
//        root.setAlignment(Pos.CENTER);
//        root.setVgap(10);
//        root.setHgap(10);
//
//        Label greeting = new Label("Welcome to JavaFX!");
//        greeting.setTextFill(Color.GREEN);
//        greeting.setFont(Font.font("Times New Roman", FontWeight.BOLD, 70));
//        root.getChildren().add(greeting);

        primaryStage.setTitle("Hello JavaFX!");
        primaryStage.setScene(new Scene(root, 700, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

/*
* JavaFX
* For every JavaFX application, we must always do these configurations or else the program will not run
* right-click the project folder -> open module settings (right at the bottom)
* -> make sure that both the tab options "project" and "module" are using JDK-11 or else this won't work
* -> Then go to "global libraries" and right click JavaFX-11, and press the option "add to module" - and click ok to
* add this to the current project
*
* Then right click the src folder, and add a new file "module-info.java" this is an option, and inside it you must add
*   // add these 2 always when configuring JavaFX
    requires javafx.fxml;
    requires javafx.controls;

    // package name here
    opens sample;
*
* JavaFX MVC pattern
* The JavaFX uses the MVC pattern, so the
*   - Applications data model is the model
*   - The FXML is the view
*   - The code that determines what happens when a user interacts with the UI is the controller
* Whilst the JavaFX does not enforce this pattern - its a very good idea to use it
*
* Main.java
* Within the Main.java, we have the class Main, and this must always extend the Application abstract class - as this
* is the entry point to all JavaFX applications
* The application manages the lifecycle of a JavaFX application - and the methods we really care about within this
* class are the init(), start() and stop() methods
* When we run the application, application.launch() will be called from the main method (psvm) this method will
* launch the JavaFX application, and does not return until the application has been exited
*
* When any JavaFX application is launched - the init() is ran first - so when we start a fresh application - this
* method is empty and it won't do anything - unless we override it
*
* The start() will ran right after the init() method - we must override this method - as start is an abstract method
* in the application class - we will create the UI in the start method
*
* The stop() is usually run when the user closes the window - and just like the init(), this method is empty in the
* application class - so unless we override it - it won't do anything
*
* start()
* This method takes a Stage parameter - and this is a top-level JavaFX container that extends the window class - so
* basically its the main window - at runtime the JavaFX constructs the initial stage and passes into the start method
*  - we can create other stages, but most application will only have one top-level window; having too many windows
* can lead to a bad user experience
*
* Creating stages - The JavaFX devs were going for a theatre analogy when creating the class names
* In this version of JavaFX dialogues are presented within a stage - but we don't explicit create stage, we have to
* use the dialogue class - which wraps the dialogue into a stage for us
*
* Line 13 is what loads the UI from the FXML file - FXML is a variant of XML - and the one provided by JavaFX is the
* default GridPane
*
* .fxml file
* The fx:controller attribute tells the runtime which class is the controller for this window -> and its equal to
* "packageName.ControllerClass", as we don't have any child elements - we only are getting one window at runtime
* So going back to Line 13, when load the UI elements, all the files are constructed
*
* At Line 14, we set the title of the stage - and as we only have one stage (being the top-level) parent window the
* title of that window will be whatever the argument has been passed as (so in this case "Hello World")
*   - primaryStage.setTitle("Hello World);
*
* At Line 15, we are setting the stage as Scene - each stage requires a scene, and backing each scene is a graph a
* tree where each node corresponds to a UI control or an area of the scene (for example a rectangle)
* In more detail: when we loaded the FXML (Line 13), we assigned it of type "Parent" with the reference name "root" -
*  the Parent class descends directly from the Node class:
*               Node
*                |
*                |
*              Parent
*
* So a Node that descend from Parent can children in the Scene graph
*
* For the HelloWorld application, the top-level and only node is in fact the GridPane - so the GridPane Node will be
* the root of the scene graph - and that was what returned from the FXMLLoader.load() from line 13
* When we construct a scene we have to pass in the root of the scene graph that will back the scene which we do here,
*  we are also setting the width and height of the scene - which of course will be the width and height for the main
* window
*   - primaryStage.setScene(new Scene(root, width, height));
*
* So why Stages and Scenes?
* Imagine watching a theatre - the curtain opens and we see the stage and the current scene showing with the actors
* and their dialogue for that scene. Now the curtain closes - they are changing scenes - and the curtain opens, what
* we see now is probably the same actors saying different dialogue of course because the scene has changed, the stage
*  itself hasn't - they are all still within the same stage - the same can be said with JavaFX
*
* Going back to JavaFX - say we were going to make a wizard for the user to go through - we first set the scene in
* the top-level stage - and when the user presses next or back - instead of hiding UI elements - we make a new scene
* and set it within the stage. You can see just like the theatre analogy - are actors may have changed and are saying
*  new dialogue because the scene they were in previously has changed - but the stage itself has not!
*
* Going back to the code, so going back to Line 15, we have instantiated all the files with our associated scene and
* we've set stage - so we are ready to show UI (the play) we do this by calling the show() on our stage (Line 16)
*   - primaryStage.show();
*
* So when we run this, we are getting an empty window with the tile "Hello World" no surprises there, and the stage
* class provides all the window decorations such as: close resize and minimise buttons
*
*
* Making Modifications
* NOTE: You can imagine almost everything like HTML elements (obviously to not the extremest, but as something
* similar to work with) and that every element has a its border, and when placed within another element then it
* becomes a child to that element adhering to the parent container border etc.
*
* So now we are going to make some modifications to the UI - and we can do this 2 ways
*   1. using the FXML file
*   2. coding it all manually
*
* We are going with the latter (opt 2) and at line 17, we created a GridPane Object called root, this is what we
* discussed earlier above - but this is basically a component to the scene - the GirdPane being a manual FXML basically
* then at lines 18, 19 and 20 we set the alignment to Pos.CENTER (center), the vGap (vertical gap) and hGap
* (horizontal gap) to 10. These settings are identical to the attributes that are in the FXML file
*
*   GridPane root = new GridPane();
*   root.setAlignment(Pos.CENTER);
*   root.setVgap(10);
*   root.setHgap(10);
*
* the alignment will determine where the grid will sit within the GridPane width and height - the alignment takes a
* Pos value, so we are using Pos.CENTER
*
* running the code now will result in an empty window with the title "Hello JavaFX!", but this code does not use the
* FXML file at all
*
* Adding a greeting to the Window
* We are going to use the Label (descends from Node) to add it to the Window, when using the Label make sure that
* accept the import or do this line at the imports at the top
*   - import javafx.scene.control.Label;
*
* Next we are going to create the label control and add it to the window
*   Label greeting = new Label("Welcome to JavaFX!");
*   root.getChildren().add(greeting);
*
* The Label constructor can accept multiple params, but we are passing in just a String literal, and then we use the
* root reference to call the getChildren()
*   - getChildren(): This will return a list of all the children
*   - add(): from the List interface - we are appending a new object to the list
*
* We then use that List to call the add() method from the List, and this will be taking a Node object as a param to
* add our new Label control (new child) to the List
*
* We always have to do this way, there is no shortcut
*
* Set the colour of the Label Control
* to change the colour of the Label control we use the setTextFill() method passing in a Color object
* Make sure that this import is done at the top or Java does not know where to get the colour from
*   - import javafx.scene.paint.Color;
*
* setting the colour
*   - greeting.setTextFill(Color.GREEN);
*
* Setting the Font and font weight
* To change the font of the text we use the setFont() from the label control, and we will be passing in a method call
* to a static method in the Font class, this method will return a Font with the values of which pass into it
* Make sure these imports are done so Java knows where to look for these methods
*   - import javafx.scene.text.Font;
*   - import javafx.scene.text.FontWeight;
*
* This is of course done before we add the label control to the children list or else we would not see the change we
* made
*   - greeting.setFont(Font.font("Times New Roman", FontWeight.BOLD, 70));
*
* We set the font to "Times New Roman" it must exactly match - or the font will not load, we then set the "FontWeight"
* to "BOLD" using the static final constant found in the FontWeight Class, and then lastly we pass a double to note
* the font size being 70 in this case.
*
* Running the program, we see that the window has green text, in Times New Roman, being bold and very large - the
* font is larger than the window you see - the window height is okay, but the width must be made larger to view all
* the text - without the user resizing the window
*
* We do this by modifying the width value where we set the scene
* Original window width
*   - primaryStage.setScene(new Scene(root, 300, 275));
*
* Modified window width
*   - primaryStage.setScene(new Scene(root, 700, 275));
*
* Now running this allows us to see that all the text is now readable within the window
*
* Do all the above changes but in FXML file
* As mentioned before FXML is flavour of XML so we will be doing almost everything in tags
*
* Firstly we want the Label control (tag) as a child element of GridPane - we do this by placing <Label></Label>
* within <GridPane></GridPane>
*
* Now the funny part comes here, we don't actually place the text within the tags, rather they are attributes that
* are associated with the tag - so for Label, we pass the text attribute "Welcome to JavaFX" and the text will appear
* up like normal, we also pass textFill the colour "green" - IntelliJ is helpful and gives us a dropdown menu of a
* colour picker for us
*
* To change the font, we have to make font a child element of Label - as Font cannot exist without any text,
* <font></font>
* font needs another child element <Font></Font> note the uppercase 'F' in Font, this refers to the Font class and
* not the font to which the Label class is trying to get access to.
*
* Within the Font tag, we specify the name of the font being "Times New Roman" and its size. You see when we try to
* put fontweight - Java is not having any of it, this is because how JavaFX works, and to fix it we would do this line
*   - <Font name="Times New Roman bold" size="70" />
*
*
* JavaFX Font does not have a fontweight attribute
* <GridPane fx:controller="sample.Controller"
          xmlns:fx="http://javafx.com/fxml" alignment="center" hgap="10" vgap="10">
    <Label text="Welcome to JavaFX!" textFill="green">
        <!-- Comment -->
        <font>
            <Font name="Times New Roman" size="70" fontweight="bold" />
        </font>
    </Label>
* </GridPane>
*
* We have to put bold within the name itself
* * <GridPane fx:controller="sample.Controller"
          xmlns:fx="http://javafx.com/fxml" alignment="center" hgap="10" vgap="10">
    <Label text="Welcome to JavaFX!" textFill="green">
        <!-- Comment -->
        <font>
            <Font name="Times New Roman bold" size="70" />
        </font>
    </Label>
* </GridPane>
*
*
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
*
* */
