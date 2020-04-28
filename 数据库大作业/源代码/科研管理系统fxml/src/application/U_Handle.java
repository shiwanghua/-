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

public class U_Handle {
	
	//处理更改--强实体集表--科研人员
	void handleUST4(Connection connection)
	{
		GridPane grid = new GridPane();
		grid.setHgap(10);   
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("请输入您的工号，然后点击“查找”按钮");
		scenetitle.setFont(Font.font("华文隶书", FontWeight.NORMAL, 17));
		grid.add(scenetitle, 0, 0, 2, 2);

	    Label noL = new Label("工号:");
	    grid.add(noL, 0, 2);
	    Label nameL = new Label("姓名:");
	    grid.add(nameL, 0, 3);
	    Label genderL = new Label("性别:");
	    grid.add(genderL, 0, 4);
	    Label ageL= new Label("年龄:");
	    grid.add(ageL, 0, 5);
	    Label positional_titleL = new Label("职称:");
	    grid.add(positional_titleL, 0, 6);
	    Label research_directionL = new Label("研究方向:");
	    grid.add(research_directionL, 0, 7);
	    
	    TextField 工号 = new TextField();
	    grid.add(工号, 1, 2);
	    TextField 姓名 = new TextField();
	    grid.add(姓名, 1, 3);
	    TextField 性别 = new TextField();
	    grid.add(性别, 1, 4);
	    TextField 年龄 = new TextField();
	    grid.add(年龄, 1, 5);
	    TextField 职称 = new TextField();
	    grid.add(职称, 1, 6);
	    TextField 研究方向 = new TextField();
	    grid.add(研究方向, 1, 7);		    
	    
	    Button btnQuery = new Button("查找");
	    HBox hbBtnQ = new HBox(10);
	    hbBtnQ.setAlignment(Pos.CENTER);
	    hbBtnQ.getChildren().add(btnQuery);
	    grid.add(hbBtnQ, 2, 2);
	    
	    Button btnUpdate = new Button("确认修改");
	    HBox hbBtnU = new HBox(10);
	    hbBtnU.setAlignment(Pos.CENTER);
	    hbBtnU.getChildren().add(btnUpdate);
	    grid.add(hbBtnU, 2, 7);
	    
	    final Text actiontarget = new Text();
	    grid.add(actiontarget, 0, 8);
	    grid.setColumnSpan(actiontarget, 2);
	    grid.setHalignment(actiontarget, RIGHT);
	    actiontarget.setId("actiontarget");
	    actiontarget.setFont(Font.font("华文行楷", FontWeight.BOLD, 30));
	    
	    //输入工号，查找自己的个人信息
	    btnQuery.setOnAction(ActionEvent->{
	    	String no,name,gender,age,positional_title,research_direction;
	    	no=工号.getText();
	    	String query_stmt="select 姓名,性别,年龄,职称,研究方向 from 科研人员 where 工号='"+no+"';";
	    	try {
				Statement stmt = connection.createStatement();
				ResultSet resQ = stmt.executeQuery(query_stmt);
				resQ.next();
				name=resQ.getString(1);gender=resQ.getString(2);age=resQ.getString(3);
				positional_title=resQ.getString(4);research_direction=resQ.getString(5);
				姓名.setText(name);性别.setText(gender);年龄.setText(age);
				职称.setText(positional_title);研究方向.setText(research_direction);
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				DisWroMess("查找错误","您输入的工号不存在");
				e.printStackTrace();
			}
	    	
		});
	    
	    //响应对个人信息的修改
	    btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
	    	String no,name,gender,age,positional_title,research_direction;
			@Override
			public void handle(ActionEvent event) {
				no=工号.getText();name=姓名.getText();gender=性别.getText();age=年龄.getText(); 
			    positional_title=职称.getText();research_direction=研究方向.getText();
				String update_stmt="update 科研人员 set 工号='"+no+"',姓名='"+name+"',性别='"+gender+"',年龄='"+age+
						"',职称='"+positional_title+"',研究方向='"+research_direction+"' where 工号='"+no+"';";
					try {
						Statement stmt = connection.createStatement();
						int res = stmt.executeUpdate(update_stmt);
						actiontarget.setFill(Color.DARKSEAGREEN);
		                if(res==1) actiontarget.setText("修改成功!");
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						actiontarget.setFont(Font.font("宋体", FontWeight.LIGHT, 15));
						actiontarget.setText("错误信息："+e.toString());  
						if(no.equals("")||name.equals("")||gender.equals("")||age.equals("")||positional_title.equals("")||research_direction.equals(""))
							DisWroMess("更新失败", "您的信息输入不全，请补全！");
						else DisWroMess("更新失败", "您的输入有误，请重新输入");
					}
			}});

		Scene scene=new Scene(grid, 350, 300);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.setTitle("修改“科研人员”表");
		stage.show();
	}

	  public void DisWroMess(String title,String message)
	    {
	    	Stage window = new Stage();
	        window.setTitle(title);
	        window.initModality(Modality.APPLICATION_MODAL);
	        window.setMinWidth(270);
	        window.setMinHeight(150);

	        Label label = new Label(message);
	        Button button = new Button("OK");
	        button.setOnAction(e -> window.close());

	        VBox layout = new VBox(12);
	        layout.getChildren().addAll(label , button);
	        layout.setAlignment(Pos.CENTER);

	        Scene scene = new Scene(layout);
	        window.setScene(scene);
	        window.showAndWait();

	    }
}
