package application;
 
import java.sql.Connection;
import java.sql.DriverManager;

import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
 
public class ui_login_controller {
    @FXML private Text actiontarget;
    
    @FXML private PasswordField password_field;
    
    @FXML private TextField user_name;
    
    @FXML private Button button_signIn;
    
	@FXML protected void handleSubmitButtonAction(ActionEvent event) {
    	Connection connection = null;
    	
    	String userName=user_name.getText();
    	String pwd=password_field.getText();
    	String connectionString = "jdbc:mysql://127.0.0.1:3306/科研管理数据库?user="+userName+"&password="+pwd+"&useSSL=FALSE&serverTimezone=GMT&allowPublicKeyRetrieval=true";
    	//String connectionString = "jdbc:mysql://127.0.0.1:3306/科研管理数据库?user=科研人员1&password=2&useSSL=FALSE&serverTimezone=GMT&allowPublicKeyRetrieval=true";
    	 try {
				connection = DriverManager.getConnection(connectionString);
				// 退出全部界面!!!
				// Platform.exit();
				// 创建新界面
				ui_function_implementation aui=new ui_function_implementation(connection);
				aui.showUIFunction(new Stage());
			} catch (Exception e) {
				e.printStackTrace();

				if (userName.isEmpty() && pwd.isEmpty()) {
					actiontarget.setText("请输入用户名和密码");
					DisWroMess("登录错误", "请输入用户名和密码");
				} else if (userName.isEmpty()) {
					actiontarget.setText("请输入用户名");
					DisWroMess("登录出错", "请输入用户名");
				} else if (pwd.isEmpty()) {
					actiontarget.setText("请输入密码");
					DisWroMess("登录错误", "请输入密码");
				} else {
					actiontarget.setText("用户名或密码错误");
					DisWroMess("登录错误", "用户名或密码错误");
				}
			}
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
