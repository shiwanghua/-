package UIlogin;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import static javafx.geometry.HPos.RIGHT;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class login extends Application{
	 public static void main(String[] args) {
	        launch(args);
	    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		    primaryStage.setTitle("科研项目管理系统--登录界面");
		    
		    GridPane grid = new GridPane();
		    grid.setAlignment(Pos.CENTER);
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(30, 30, 30, 30));
	        
	        Button btn2; 
	     
	        btn2=new Button("清空");
	            
	        Text uiSceneTitle = new Text("Welcome");
	        uiSceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        grid.add(uiSceneTitle, 0, 0, 2, 1);	

	        Label userName = new Label("User Name:");
	        grid.add(userName, 0, 1);

	        TextField userTextField = new TextField();
	        grid.add(userTextField, 1, 1);

	        Label pw = new Label("Password:");
	        grid.add(pw, 0, 2);

	        PasswordField pwBox = new PasswordField();
	        grid.add(pwBox, 1, 2);

	        Button btn1 = new Button("Sign in");
	        HBox hbBtn = new HBox(10);
	        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);//右对齐
	        hbBtn.getChildren().add(btn1);
	        grid.add(hbBtn, 1, 4);

	        final Text actiontarget = new Text();
	        grid.add(actiontarget, 0, 6);
	        GridPane.setColumnSpan(actiontarget, 2);
	        GridPane.setHalignment(actiontarget, RIGHT);
	        actiontarget.setId("actiontarget");

	        btn1.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent e) {
	        	actiontarget.setFill(Color.FIREBRICK);
	        	actiontarget.setText("Sign in button pressed");
	        }
	        });
	        Scene scene = new Scene(grid, 400, 245);
	        primaryStage.setScene(scene);
	        primaryStage.show();
		
	}
		
}