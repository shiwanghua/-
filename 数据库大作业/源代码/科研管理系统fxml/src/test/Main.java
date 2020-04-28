package test;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Menus");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250, Color.WHITE);

        MenuBar menuBar = new MenuBar();

        Menu menu = new Menu("File");
        menu.getItems().add(new MenuItem("New"));
        menu.getItems().add(new MenuItem("Save"));
        menu.getItems().add(new SeparatorMenuItem());
        menu.getItems().add(new MenuItem("Exit"));

        CustomMenuItem customMenuItem = new CustomMenuItem(new Slider());
        customMenuItem.setHideOnClick(false);
        menu.getItems().add(customMenuItem);

        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

        Menu fileMenu = new Menu("_File");
        fileMenu.setMnemonicParsing(false);
        MenuItem exitMenuItem = new MenuItem("Exit");
        fileMenu.getItems().add(exitMenuItem);
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        menuBar.getMenus().add(menu);
        menuBar.getMenus().addAll(fileMenu);
        root.getChildren().add(menuBar); 
        
        ContextMenu  contextFileMenu = new ContextMenu(menu);
        primaryStage.addEventHandler(MouseEvent.MOUSE_CLICKED,  (MouseEvent  me) ->  {
            if (me.getButton() == MouseButton.SECONDARY  || me.isControlDown())  {
                contextFileMenu.show(root, me.getScreenX(), me.getScreenY());
            }  else  {
                contextFileMenu.hide();
            }});

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
