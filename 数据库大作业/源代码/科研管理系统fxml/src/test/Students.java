package test;


import java.io.*;

import java.util.Scanner;

import java.util.logging.Level;

import java.util.logging.Logger;

import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.control.TextField;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;

import javafx.scene.layout.GridPane;

import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;

import javafx.scene.shape.Rectangle;

import javafx.scene.text.Font;

import javafx.scene.text.Text;

import javafx.stage.Stage;

 

public class Students extends Application{

    static int i = 0;

    private TextField setName = new TextField();

    private TextField setNumber = new TextField();

    private TextField setClass = new TextField();

    private TextField setGrade = new TextField();

    Stage stage = new Stage();

    

    @Override

    public void start(Stage primaryStage) throws Exception {//声明异常

        GridPane pane = new GridPane();

        

        pane.setHgap(20);

        pane.setVgap(10);

        

        Button[] bt = new Button[7];

        bt[1] = new Button("清空学生数据库");

        bt[2] = new Button("增添学生数据");

        bt[3] = new Button("删除学生数据");

        bt[4] = new Button("修改学生数据");

        bt[5] = new Button("查看学生数据");

        bt[6] = new Button("显示所有学生数据");

        bt[0] = new Button("退出应用");

        

        pane.add(new Label("\t\t"), 2, 0);

        pane.add(new Label("\t\t"), 0, 0);

        pane.add(bt[1], 1, 1);

        pane.add(bt[2], 1, 2);

        pane.add(bt[3], 1, 3);

        pane.add(bt[4], 1, 4);

        pane.add(bt[5], 1, 5);

        pane.add(bt[6], 1, 6);

        pane.add(bt[0], 1, 7);

        pane.add(new Label("\t\t"), 1, 9);

        

        Pane pane2 = new Pane();

        Rectangle rectangle;

        for(int j = 0; j < 20; j ++)

            for(int c = 0; c < 20; c ++){

                rectangle = new Rectangle(j * 20, c * 20, 20, 20);

                rectangle.setFill(Color.color(Math.random(), Math.random(), Math.random(), 0.3));

                pane2.getChildren().addAll(rectangle);

        }//作为背景存在

        pane2.getChildren().addAll(pane);//将pane面板覆盖在pane2上

        Scene scene = new Scene(pane2, 290, 350);//定义面板大小

        stage.setTitle("Students");

        stage.setScene(scene);

        stage.show();

        

        bt[0].setOnAction(e -> EXIT());

        bt[1].setOnAction(e -> closeStudents());

        bt[2].setOnAction(e -> addStudents());

        bt[3].setOnAction(e -> deleteStudents());

        bt[4].setOnAction(e -> amendStudents());

        bt[5].setOnAction(e -> checkStundets());

        bt[6].setOnAction(e -> {

            try {

                checkallStudents();

            } 

            catch (FileNotFoundException ex) {//捕获异常

                Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);

            }

        });

    }

    

    public static void main(String[] args){

        Application.launch(args);

    }

 

    private void EXIT() {//关闭界面，结束程序

        stage.close();

    }

 

    private void closeStudents() {//清空数据

        

        Pane pane = new Pane();

        Text text = new Text(200, 200, "你确定要删除所有数据！");

        text.setFill(Color.RED);

        text.setFont(Font.font(20));//定义字体大小

        

        Button bt1 = new Button("Ok");

        Button bt2 = new Button("no");

        bt1.setLayoutX(200);

        bt1.setLayoutY(230);

        bt2.setLayoutX(350);

        bt2.setLayoutY(230);

        

        Image image = new Image("http://i-7.vcimg.com/trim/0c0a306c6947c8c125c52a25850a22181611368/trim.jpg");//获取图片

        ImageView imageview = new ImageView(image);

        imageview.setFitHeight(448);

        imageview.setFitWidth(600);//更改图片大小

        pane.getChildren().add(imageview);

        

        pane.getChildren().addAll(text, bt1, bt2);

        Scene scene = new Scene(pane);

        stage.setScene(scene);

        

        bt1.setOnAction(e -> {

            try {

                YES();

            } 

            catch (FileNotFoundException ex) {

                Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);

            }

        });

        

        bt2.setOnAction(e -> {

            try {

                start(stage);//返回主界面

            } 

            catch (Exception ex) {

                Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);

            }

        });

    }

    

    private void YES() throws FileNotFoundException{//确定清除数据

        File fl = new File("Students.txt");

        try (PrintWriter output = new PrintWriter(fl)) {

        }//向文件中写入新的学生信息

        

        File Fl = new File("Number.txt");

        try(PrintWriter output = new PrintWriter(Fl)){

            output.print(0);//定义学生数量为0

        }

        

        Text text = new Text(100, 100, "程序已将所有数据删除！\n你现在有以下两种选择:");

        Button bt1 = new Button("添加学生");

        Button bt2 = new Button("退出应用");

        bt1.setLayoutX(130);

        bt1.setLayoutY(130);

        bt2.setLayoutX(130);

        bt2.setLayoutY(180);

        

        Pane pane = new Pane();

        pane.getChildren().addAll(text, bt1, bt2);

        Scene scene = new Scene(pane,350, 300);

        

        stage.setScene(scene);

        

        bt1.setOnAction(e -> addStudents());

        bt2.setOnAction(e -> EXIT());

    }

    

    private void addStudents() {//添加学生1

 

        Button bt = new Button("OK");

        GridPane pane = new GridPane();

        pane.setHgap(20);

        pane.setVgap(10);

        

        pane.add(new Label("new student's name："), 0, 1);

        pane.add(setName, 1, 1);

        pane.add(new Label("new student's class："), 0, 2);

        pane.add(setClass, 1, 2);

        pane.add(new Label("new student's number："), 0, 3);

        pane.add(setNumber, 1, 3);

        pane.add(new Label("new student's grade："), 0, 4);

        pane.add(setGrade, 1, 4);

        pane.add(bt, 1, 5);

        

        Scene scene = new Scene(pane);

        stage.setTitle("new Staudents");

        stage.setScene(scene);

        

        bt.setOnAction(e -> {

            try {

                addstudents();

            } 

            catch (Exception ex) {

                Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);

            }

        });

    }

 

    private void addstudents() throws Exception {//添加学生2

        

        Student stu = new Student(setName.getText(), setClass.getText(), 

                 setNumber.getText(), Integer.parseInt(setGrade.getText()));//使用自定义的类暂时储存数据

       

       File Fl = new File("Number.txt");

       try (Scanner input = new Scanner(Fl)) {

            i = input.nextInt();

        }

       catch(Exception e){

           i = 0;

       }

       

        try (FileWriter out = new FileWriter("Students.txt",true)) {

            out.write("\t");

            out.write(stu.GetName() + "\t");

            out.write(stu.GetClass() + "\t");

            out.write(stu.GetNumber() + "\t");

            out.write(stu.GetScore() + "\n");

        }

        

        i++;

        try(PrintWriter output = new PrintWriter(Fl)){

                output.print(i);

            }

         

        start(stage);

    }

 

    private void deleteStudents() {//删除学生1

        Text text = new Text(100, 100, "请输入你要操作学员的学号:");

        setNumber.setLayoutX(100);

        setNumber.setLayoutY(120);

        Button bt = new Button("ok");

        bt.setLayoutX(170);

        bt.setLayoutY(165);

        Pane pane = new Pane();

        pane.getChildren().addAll(text, setNumber, bt);

        

        Scene scene = new Scene(pane, 400, 280);

        stage.setScene(scene);

        

        bt.setOnAction(e -> {

            try {

                deletestudents();

            } 

            catch (FileNotFoundException ex) {

                Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);

            }

        });

    }

    

    private void deletestudents() throws FileNotFoundException{//删除学生2

        File Fl = new File("Number.txt");

        File fl = new File("Students.txt");

        try (Scanner input = new Scanner(Fl)) {

            i = input.nextInt();

        }

        

        String Number = setNumber.getText();

        

        Student[] stu = new Student[i];

        int j;

        try(Scanner Input = new Scanner(fl)){

            for(j =0; j < i;j++){

                stu[j] = new Student(Input.next(), Input.next(), 

                        Input.next(), Input.nextInt());

                if(Number.compareTo(stu[j].GetNumber()) == 0){

                    Text text1 = new Text(100, 50, "name:\t" + stu[j].GetName());

                    Text text2 = new Text(100, 80, "class:\t" + stu[j].GetClass());

                    Text text3 = new Text(100, 120, "Number:\t" + stu[j].GetNumber());

                    Text text4 = new Text(100, 150, "grade:\t" + stu[j].GetScore());

                    

                    Button bt1 = new Button("delete");

                    Button bt2 = new Button("out");

                    bt1.setLayoutX(100);

                    bt1.setLayoutY(180);

                    bt2.setLayoutX(200);

                    bt2.setLayoutY(180);

                    Pane pane = new Pane();

                    pane.getChildren().addAll(text1, text2, text3, text4, bt1, bt2);

                    Scene scene = new Scene(pane, 350, 280);

                    stage.setScene(scene);

                    

                    bt1.setOnAction(e -> {

                        try {

                            detele();

                        } 

                        catch (FileNotFoundException ex) {

                            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);

                        }

                        catch (Exception ex) {

                            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);

                        }

                    });

                    bt2.setOnAction(e -> {

                        try {

                            start(stage);

                        } 

                        catch (Exception ex) {

                            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);

                        }

                    });

                    break;

                }

            }

            

            if(j == i ){

                Text text = new Text(100, 100, "No fond this person!");

                text.setFill(Color.RED);

                Button bt1 = new Button("again");

                Button bt2 = new Button("out");

                bt1.setLayoutX(100);

                bt1.setLayoutY(130);

                bt2.setLayoutX(200);

                bt2.setLayoutY(130);

                Pane pane = new Pane();

                pane.getChildren().addAll(text, bt1, bt2);

                

                Scene scene = new Scene(pane, 350, 250);

                stage.setScene(scene);

                

                bt1.setOnAction(e -> deleteStudents());

                bt2.setOnAction(e -> {

                    try {

                        start(stage);

                    } 

                    catch (Exception ex) {

                        Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);

                    }

                });

            }

        }

    }

 

    private void detele() throws FileNotFoundException, Exception {//删除学生3

        File Fl = new File("Number.txt");

        File fl = new File("Students.txt");

        try (Scanner input = new Scanner(Fl)) {

            i = input.nextInt();

        }

        

        String Number = setNumber.getText();

        

        Student[] stu = new Student[i];

        try(Scanner Input = new Scanner(fl)){

            for(int j =0; j < i;j++){

                stu[j] = new Student(Input.next(), Input.next(), 

                        Input.next(), Input.nextInt());

                if(Number.compareTo(stu[j].GetNumber()) == 0){//确定删除则用下一组数据，将要删除的数据覆盖

                    i --;

                    j --;

                }

            }

        }

        

        try(PrintWriter output = new PrintWriter(Fl)){

            output.print(i);

        }

        

        if(i == 0){//如果删除后的学生数量为0，则不输入如何学生数据，使文件的内容为空

            try (PrintWriter output = new PrintWriter(fl)) {

            }

        }

        else{

            try (PrintWriter output = new PrintWriter(fl)) {

                for(int j = 0;j < i;j ++){

                    output.print(stu[j].GetName() + "\t");

                    output.print(stu[j].GetNumber() + "\t");

                    output.print(stu[j].GetClass() + "\t");

                    output.println(stu[j].GetScore());

                }

            }

        }

        start(stage);

    }

 

    private void amendStudents() {//修改学生1

        Pane pane = new Pane(); 

        Text text = new Text(100, 100.0, "请输入你要操作学员的学号:");

        setNumber.setLayoutX(100);

        setNumber.setLayoutY(120);

        

        

        

        Rectangle rectangle;

        for(int j = 0; j < 20; j ++)

            for(int c = 0; c < 20; c ++){

                rectangle = new Rectangle(j * 20, c * 20, 20, 20);

                rectangle.setFill(Color.color(Math.random(), Math.random(), Math.random(), 0.3));

                pane.getChildren().addAll(rectangle);

        }

        Button bt = new Button("ok");

        bt.setLayoutX(170);

        bt.setLayoutY(165);

        pane.getChildren().addAll(text, setNumber, bt);//画面重合时，添加的先后顺序决定了那个类型覆盖在另一个类型上

        

        

        

        Scene scene = new Scene(pane, 400, 280);

        stage.setScene(scene);

        

        bt.setOnAction(e -> {

            try {

                amendstudents();

            } 

            catch (FileNotFoundException ex) {

                Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);

            }

        });

    }

 

    private void amendstudents() throws FileNotFoundException {//修改学生2

        File Fl = new File("Number.txt");

        File fl = new File("Students.txt");

        try (Scanner input = new Scanner(Fl)) {

            i = input.nextInt();

        }

        

        String Number = setNumber.getText();

        

        Student[] stu = new Student[i];

        int j;

        try(Scanner Input = new Scanner(fl)){

            for(j =0; j < i;j++){

                stu[j] = new Student(Input.next(), Input.next(), 

                        Input.next(), Input.nextInt());

                if(Number.compareTo(stu[j].GetNumber()) == 0){

                    Text text1 = new Text(100, 50, "name:\t" + stu[j].GetName());

                    Text text2 = new Text(100, 80, "class:\t" + stu[j].GetClass());

                    Text text3 = new Text(100, 120, "Number:\t" + stu[j].GetNumber());

                    Text text4 = new Text(100, 150, "grade:\t" + stu[j].GetScore());

                    

                    Button bt1 = new Button("amend");

                    Button bt2 = new Button("out");

                    bt1.setLayoutX(100);

                    bt1.setLayoutY(180);

                    bt2.setLayoutX(200);

                    bt2.setLayoutY(180);

                    Pane pane = new Pane();

                    pane.getChildren().addAll(text1, text2, text3, text4, bt1, bt2);

                    Scene scene = new Scene(pane, 350, 280);

                    stage.setScene(scene);

                    

                    bt1.setOnAction(e -> amend());

                    bt2.setOnAction(e -> {

                        try {

                            start(stage);

                        } 

                        catch (Exception ex) {

                            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);

                        }

                    });

                    break;

                }

            }

            

            if(j == i ){

                Text text = new Text(100, 100, "No fond this person!");

                text.setFill(Color.RED);

                Button bt1 = new Button("again");

                Button bt2 = new Button("out");

                bt1.setLayoutX(100);

                bt1.setLayoutY(130);

                bt2.setLayoutX(200);

                bt2.setLayoutY(130);

                Pane pane = new Pane();

                pane.getChildren().addAll(text, bt1, bt2);

                

                Scene scene = new Scene(pane, 350, 250);

                stage.setScene(scene);

                

                bt1.setOnAction(e -> amendStudents());

                bt2.setOnAction(e -> {

                    try {

                        start(stage);

                    } 

                    catch (Exception ex) {

                        Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);

                    }

                });

            }

        }

    }

 

    private void amend() {//修改学生3

        TextField newName = new TextField();

        TextField newClass = new TextField();

        TextField newNumber = new TextField();

        TextField newGrade = new TextField();

        

        Button bt = new Button("OK");

        GridPane pane = new GridPane();

        pane.setHgap(20);

        pane.setVgap(10);

        

        pane.add(new Label("new name："), 0, 1);

        pane.add(newName, 1, 1);

        pane.add(new Label("new class："), 0, 2);

        pane.add(newClass, 1, 2);

        pane.add(new Label("new number："), 0, 3);

        pane.add(newNumber, 1, 3);

        pane.add(new Label("new grade："), 0, 4);

        pane.add(newGrade, 1, 4);

        pane.add(bt, 1, 5);

        

        Scene scene = new Scene(pane);

        stage.setScene(scene);

 

        bt.setOnAction(e -> {

            try {

                Amend(newName, newClass,

                        newNumber, newGrade);

            } 

            catch (FileNotFoundException ex) {

                Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);

            } catch (Exception ex) {

                Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);

            }

        });

    }

 

    private void Amend(TextField newName, TextField newClass, TextField newNumber,

            TextField newGrade) throws FileNotFoundException, Exception {//修改学生4

        File Fl = new File("Number.txt");

        File fl = new File("Students.txt");

        try (Scanner input = new Scanner(Fl)) {

            i = input.nextInt();

        }

        

        String Number = setNumber.getText();

        

        Student[] stu = new Student[i];

        try(Scanner Input = new Scanner(fl)){

            for(int j =0; j < i;j++){

                stu[j] = new Student(Input.next(), Input.next(), 

                        Input.next(), Input.nextInt());

                if(Number.compareTo(stu[j].GetNumber()) == 0){

                    stu[j] = new Student(newName.getText(), newClass.getText(), 

                 newNumber.getText(), Integer.parseInt(newGrade.getText()));

                }

            }

        }

        

        try(PrintWriter output = new PrintWriter(Fl)){

            output.print(i);

        }

        

        if(i == 0){

            try (PrintWriter output = new PrintWriter(fl)) {

            }

        }

        else{

            try (PrintWriter output = new PrintWriter(fl)) {

                for(int j = 0;j < i;j ++){

                    output.print(stu[j].GetName() + "\t");

                    output.print(stu[j].GetNumber() + "\t");

                    output.print(stu[j].GetClass() + "\t");

                    output.println(stu[j].GetScore());

                }

            }

        }

        start(stage);

    }

 

    private void checkStundets() {//查看学生1

        Text text = new Text(100, 100, "请输入你要操作学员的学号:");

        setNumber.setLayoutX(100);

        setNumber.setLayoutY(120);

        Button bt = new Button("ok");

        bt.setLayoutX(170);

        bt.setLayoutY(165);

        Pane pane = new Pane();

        pane.getChildren().addAll(text, setNumber, bt);

        

        Scene scene = new Scene(pane, 400, 280);

        stage.setScene(scene);

        

        bt.setOnAction(e -> {

            try {

                checktudents();

            } 

            catch (FileNotFoundException ex) {

                Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);

            }

        });

    }

 

    private void checktudents() throws FileNotFoundException {//查看学生2

        File Fl = new File("Number.txt");

        File fl = new File("Students.txt");

        try (Scanner input = new Scanner(Fl)) {

            i = input.nextInt();

        }

        

        String Number = setNumber.getText();

        

        Student[] stu = new Student[i];

        int j;

        try(Scanner Input = new Scanner(fl)){

            for(j =0; j < i;j++){

                stu[j] = new Student(Input.next(), Input.next(), 

                        Input.next(), Input.nextInt());

                if(Number.compareTo(stu[j].GetNumber()) == 0){

                    Text text1 = new Text(100, 50, "name:\t" + stu[j].GetName());

                    Text text2 = new Text(100, 80, "class:\t" + stu[j].GetClass());

                    Text text3 = new Text(100, 120, "Number:\t" + stu[j].GetNumber());

                    Text text4 = new Text(100, 150, "grade:\t" + stu[j].GetScore());

                    

                    Button bt2 = new Button("out");

                    bt2.setLayoutX(100);

                    bt2.setLayoutY(180);

                    Pane pane = new Pane();

                    pane.getChildren().addAll(text1, text2, text3, text4, bt2);

                    Scene scene = new Scene(pane, 280, 250);

                    stage.setScene(scene);

                    

                    bt2.setOnAction(e -> {

                        try {

                            start(stage);

                        } 

                        catch (Exception ex) {

                            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);

                        }

                    });

                    break;

                }

            }

            

            if(j == i ){

                Text text = new Text(100, 100, "No fond this person!");

                text.setFill(Color.RED);

                Button bt1 = new Button("again");

                Button bt2 = new Button("out");

                bt1.setLayoutX(100);

                bt1.setLayoutY(130);

                bt2.setLayoutX(200);

                bt2.setLayoutY(130);

                Pane pane = new Pane();

                pane.getChildren().addAll(text, bt1, bt2);

                

                Scene scene = new Scene(pane, 350, 250);

                stage.setScene(scene);

                

                bt1.setOnAction(e -> amendStudents());

                bt2.setOnAction(e -> {

                    try {

                        start(stage);

                    } 

                    catch (Exception ex) {

                        Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);

                    }

                });

            }

        }

    }

 

    private void checkallStudents() throws FileNotFoundException {//显示所有学生数据

        File Fl = new File("Number.txt");

        File fl = new File("Students.txt");

        try (Scanner input = new Scanner(Fl)) {

            i = input.nextInt();

        }

        

        GridPane pane = new GridPane();

        pane.setHgap(20);

        pane.setVgap(10);

        

        pane.add(new Label("次序") , 0, 0);

        pane.add(new Label("姓名"), 1, 0);

        pane.add(new Label("班级"), 2, 0);

        pane.add(new Label("学号"), 3, 0);

        pane.add(new Label("成绩"), 4, 0);

        Student[] stu = new Student[i];

        Text[][] text = new Text[i][4];

        int j;

        try(Scanner Input = new Scanner(fl)){

            for(j =0; j < i;j++){

                stu[j] = new Student(Input.next(), Input.next(), 

                        Input.next(), Input.nextInt());

                text[j][0] = new Text(stu[j].GetName());

                text[j][1] = new Text(stu[j].GetClass());

                text[j][2] = new Text(stu[j].GetNumber());

                text[j][3] = new Text("" + stu[j].GetScore());

                pane.add(new Label("" + j + 1), 0, j + 1);

                pane.add(text[j][0], 1, j + 1);

                pane.add(text[j][1], 2, j + 1);

                pane.add(text[j][2], 3, j + 1);

                pane.add(text[j][3], 4, j + 1);

            }

        }

        Button bt = new Button("out");

        pane.add(bt, 2, j + 1);

        

        bt.setOnAction(e -> {

            try {

                start(stage);

            } 

            catch (Exception ex) {

                Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);

            }

        });

        Scene scene = new Scene(pane);

        stage.setScene(scene);

    }

}
