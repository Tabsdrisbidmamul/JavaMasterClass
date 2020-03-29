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
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

/*
* Controls
* Every Control we have used so far has a documentation to it, so looking at the documentation for the Button Class,
* we can see that its has its default attributes that are given to that Class, but not only that we can also see that
* the Class has also inherited from multiple classes - thus it will also have those superclasses attributes as well
*
* We are going to cover the most commonly used attributes to controls
*
* Button
* So with buttons you are mainly looking to add text and sometimes an image to the button
* To add text to the button you use the text field and pass the String literal to be the contents of the button
*
* For the image its a bit more work to do:
* In Desktop there is a folder called JavaFX Image Repos -> inside it is a .jar file we are going to add that to the
* project
*
*   1. Right click the project and go to "open module setting" (right at the bottom)
*   2. Click the "modules" tab, and within the modules, click "dependencies" tab.
*   3. Within the dependencies tab, you want to locate the plus icon (+) to the right of the window and click it
*   4. Once pressed, a dropdown will appear, you want to click "JARs and directories" option
*   5. This will bring up the file explorer, go to the where the .jar file is located and click "OK"
*   6. This will bring up a dialogue box, you want to click the "Classes" option and click "OK"
*   7. Once that is done, click "Apply" and then "OK".
*   8. Open up the "module-info.java" and add the following line:
*      requires jlfgr;
*   9. We should now see in "External Libraries" the jlfgr.jar file was added, we know that its worked successfully
*
* To add a graphic to the button we do this:
*   <?import javafx.scene.image.ImageView?>
*   <?import javafx.scene.image.Image?>

    <Button text="Click Me" GridPane.rowIndex="0" GridPane.columnIndex="0" >
        <graphic>
            <ImageView>
                <Image url="@/toolbarButtonGraphics/general/TipOfTheDay24.gif"/>
            </ImageView>
        </graphic>
    </Button>

* We add the <graphic> element, <ImageView> element and lastly <Image> element as children to Button, we pass a url
* prefixed with @, this tells Java to look in a relative path we have defined, and pass in the image location you
* want to add to the button
*
* Label
* So we know that we can use the text property in Label to output content to the GUI, but we can also change the
* colour of the text, its font and make it wrap-able (by default its static and not fluid). And just like Button we
* can also add images to the label as well
*
*    <Label text="This is a label" GridPane.rowIndex="0" GridPane.columnIndex="1" textFill="darkcyan">
        <font>
            <Font name="Sans-Serif italic" size="20"/>
        </font>
        <graphic>
            <ImageView>
                <Image url="@/toolbarButtonGraphics/general/Information24.gif"/>
            </ImageView>
        </graphic>
    </Label>
*
* Here we use the textFill property to choose a colour for the text
* We use the Font Class property to change its font, whether its italic, bold etc and its size
* And by putting the wrapText="true" as a property to Label, we get that the text in the GUI window is no wrappable
*
*
* Radio Button
* Now just like a radio, a radio can only be tuned to one radio station at a time, and this analogy is brought over
* to the radio buttons itself, where only one button can be selected at a time and the other buttons are then thus
* de-selected
    <RadioButton text="Red" GridPane.rowIndex="0" GridPane.columnIndex="2"/>
    <RadioButton text="Blue" GridPane.rowIndex="0" GridPane.columnIndex="3"/>
    <RadioButton text="Green" GridPane.rowIndex="0" GridPane.columnIndex="4"/>

* We use the element <RadioButton> to make a radio button in the layout, however when we run the code, we get all the
*  radio buttons on screen (which we want), but the problem now occurs is that we can select all the buttons, none of
*  them are showing the intended effects which we want (they are actually acting separately) To fix this we need to
* group them together and have them work dependent on the grouping
*
* Now to group them, we use a <ToggleGroup>, now something to remember about the ToggleGroup, is that it does not
* extend from Node, meaning that we cannot use it on its own in the fmxl file and in our scene graph, we have to wrap
* this class around a Node like object to get the functionality we want
*
* We use the <fx:define> element, and place the ToggleGroup as a child element to <fx:define>, and within ToggleGroup
*  we want to make an id which will pass to our radio buttons so they will work like normal
*   - fx:id="colorToggleGroup"
*
* In radio button, we want all our buttons to belong to the same group, so we reference it within their properties
*   - toggleGroup
* and set that equal to $ + ToggleGroupName
* The dollar tells Java to look for a fx definition with the ID which are passing
*   - "$colorToggleGroup"
*
*   - toggleGroup="$colorToggleGroup"
*
* <?import javafx.scene.control.ToggleGroup?>
*   ...
*     <fx:define>
        <ToggleGroup fx:id="colorToggleGroup"/>
      </fx:define>

    <RadioButton text="Red" GridPane.rowIndex="0" GridPane.columnIndex="2" toggleGroup="$colorToggleGroup"/>
    <RadioButton text="Blue" GridPane.rowIndex="0" GridPane.columnIndex="3" toggleGroup="$colorToggleGroup"/>
    <RadioButton text="Green" GridPane.rowIndex="0" GridPane.columnIndex="4" id="$colorToggleGroup"/>

* And if we want one of radio buttons to selected as default, we set the selected property to true for the radio
* button we want selected
*
*     <RadioButton text="Red" GridPane.rowIndex="0" GridPane.columnIndex="2" toggleGroup="$colorToggleGroup"
                 selected="true"/>

* CheckBox
* Now if the want to select or deselect an option say "Subscribe to our newsletter" then it makes sense to give a
* checkbox and not a radio button in this scenario
* The checkbox actually has 3 states ticked, not ticked and dashed
*
*     <CheckBox text="Subscribe to our Newsletter" GridPane.rowIndex="1" GridPane.columnIndex="0" indeterminate="true"/>
*
* The indeterminate option default to false, what this does is place a dash in the checkbox, but once click on the
* checkbox, it can never go back to the third state (dashed)
*
* To group our checkboxes together we cannot use the ToggleGroup instead place them into a VBox and from there you
* can determine which one has been selected via code in the Controller
*     <VBox GridPane.rowIndex="0" GridPane.columnIndex="5" spacing="5">
        <CheckBox text="Dog"/>
        <CheckBox text="Cat"/>
        <CheckBox text="Bird"/>
    </VBox>

* ToggleButton
* Its a button which ahs 2 states on and off (pressed down and pressed up)
*     <HBox GridPane.rowIndex="0" GridPane.columnIndex="6">
        <ToggleButton text="Toggle Me"/>
        <ToggleButton text="Hello"/>
        <ToggleButton text="Good-Bye" />
    </HBox>

* These ToggleButton descend from ToggleGroup, so we can group them together just like radio buttons
*
* We can also place the radio buttons in a VBox as well to make grouping easier in that sense
*
* TextField
* This controller places a text box into the GUI and allows the user to type in alphanumeric string literals into the
* GUI from the keyboard
*
*     <TextField GridPane.rowIndex="1" GridPane.columnIndex="0"/>
*
* TextField support cut, copy and paste and can have preloaded text in the text field - but this last option can very
*  irritating for both the user and the programmer
*
* PasswordField
* This controller descends directly from TextField - however it will mask the input with black dots and does not
* support cut, copy and paste
*
*     <PasswordField GridPane.rowIndex="1" GridPane.columnIndex="1"/>
*
* ComboBox
* Just like Radio buttons, when we want to offer a user with a list of many choices instead of using radio buttons
* (they will take up too much room) we use ComboBox instead - because it will only expand its options once the user
* has interacted with it - as it is a dropdown menu of options
* <?import javafx.collections.*?> (make sure these import are done, sometime IntelliJ does not do it automatically)
* <?import java.lang.String?>
* <?import javafx.scene.control.ComboBox?>
* ...
*     <ComboBox GridPane.rowIndex="1" GridPane.columnIndex="2">
        <items>
            <FXCollections fx:factory="observableArrayList">
                <String fx:value="Option 1"/>
                <String fx:value="Option 2"/>
                <String fx:value="Option 3"/>
                <String fx:value="Option 4"/>
                <String fx:value="Option 5"/>
            </FXCollections>
        </items>
    </ComboBox>
*
* When ran, this will produce a dropdown with options
* Now an issue can occur, if our dropdown option contents is too large to show in a small window, the text will be
* cut off, to fix that - set the ComboBox columnSpan to a large enough value like 3 or 4 or whatever length you need
* to accommodate the text
*
* Now we can add a
*         <value>
            <String fx:value="drop down here"/>
        </value>
just right underneath </value> and this allows us to select a default option for the ComboBox, you can do 2 things:
*   1. Set it to a value already in the items list
*   2. A value which notifies the use this is a drop down box - but this value is not actually part of the dropdown
*
*
* ChoiceBox
* Just like ComboBox, the ChoiceBox is another dropdown menu, however once selected - it will prefix a tick next to
* the option in the ChoiceBox - in the User experience they practically work the same, however from a developer
* standpoint they are different
*
*     <ChoiceBox GridPane.rowIndex="1" GridPane.columnIndex="3">
        <items>
            <FXCollections fx:factory="observableArrayList">
                <String fx:value="cb 1"/>
                <String fx:value="cb 2"/>
                <String fx:value="cb 3"/>
                <String fx:value="cb 4"/>
                <String fx:value="cb 5"/>
            </FXCollections>
        </items>
        <value>
            <String fx:value="choice box here"/>
        </value>
    </ChoiceBox>

* The ChoiceBox is more suited to a small list of options, and a ComboBox to a larger list of item
*
* Slider
* It presents a long bar with thumb know which allows the user to numerical select a value from the slider
*     <Slider GridPane.rowIndex="2" GridPane.columnIndex="0" GridPane.columnSpan="4" min="0" max="100"
            showTickLabels="true" showTickMarks="true" minorTickCount="4" snapToTicks="true"/>

With all these options on we allow the user to be precise with their option and they know roughly what they have chose
*
* Spinner
* Now this is actually for numeric value input, and its menu that allows the user to go up or down by 1 and its much
* more precise than the slider
* <Spinner GridPane.rowIndex="2" GridPane.columnIndex="4" min="0" max="100"/>
*
* If we set the editable property to true, we can allow the user to input numbers via the keyboard, but if they enter
*  an extremely large number the program wil crash - if the number entered is at the boundary of min and max the
* spinner will then snap it to the min or max value that was set in the spinner
*
* You can also set the initialValue="50" to any number within the range, this is useful, because when we set the
* editable property to true, the user ahs to press enter for the value in the Spinner to save - if they don't then
* the value will default to the previous value - so its best to use editable in conduction with initialValue
*
* ColourPicker
* The color picker allows the user to select colors from a dropdown menu
*   - <ColorPicker GridPane.rowIndex="3" GridPane.columnIndex="0"/>
*
* DatePicker
* Allows us to input a date to be entered
*   - <DatePicker GridPane.rowIndex="3" GridPane.columnIndex="1"/>
*
* TitledPane
* This is not a layout!
* This basically a toggle to show the data within the pane, and once pressed it will expand to show the data, and
* when closed will hide the data only showing the title of the pane
*    <TitledPane text="Titled Pane" GridPane.rowIndex="3" GridPane.columnIndex="2">
        <Label text="Label in Titled Pane"/>
    </TitledPane>

* The TitledPane is best used in the Accordion element, so this will take a pane as an element - not it can take
* other elements as well, and you can place many panes within it, now the feature about this is that it will only
* show one pane at a time, the other panes will close automatically
*
*     <Accordion GridPane.rowIndex="3" GridPane.columnIndex="2">
        <panes>
            <TitledPane text="Titled Pane">
                <Label text="Label in Titled Pane"/>
            </TitledPane>
            <TitledPane text="Titled Pane 2">
                <Label text="Label 2 in Titled Pane"/>
            </TitledPane>
            <TitledPane text="Titled Pane 3">
                <Label text="Label 3 in Titled Pane"/>
            </TitledPane>
        </panes>
    </Accordion>

* Now when we ran the program, all the accordions are closed by default, we can make it so that one of the panes are
* opened by default when the application starts
*
* so we first set the id of one the TitlePane
*   <TitledPane fx:id="tp3" text="Titled Pane 3">
*       <Label text="Label 3 in Titled Pane"/>
*   </TitledPane>
*
* and in the Accordion element property we set the expandedPane="$tp3" to the ID of the TitlePane so when ran,
* TitlePane 3 is opened by default
* */
