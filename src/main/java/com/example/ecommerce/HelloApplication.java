package com.example.ecommerce;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    public static DatabaseConnection connection;

    public static Group root;
    public static String emailId;
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        emailId="";
        connection=new DatabaseConnection();
        root=new Group();
        stage.setTitle("Hello!");
        HeaderArea header=new HeaderArea();

        ProductPage productPage=new ProductPage();
        AnchorPane productPane=new AnchorPane();
        productPane.getChildren().add(productPage.products());
        productPane.setLayoutX(125);
        productPane.setLayoutY(70);
        root.getChildren().addAll(header.root,productPane);

        stage.setScene(new Scene(root, 500, 500));
        stage.show();

        stage.setOnCloseRequest(e ->
        {
            try {
                connection.con.close();
                System.out.println("Connection is closed Successfully");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}