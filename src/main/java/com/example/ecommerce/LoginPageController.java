package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageController {
    @FXML
    TextField email;

    @FXML
    PasswordField password;

    @FXML
    public void login(MouseEvent e) throws SQLException, IOException
    {
        String query = String.format("Select * from user where emailId='%s' and pass='%s'",email.getText(),password.getText());
        ResultSet res=HelloApplication.connection.executeQuery(query);

        if(res.next())
        {
            HelloApplication.emailId=res.getString("emailId");
            String userType = res.getString("userType");
            if(userType.equals("Seller"))
            {
                AnchorPane sellerpage= FXMLLoader.load(getClass().getResource("SellerPage.fxml"));
                HelloApplication.root.getChildren().add(sellerpage);
            }
            else
            {
                ProductPage productPage=new ProductPage();
                HeaderArea header=new HeaderArea();

                AnchorPane productPane=new AnchorPane();
                productPane.getChildren().add(productPage.products());
                productPane.setLayoutX(125);
                productPane.setLayoutY(70);
                HelloApplication.root.getChildren().clear();
                HelloApplication.root.getChildren().addAll(header.root,productPane);

                System.out.println("We are logged in as a buyer");
            }
            System.out.println("The user is present in USER Table");
        }
        else
        {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Login");
            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("Login Failed! Please check Username or Password and try again");
            dialog.showAndWait();
        }
    }
}
