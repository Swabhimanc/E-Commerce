package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.w3c.dom.Text;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerPageController {

    @FXML
    TextField name,price,sellerid;

    @FXML
    public void AddProduct(MouseEvent event) throws SQLException {
        int productID=1;
        ResultSet response2=HelloApplication.connection.executeQuery("Select max(productID) as productID from product;" );
        if(response2.next())
        {
            productID=response2.getInt("productID")+1;
        }
        String query = String.format("Insert into product values(%s,'%s',%s,'%s')",productID,name.getText(),price.getText(),sellerid.getText());
        int response=HelloApplication.connection.executeUpdate(query);
        if(response>0)
        {
            System.out.println("New Product Added");
        }

    }
}
