package application;

import static javafx.geometry.HPos.RIGHT;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class handleResearchers {
	
	void functionSelect(Connection c)
	{
		GridPane grid = new GridPane();
		grid.setHgap(10);   
		grid.setVgap(10);
		grid.setPadding(new Insets(40, 25, 10, 60));
	   
	    Button btn1 = new Button("注册研究员");
	    Button btn2 = new Button("更改个人信息");
	    Button btn3 = new Button("查询研究室秘书");
	    Button btn4 = new Button("查询研究室信息");
	    Button btn5 = new Button("查询个人项目");
	    Button btn6 = new Button("查询项目成员");
	    
	    btn1.setTextFill(Color.DEEPSKYBLUE);
	    btn1.setFont(Font.font("幼圆", FontWeight.MEDIUM, 20));
	    btn1.setLayoutX(40);
	    btn1.setLayoutY(30);
	    btn1.setMinSize(60, 30);
	    grid.add(btn1, 0, 0);
	    
	    btn2.setTextFill(Color.DEEPSKYBLUE);
	    btn2.setFont(Font.font("幼圆", FontWeight.MEDIUM, 20));
	    btn2.setLayoutX(40);
	    btn2.setLayoutY(80);
	    btn2.setMinSize(60, 30);
	    grid.add(btn2, 0, 1);
	    
	    btn3.setTextFill(Color.DEEPSKYBLUE);
	    btn3.setFont(Font.font("幼圆", FontWeight.MEDIUM, 20));
	    btn3.setLayoutX(40);
	    btn3.setLayoutY(130);
	    btn3.setMinSize(60, 30);
	    grid.add(btn3, 0, 2);
	    
	    btn4.setTextFill(Color.DEEPSKYBLUE);
	    btn4.setFont(Font.font("幼圆", FontWeight.MEDIUM, 20));
	    btn4.setLayoutX(40);
	    btn4.setLayoutY(180);
	    btn4.setMinSize(60, 30);
	    grid.add(btn4, 0, 3);
	    
	    btn5.setTextFill(Color.DEEPSKYBLUE);
	    btn5.setFont(Font.font("幼圆", FontWeight.MEDIUM, 20));
	    btn5.setLayoutX(40);
	    btn5.setLayoutY(230);
	    btn5.setMinSize(60, 30);
	    grid.add(btn5, 0, 4);
	    
	    btn6.setTextFill(Color.DEEPSKYBLUE);
	    btn6.setFont(Font.font("幼圆", FontWeight.MEDIUM, 20));
	    btn6.setLayoutX(40);
	    btn6.setLayoutY(280);
	    btn6.setMinSize(60, 30);
	    grid.add(btn6, 0, 5);
	    
	    //响应注册研究员功能
		I_Handle researcherRegister=new I_Handle();
	    btn1.setOnAction(ActionEvent->{
				try {
					researcherRegister.handleIST4(c);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
	    
	    //响应更改个人信息功能
	    U_Handle researcherInfoUpdate=new U_Handle();
	    btn2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				researcherInfoUpdate.handleUST4(c);
			}
	        });

	    //响应查询研究室——秘书功能
	    Q_Handle resSecQuery=new Q_Handle();
	    btn3.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	public void handle(ActionEvent event) {
	    		try {
					resSecQuery.handleQRT1(c);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
		});
	    
	    //响应查询研究室——信息功能
	    Q_Handle researchInfoQuery=new Q_Handle();
	    btn4.setOnAction(ActionEvent->{
	    	try {
				researchInfoQuery.handleQST1(c);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    });
	    
	    //响应查询个人项目功能
	    Q_Handle personalProject=new Q_Handle();
	    btn5.setOnAction(ActionEvent->{
	    	personalProject.handlePersonalProject(c);
	    });
	    //响应查询项目成员
	    
		Scene scene=new Scene(grid, 350, 400,Color.BURLYWOOD);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.setTitle("科研人员--功能选择");
		stage.show();
	}
	
}
