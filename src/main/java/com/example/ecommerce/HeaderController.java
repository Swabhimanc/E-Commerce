package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class HeaderController {

    @FXML
    public void initialize()
    {
        if(!HelloApplication.emailId.equals(""))
        {
            loginbutton.setOpacity(0);
            loginbutton.setLayoutX(600);
            email.setText(HelloApplication.emailId);
        }
    }

    @FXML
    Label email;
    @FXML
    Button loginbutton;

    @FXML
    public void login(MouseEvent e) throws IOException {


            AnchorPane loginpage= FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            HelloApplication.root.getChildren().add(loginpage);

    }

    @FXML
    TextField searchtext;
    @FXML
    public void search(MouseEvent e) throws IOException, SQLException {
        ProductPage productPage=new ProductPage();
        HeaderArea header=new HeaderArea();

        AnchorPane productPane=new AnchorPane();
        productPane.getChildren().add(productPage.productBySearch(searchtext.getText()));
        productPane.setLayoutX(125);
        productPane.setLayoutY(70);
        HelloApplication.root.getChildren().clear();
        HelloApplication.root.getChildren().addAll(header.root,productPane);
    }

    @FXML
    Button logoutbutton;
    public void logoutAppear(MouseEvent e)
    {
        if(logoutbutton.getOpacity()==0)
        {
            logoutbutton.setOpacity(1);
        }
        else {
            logoutbutton.setOpacity(0);
        }
    }

    @FXML
    public void logout(MouseEvent e) throws IOException {
        if(logoutbutton.getOpacity()==1)
        {
            HelloApplication.emailId="";
            logoutbutton.setOpacity(0);
            HeaderArea head = new HeaderArea();
            HelloApplication.root.getChildren().add(head.root);
        }
    }
}
