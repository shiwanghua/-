package application;

import static javafx.geometry.HPos.RIGHT;

import java.nio.channels.ScatteringByteChannel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Q_Handle{

//-----------------------------------------------------------------------------------------------------
//处理强实体集查询
	
	//处理查询--强实体集表--研究室
	public static class research{  //  research 一个类代表一行-->一个研究室
		 
        private final SimpleStringProperty researchName;
        private final SimpleStringProperty researchDirectionIntroduction; 
 
        private research(String research, String secretary) {
            this.researchName = new SimpleStringProperty(research);
            this.researchDirectionIntroduction = new SimpleStringProperty(secretary); 
        }
 
        public String getResearchName()
        {
    	   return researchName.get();
        }
 
        public String getResearchDirectionIntroduction() {
            return researchDirectionIntroduction.get();
        }
 
        public void setsea(String s) {
        	researchName.set(s);
        }
        
        public void setsec(String rdi) {
        	researchDirectionIntroduction.set(rdi);
        }
	}
	void handleQST1(Connection connection) throws SQLException 
	{
		Statement stmt = connection.createStatement();
		ResultSet res = stmt.executeQuery("SELECT * FROM 研究室;");
		Statement stmt2 = connection.createStatement();
		ResultSet resRowCount=stmt2.executeQuery("SELECT count(*) FROM 研究室;");
		
		resRowCount.next();
		int rowCount=resRowCount.getInt(1);
		String [] researchName=new String[rowCount];
		String [] researchDirectionIntroduction=new String[rowCount];
		
	    TableView<research> table = new TableView<research>();
	    table.setEditable(true);
	    table.setCenterShape(true);
	    table.setMinSize(150, 100);
	    TableColumn nameRes = new TableColumn("研究室名称");
	    nameRes.setMinWidth(100);
	    nameRes.setCellValueFactory(new PropertyValueFactory<research,String>("ResearchName"));
        TableColumn noSec = new TableColumn("研究方向介绍");
        noSec.setMinWidth(220);
        noSec.setCellValueFactory(new PropertyValueFactory<research,String>("ResearchDirectionIntroduction"));
        
        //构造数据集
        int i = 0;
		while (res.next()) {
			researchName[i] = res.getString(1);
			researchDirectionIntroduction[i++] = res.getString(2);
		}
		res.close();
		resRowCount.close();
		stmt.close();
		stmt2.close();
        ObservableList<research> data = FXCollections.observableArrayList();
        for(int n=0;n<i;n++)
        {
        	data.add(new research(researchName[n], researchDirectionIntroduction[n]));
        }
        //插入数据集
        table.setItems(data);
        table.setStyle("-fx-background-color: WHEAT");
        table.getColumns().addAll(nameRes,noSec);
        
        final Label label = new Label("研究室");
        label.setFont(Font.font("华文行楷",FontWeight.SEMI_BOLD, 24));
        label.setTextFill(Color.DARKGREY);

        final VBox vbox = new VBox();
        VBox.setMargin(label, new Insets(8, 0, 3, 190));
        VBox.setMargin(table, new Insets(5, 0, 10, 70));
        vbox.setSpacing(2);
        vbox.getChildren().addAll(label, table);

        Scene scene = new Scene(new Group(),200, 100, Color.LIGHTGREEN);
        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        Stage stage=new Stage();
        stage.setTitle("查询\"研究室\"表");
        stage.setWidth(480);
        stage.setHeight(520);
        stage.setScene(scene);
        stage.show();
	}
	
//-----------------------------------------------------------------------------------------------------
//处理弱实体集查询
	
	//处理查询--弱实体集表--研究成果
	void handleQWT1(Connection connection) throws SQLException
	{
			
	}
	
//-----------------------------------------------------------------------------------------------------
//处理联系集查询
	
	//处理查询--联系集表--研究室_秘书服务
	public static class r_c{  //  research-secretary 一个类代表一行
		 
        private final SimpleStringProperty sea;
        private final SimpleStringProperty sec; 
 
        private r_c(String research, String secretary) {
            this.sea = new SimpleStringProperty(research);
            this.sec = new SimpleStringProperty(secretary); 
        }
 
        public String getSea()
        {
    	   return sea.get();
        }
 
        public String getSec() {
            return sec.get();
        }
 
        public void setsea(String s) {
            sea.set(s);
        }
        
        public void setsec(String c) {
            sec.set(c);
        }
	}
	void handleQRT1(Connection connection) throws SQLException
	{
		Statement stmt = connection.createStatement();
		ResultSet res = stmt.executeQuery("SELECT * FROM 研究室_秘书服务;");
		Statement stmt2 = connection.createStatement();
		ResultSet resRowCount=stmt2.executeQuery("SELECT count(*) FROM 研究室_秘书服务;");
		
		resRowCount.next();
		int rowCount=resRowCount.getInt(1);
		String [] research=new String[rowCount];
		String [] secretary=new String[rowCount];
		
	    TableView<r_c> table = new TableView<r_c>();
	    table.setEditable(true);
	    table.setCenterShape(true);
	    table.setMaxSize(174, 250);
	    TableColumn nameRes = new TableColumn("研究室名称");
	    nameRes.setMaxWidth(120);
	    nameRes.setCellValueFactory(new PropertyValueFactory<r_c,String>("Sea"));
        TableColumn noSec = new TableColumn("秘书工号");
        noSec.setMaxWidth(83);
        noSec.setCellValueFactory(new PropertyValueFactory<r_c,String>("Sec"));
        
        //构造数据集
        int i = 0;
		while (res.next()) {
			research[i] = res.getString(1);
			secretary[i++] = res.getString(2);
		}
		res.close();
		resRowCount.close();
		stmt.close();
		stmt2.close();
        ObservableList<r_c> data = FXCollections.observableArrayList();//= FXCollections.observableArrayList(new r_c(research, secretary));
        for(int n=0;n<rowCount;n++)
        {
        	data.add(new r_c(research[n], secretary[n]));
        }
        //插入数据集
        table.setItems(data);
        table.setStyle("-fx-background-color: BLACK");
        table.getColumns().addAll(nameRes,noSec);
        
        final Label label = new Label("一个研究室有唯一一个秘书");
        label.setFont(Font.font("行楷",FontWeight.BLACK, 16));
        label.setTextFill(Color.CORNFLOWERBLUE);

        final VBox vbox = new VBox();
        VBox.setMargin(label, new Insets(8, 0, 3, 65));
        VBox.setMargin(table, new Insets(5, 0, 10, 70));
        vbox.setSpacing(2);
        vbox.getChildren().addAll(label, table);

        Scene scene = new Scene(new Group(),1500, 800, Color.WHEAT);
        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        Stage stage=new Stage();
        stage.setTitle("查询\"研究室_秘书服务\"表");
        stage.setWidth(320);
        stage.setHeight(360);
        stage.setScene(scene);
        stage.show();
        
	}
	
	//处理查询--联系集表--研究室_位置
	void handleQRT2(Connection conection) throws SQLException
	{
		
	}
	
	//处理查询--联系集表--研究室_科研人员
	void handleQRT3(Connection conection) throws SQLException
	{
		
	}

//-----------------------------------------------------------------------------------------------------
//处理多表混合查询

	//处理个人项目查询：“项目_科研人员”表+“科研项目”表
	public static class Project{  //  research-secretary 一个类代表一行
		 
        private final SimpleStringProperty noP;
        private final SimpleStringProperty nameP; 
        private final SimpleStringProperty researchContent; 
        private final SimpleStringProperty totalFunding; 
        private final SimpleStringProperty startTime; 
        private final SimpleStringProperty deadline; 
 
        private Project(String no, String name,String rC,String tF,String sT,String ddl) {
            this.noP = new SimpleStringProperty(no);
            this.nameP = new SimpleStringProperty(name); 
            this.researchContent = new SimpleStringProperty(rC); 
            this.totalFunding = new SimpleStringProperty(tF);
            this.startTime = new SimpleStringProperty(sT);
            this.deadline = new SimpleStringProperty(ddl);
        }
 
        public String getNoP()
        {
    	   return noP.get();
        }
        public String getNameP()
        {
    	   return nameP.get();
        }
        public String getRC()
        {
    	   return researchContent.get();
        }
        public String getTF()
        {
    	   return totalFunding.get();
        }
        public String getST()
        {
    	   return startTime.get();
        }
        public String getDDL() {
            return deadline.get();
        }
	}
	void handlePersonalProject(Connection connection)
	{
		Label noT = new Label("工号：");
		noT.setFont(Font.font("华文琥珀", FontWeight.NORMAL, 17));
		TextField noTF = new TextField();

		//构建项目表
		TableView<Project> table = new TableView<Project>();
	    table.setEditable(true);
	    table.setCenterShape(true);
	    table.setMinSize(550, 220);
	    TableColumn noP = new TableColumn("项目号");
	    noP.setMinWidth(80);
	    noP.setCellValueFactory(new PropertyValueFactory<r_c,String>("NoP"));
        TableColumn nameP = new TableColumn("项目名");
        nameP.setMinWidth(130);
        nameP.setCellValueFactory(new PropertyValueFactory<r_c,String>("NameP"));
        TableColumn researchContent = new TableColumn("研究内容");
        researchContent.setMinWidth(170);
        researchContent.setCellValueFactory(new PropertyValueFactory<r_c,String>("RC"));
	    TableColumn totalFunding = new TableColumn("经费总额");
	    totalFunding.setMinWidth(60);
	    totalFunding.setCellValueFactory(new PropertyValueFactory<r_c,String>("TF"));
	    TableColumn startTime = new TableColumn("开工时间");
	    startTime.setMinWidth(100);
	    startTime.setCellValueFactory(new PropertyValueFactory<r_c,String>("ST"));
	    TableColumn deadline = new TableColumn("完成时间");
	    deadline.setMinWidth(100);
	    deadline.setCellValueFactory(new PropertyValueFactory<r_c,String>("DDL"));
	    table.setStyle("-fx-background-color: LIGHTBLUE");
        table.getColumns().addAll(noP,nameP,researchContent,totalFunding,startTime,deadline);
	    
	    Button btnQuery = new Button("查找我的项目");
	    btnQuery.setMinSize(45, 27);
	    btnQuery.setOnAction(ActionEvent->{
          try {
				Statement stmt;
				ObservableList<Project> data =  null;
                data=FXCollections.observableArrayList();
				stmt = connection.createStatement();
				String noInput=noTF.getText();
				ResultSet res = stmt.executeQuery("SELECT 项目号 FROM 科研项目_科研人员 where 科研人员工号='"+noInput+"';");
				//存储个人项目号
				 List<String> noProject=new ArrayList<String>();  
				int numOfProject=0;
				while(res.next()) {
					noProject.add(res.getString(1));
					numOfProject++;
				}
				stmt.close();
				res.close();
				for(int i=0;i<numOfProject;i++)
				{
					stmt = connection.createStatement();
					res=stmt.executeQuery("select 项目号,项目名,研究内容,经费总额,开工时间,完成时间 from 科研项目 where 项目号='"+noProject.get(i)+"';");
					res.next();
					data.add(new Project(res.getString(1), res.getString(2),  res.getString(3),  res.getString(4),  res.getString(5),  res.getString(6)));
					stmt.close();
					res.close();
				}
				table.setItems(data);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    });
		
	    HBox hBox=new HBox(10);
		hBox.setAlignment(Pos.TOP_LEFT);
		hBox.getChildren().add(btnQuery);
		
		GridPane grid = new GridPane();
		grid.setHgap(10);   
		grid.setVgap(5);
		grid.setPadding(new Insets(25, 25, 25, 38));
		grid.add(noT, 0, 0);
		grid.add(noTF,1, 0);
		grid.add(hBox,2, 0);
		grid.add(table, 0, 3,3,1);
	    
	    Group group=new Group();
	    group.getChildren().addAll(grid);
	    Scene scene=new Scene(group, 700, 580);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.setWidth(750);
	    stage.setHeight(560);
		stage.setTitle("查看个人项目");
		stage.show();
	}
}
