package application;

import java.sql.Connection;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

public class ui_function_implementation extends Application{

	private Connection connection;
	private I_Handle insert_handler=new I_Handle();
	private D_Handle delete_handler=new D_Handle();
	private U_Handle update_handler=new U_Handle();
	private Q_Handle query_handler=new Q_Handle();
	
	public ui_function_implementation(Connection con) {
		connection=con;
	}
	
	  @Override
	  public void start(Stage primaryStage) {

	    MenuBar menuBar = new MenuBar();
	    ToggleGroup tGroup=new ToggleGroup(); // 只允许选一次
	    menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

	    //“插入功能”选项
	    Menu menuInsert = new Menu("Insert Into ? ");
	    
	    Menu menuIStrongEntity = new Menu("增加强实体集集表信息");
	    RadioMenuItem menuIStrongTableName1=new RadioMenuItem("插入->研究室");
	    RadioMenuItem menuIStrongTableName2=new RadioMenuItem("插入->办公场地");
	    RadioMenuItem menuIStrongTableName3=new RadioMenuItem("插入->秘书");
	    RadioMenuItem menuIStrongTableName4=new RadioMenuItem("插入->科研人员");
	    menuIStrongTableName1.setToggleGroup(tGroup);
	    menuIStrongTableName2.setToggleGroup(tGroup);
	    menuIStrongTableName3.setToggleGroup(tGroup);
	    menuIStrongTableName4.setToggleGroup(tGroup);
	    //响应强实体集插入功能
	    menuIStrongTableName4.setOnAction(ActionEvent->{
			try {
				insert_handler.handleIST4(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	    menuIStrongEntity.getItems().addAll(menuIStrongTableName1,new SeparatorMenuItem(),menuIStrongTableName2,new SeparatorMenuItem(),menuIStrongTableName3,
	    														  new SeparatorMenuItem(),menuIStrongTableName4);
	    
	    Menu menuIWeakEntity = new Menu("增加弱实体集集表信息");
	    RadioMenuItem menuIWeakTableName1= new RadioMenuItem("插入->研究成果");
	    RadioMenuItem menuIWeakTableName2= new RadioMenuItem("插入->有项目的科研人员");
	    RadioMenuItem menuIWeakTableName3= new RadioMenuItem("插入->子课题");
	    menuIWeakTableName1.setToggleGroup(tGroup);
	    menuIWeakTableName2.setToggleGroup(tGroup);
	    menuIWeakTableName3.setToggleGroup(tGroup);
	    menuIWeakEntity.getItems().addAll(menuIWeakTableName1,new SeparatorMenuItem(),menuIWeakTableName2,new SeparatorMenuItem(),menuIWeakTableName3);
	    Menu menuIRelationshipEntity = new Menu("增加联系集集表信息");
	    RadioMenuItem menuIRelationshipTableName1=new RadioMenuItem("插入->研究室--秘书服务");
	    RadioMenuItem menuIRelationshipTableName2=new RadioMenuItem("插入->研究室--位置");
	    RadioMenuItem menuIRelationshipTableName3=new RadioMenuItem("插入->研究室--科研人员");
	    menuIRelationshipTableName1.setToggleGroup(tGroup);
	    menuIRelationshipTableName2.setToggleGroup(tGroup);
	    menuIRelationshipTableName3.setToggleGroup(tGroup);
	    menuIRelationshipEntity.getItems().addAll(menuIRelationshipTableName1,new SeparatorMenuItem(),menuIRelationshipTableName2,new SeparatorMenuItem(),menuIRelationshipTableName3);
	    
	    menuInsert.getItems().addAll(menuIStrongEntity,new SeparatorMenuItem(), menuIWeakEntity,new SeparatorMenuItem(),menuIRelationshipEntity);  

	    //“删除功能”选项
	    Menu menuDelete = new Menu("Delete From ?");
	    
	    Menu menuDStrongEntity = new Menu("删除强实体集集表信息");
	    RadioMenuItem menuDStrongTableName1=new RadioMenuItem("删除->研究室");
	    RadioMenuItem menuDStrongTableName2=new RadioMenuItem("删除->办公场地");
	    RadioMenuItem menuDStrongTableName3=new RadioMenuItem("删除->秘书");
	    menuDStrongTableName1.setToggleGroup(tGroup);
	    menuDStrongTableName2.setToggleGroup(tGroup);
	    menuDStrongTableName3.setToggleGroup(tGroup);
	    menuDStrongEntity.getItems().addAll(menuDStrongTableName1,new SeparatorMenuItem(),menuDStrongTableName2,new SeparatorMenuItem(),menuDStrongTableName3);
	    Menu menuDWeakEntity = new Menu("删除弱实体集集表信息");
	    RadioMenuItem menuDWeakTableName1= new RadioMenuItem("删除->研究成果");
	    RadioMenuItem menuDWeakTableName2= new RadioMenuItem("删除->有项目的科研人员");
	    RadioMenuItem menuDWeakTableName3= new RadioMenuItem("删除->子课题");
	    menuDWeakTableName1.setToggleGroup(tGroup);
	    menuDWeakTableName2.setToggleGroup(tGroup);
	    menuDWeakTableName3.setToggleGroup(tGroup);
	    menuDWeakEntity.getItems().addAll(menuDWeakTableName1,new SeparatorMenuItem(),menuDWeakTableName2,new SeparatorMenuItem(),menuDWeakTableName3);
	    Menu menuDRelationshipEntity = new Menu("删除联系集表信息");
	    RadioMenuItem menuDRelationshipTableName1=new RadioMenuItem("删除->研究室--秘书服务");
	    RadioMenuItem menuDRelationshipTableName2=new RadioMenuItem("删除->研究室--位置");
	    RadioMenuItem menuDRelationshipTableName3=new RadioMenuItem("删除->研究室--科研人员");
	    menuDRelationshipTableName1.setToggleGroup(tGroup);
	    menuDRelationshipTableName2.setToggleGroup(tGroup);
	    menuDRelationshipTableName3.setToggleGroup(tGroup);
	    menuDRelationshipEntity.getItems().addAll(menuDRelationshipTableName1,new SeparatorMenuItem(),menuDRelationshipTableName2,new SeparatorMenuItem(),menuDRelationshipTableName3);
	    
	    menuDelete.getItems().addAll(menuDStrongEntity,new SeparatorMenuItem(), menuDWeakEntity,new SeparatorMenuItem(),menuDRelationshipEntity);  

	    //“修改功能”选项
	    Menu menuUpdate = new Menu("Update ?");
	    
	    Menu menuUStrongEntity = new Menu("更新强实体集集表信息");
	    RadioMenuItem menuUStrongTableName1=new RadioMenuItem("修改->研究室");
	    RadioMenuItem menuUStrongTableName2=new RadioMenuItem("修改->办公场地");
	    RadioMenuItem menuUStrongTableName3=new RadioMenuItem("修改->秘书");
	    menuUStrongTableName1.setToggleGroup(tGroup);
	    menuUStrongTableName2.setToggleGroup(tGroup);
	    menuUStrongTableName3.setToggleGroup(tGroup);
	    menuUStrongEntity.getItems().addAll(menuUStrongTableName1,new SeparatorMenuItem(),menuUStrongTableName2,new SeparatorMenuItem(),menuUStrongTableName3);
	    Menu menuUWeakEntity = new Menu("更新弱实体集表信息");
	    RadioMenuItem menuUWeakTableName1= new RadioMenuItem("修改->研究成果");
	    RadioMenuItem menuUWeakTableName2= new RadioMenuItem("修改->有项目的科研人员");
	    RadioMenuItem menuUWeakTableName3= new RadioMenuItem("修改->子课题");
	    menuUWeakTableName1.setToggleGroup(tGroup);
	    menuUWeakTableName2.setToggleGroup(tGroup);
	    menuUWeakTableName3.setToggleGroup(tGroup);
	    menuUWeakEntity.getItems().addAll(menuUWeakTableName1,new SeparatorMenuItem(),menuUWeakTableName2,new SeparatorMenuItem(),menuUWeakTableName3);
	    Menu menuURelationshipEntity = new Menu("更新联系集表信息");
	    RadioMenuItem menuURelationshipTableName1=new RadioMenuItem("修改->研究室--秘书服务");
	    RadioMenuItem menuURelationshipTableName2=new RadioMenuItem("修改->研究室--位置");
	    RadioMenuItem menuURelationshipTableName3=new RadioMenuItem("修改->研究室--科研人员");
	    menuURelationshipTableName1.setToggleGroup(tGroup);
	    menuURelationshipTableName2.setToggleGroup(tGroup);
	    menuURelationshipTableName3.setToggleGroup(tGroup);
	    menuURelationshipEntity.getItems().addAll(menuURelationshipTableName1,new SeparatorMenuItem(),menuURelationshipTableName2,new SeparatorMenuItem(),menuURelationshipTableName3);
	    
	    menuUpdate.getItems().addAll(menuUStrongEntity,new SeparatorMenuItem(), menuUWeakEntity,new SeparatorMenuItem(),menuURelationshipEntity);

	    //“查询功能”选项
	    Menu menuQuery = new Menu("Select from ?");
	    
	    Menu menuQStrongEntity=new Menu("查询强实体集表");
	    RadioMenuItem menuQStrongTableName1=new RadioMenuItem("查询->研究室");
	    RadioMenuItem menuQStrongTableName2=new RadioMenuItem("查询->办公场地	");
	    RadioMenuItem menuQStrongTableName3=new RadioMenuItem("查询->秘书");
	    menuQStrongTableName1.setToggleGroup(tGroup);
	    menuQStrongTableName2.setToggleGroup(tGroup);
	    menuQStrongTableName3.setToggleGroup(tGroup);
	    menuQStrongEntity.getItems().addAll(menuQStrongTableName1,new SeparatorMenuItem(),menuQStrongTableName2,new SeparatorMenuItem(),menuQStrongTableName3);
	    Menu menuQWeakEntity=new Menu("查询弱实体集表");
	    RadioMenuItem menuQWeakTableName1=new RadioMenuItem("查询->研究成果");
	    RadioMenuItem menuQWeakTableName2=new RadioMenuItem("查询->有项目的科研人员");
	    RadioMenuItem menuQWeakTableName3=new RadioMenuItem("查询->子课题");
	    menuQWeakTableName1.setToggleGroup(tGroup);
	    menuQWeakTableName2.setToggleGroup(tGroup);
	    menuQWeakTableName3.setToggleGroup(tGroup);
	    menuQWeakEntity.getItems().addAll(menuQWeakTableName1,new SeparatorMenuItem(),menuQWeakTableName2,new SeparatorMenuItem(),menuQWeakTableName3);
	    Menu menuQRelationshipEntity=new Menu("查询联系集表");
	    RadioMenuItem menuQRelationshipTableName1=new RadioMenuItem("查询->研究室--秘书服务");
	    RadioMenuItem menuQRelationshipTableName2=new RadioMenuItem("查询->研究室--位置");
	    RadioMenuItem menuQRelationshipTableName3=new RadioMenuItem("查询->研究室--科研人员");
	    menuQRelationshipTableName1.setToggleGroup(tGroup);
	    menuQRelationshipTableName2.setToggleGroup(tGroup);
	    menuQRelationshipTableName3.setToggleGroup(tGroup);
	    //响应联系集查询功能
	    menuQRelationshipTableName1.setOnAction(ActionEvent->{
			try {
				query_handler.handleQRT1(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	    menuQRelationshipEntity.getItems().addAll(menuQRelationshipTableName1,new SeparatorMenuItem(),menuQRelationshipTableName2,new SeparatorMenuItem(),menuQRelationshipTableName3);
	    
	    menuQuery.getItems().addAll(menuQStrongEntity,new SeparatorMenuItem(),menuQWeakEntity,new SeparatorMenuItem(),menuQRelationshipEntity);
	    
	    menuBar.getMenus().addAll(menuInsert, menuDelete, menuUpdate, menuQuery);
	    
	    //用menu的退出系统功能
	    //Menu exitMenu=new Menu("exit");
	    //RadioMenuItem exitMenuItem= new RadioMenuItem("Exit System");
	    //exitMenuItem.setOnAction(actionEvent -> Platform.exit());
	    //exitMenu.getItems().add(exitMenuItem);
	    //用button的退出系统功能
	    Button exitSystemButton=new Button("Exit System");
	    exitSystemButton.setLayoutX(1100);
	    exitSystemButton.setLayoutY(40);
	    exitSystemButton.setMinSize(80, 40);
	    exitSystemButton.setTextFill(Color.DEEPSKYBLUE);
	    exitSystemButton.setFont(Font.font("Lithos Pro", FontWeight.LIGHT, 30));
	    exitSystemButton.setOnAction(ActionEvent->{try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}Platform.exit();});
	    
	    Label labelSelect = new Label("                          按Alt后可以用方向按键选择，单击鼠标右键可直接选择");
	    labelSelect.setTextFill(Color.web("#0076a3"));
	    labelSelect.setScaleX(2);// 设置字体大小
	    labelSelect.setScaleY(2);
	    labelSelect.setLayoutX(10);
	    labelSelect.setLayoutY(35);
	    
	    Button Researchers =new Button("I am a researcher.");
	    Researchers.setTextFill(Color.CORNFLOWERBLUE);
	    Researchers.setFont(Font.font("Hobo Std", FontWeight.BOLD, 24));
	    Researchers.setLayoutX(20);
	    Researchers.setLayoutY(130);
	    Researchers.setMaxSize(255, 80);
	    Researchers.setCancelButton(true);
	    Researchers.setOnAction(ActionEvent->{
		    handleResearchers hR=new handleResearchers();
		    hR.functionSelect(connection);
		    });
	    
	    javafx.scene.Group group=new javafx.scene.Group(menuBar);
	    group.getChildren().addAll(labelSelect,exitSystemButton,Researchers);
	    Scene scene = new Scene(group, 1340, 800, Color.CHARTREUSE);
	   
	    primaryStage.setTitle("科研管理系统--差删改查");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    
	    // 鼠标右键单击弹出功能选项
	    ContextMenu  contextFileMenu = new ContextMenu(menuInsert,menuDelete,menuUpdate,menuQuery);
        primaryStage.addEventHandler(MouseEvent.MOUSE_CLICKED,  (MouseEvent  me) ->  {
            if (me.getButton() == MouseButton.SECONDARY  || me.isControlDown())  {
                contextFileMenu.show(group, me.getScreenX(), me.getScreenY());
            }  else  {
                contextFileMenu.hide(); // 单击左键后能使选项消失
            }});
	  }
	  
	  public void showUIFunction(Stage primaryStage)
	  {
		  start(primaryStage);
	  }
	  
	  public static void main(String[] args) {
	    launch(args);
	  }
   
}
