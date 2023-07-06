package com.spaghetti.visprog_bz214_jdbc.first_example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;

public class FindGrade extends Application {
    private Connection connection;
    private Statement statement;
    private TextField txtID = new TextField();
    private TextField txtCourseID = new TextField();
    private Button btnFind = new Button("Find Grade");
    private Label lblStatus = new Label();
    @Override
    public void start(Stage stage) throws Exception {
        GridPane root = new GridPane();
        root.add(new Label("Student ID:"),0,0);
        root.add(txtID,1,0);
        root.add(new Label("Course ID:"),0,1);
        root.add(txtCourseID,1,1);
        root.add(btnFind,1,2);
        root.add(lblStatus,0,3,2,1);
        root.setPadding(new Insets(10,10,10,10));
        root.setHgap(10);
        root.setVgap(10);

        initializeDB();
        btnFind.setOnAction(actionEvent -> showGrade());

        stage.setScene(new Scene(root, 400,300));
        stage.setTitle("Find Grade");
        stage.show();
    }


    public void initializeDB(){
        try {
            connection = DriverManager.
                    getConnection("jdbc:postgresql://localhost:5432/vispro","postgres","1234");
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void showGrade(){
        String query = "SELECT firstName, lastName, title, grade FROM student "+
                "INNER JOIN enrollment ON student.studentId = enrollment.studentId "+
                "INNER JOIN course ON enrollment.courseId = course.courseId "+
                "WHERE student.studentId = '" + txtID.getText() +"' AND enrollment.courseId = '"
                + txtCourseID.getText() +"'";
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next())
                lblStatus.setText(resultSet.getString(1) + " " + resultSet.getString(2) + " "
                        + resultSet.getString(3) + " " + resultSet.getString(4));
        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }
}
