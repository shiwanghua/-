package application;

import static javafx.geometry.HPos.RIGHT;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class I_Handle {
	
		//处理插入--强实体集表--科研人员
		void handleIST4(Connection connection) throws SQLException
		{	
			GridPane grid = new GridPane();
			grid.setHgap(10);   
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));

			Text scenetitle = new Text("请输入您的个人信息：");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(scenetitle, 0, 0, 2, 1);

		    Label noL = new Label("工号:");
		    grid.add(noL, 0, 1);
		    Label nameL = new Label("姓名:");
		    grid.add(nameL, 0, 2);
		    Label genderL = new Label("性别:");
		    grid.add(genderL, 0, 3);
		    Label ageL= new Label("年龄:");
		    grid.add(ageL, 0, 4);
		    Label positional_titleL = new Label("职称:");
		    grid.add(positional_titleL, 0, 5);
		    Label research_directionL = new Label("研究方向:");
		    grid.add(research_directionL, 0, 6);
		    
		    TextField noField = new TextField();
		    grid.add(noField, 1, 1);
		    TextField nameField = new TextField();
		    grid.add(nameField, 1, 2);
		    TextField genderField = new TextField();
		    grid.add(genderField, 1, 3);
		    TextField ageField = new TextField();
		    grid.add(ageField, 1, 4);
		    TextField positional_titleField = new TextField();
		    grid.add(positional_titleField, 1, 5);
		    TextField reserach_directionField = new TextField();
		    grid.add(reserach_directionField, 1, 6);		    
		    
		    Button btn = new Button("注册");
		    HBox hbBtn = new HBox(10);
		    hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		    hbBtn.getChildren().add(btn);
		    grid.add(hbBtn, 1, 7);
		    
		    final Text actiontarget = new Text();
		    grid.add(actiontarget, 0, 8);
		    grid.setColumnSpan(actiontarget, 2);
		    grid.setHalignment(actiontarget, RIGHT);
		    actiontarget.setId("actiontarget");
		    actiontarget.setFont(Font.font("华文行楷", FontWeight.BOLD, 30));
		    btn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					String no=noField.getText(),name=nameField.getText(),gender=genderField.getText(),age=ageField.getText(), 
							   positional_title=positional_titleField.getText(),research_direction=reserach_directionField.getText();
						String insert_stmt="insert into 科研人员 value('"+no+"','"+name+"','"+gender+"','"+age+"','"+positional_title+"','"+research_direction+"');";
						try {
							Statement stmt = connection.createStatement();
							boolean res = stmt.execute(insert_stmt);
							actiontarget.setFill(Color.DARKSEAGREEN);
			                if(!res==true) actiontarget.setText("注册成功!");
							stmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							actiontarget.setFont(Font.font("宋体", FontWeight.LIGHT, 15));
							actiontarget.setText("错误信息："+e.toString());  
							if(no.equals("")||name.equals("")||gender.equals("")||age.equals("")||positional_title.equals("")||research_direction.equals(""))
								DisWroMess("注册失败", "您的信息输入不全，请补全！");
							else DisWroMess("注册失败", "您的输入有误，请重新输入");
						}
				}});
  
			Scene scene=new Scene(grid, 290, 320,Color.BURLYWOOD);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.setTitle("插入“科研人员”表");
			stage.show();
		}

		  public void DisWroMess(String title,String message)
		    {
		    	Stage window = new Stage();
		        window.setTitle(title);
		        window.initModality(Modality.APPLICATION_MODAL);
		        window.setMinWidth(270);
		        window.setMinHeight(125);

		        Button button = new Button("OK");
		        button.setOnAction(e -> window.close());

		        Label label = new Label(message);

		        VBox layout = new VBox(12);
		        layout.getChildren().addAll(label , button);
		        layout.setAlignment(Pos.CENTER);

		        Scene scene = new Scene(layout);
		        window.setScene(scene);
		        window.showAndWait();

		    }
}
