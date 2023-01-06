package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductPage
{
    ListView<HBox>products;

    ListView<HBox> products() throws SQLException {
        products=new ListView<>();
        ObservableList<HBox>productList = FXCollections.observableArrayList();
        ResultSet res=HelloApplication.connection.executeQuery("Select * from Product;");
        while(res.next())
        {
            Label name=new Label();
            name.setMinWidth(50);


            Label productID=new Label();
            productID.setMinWidth(50);


            Label price=new Label();
            price.setMinWidth(50);


            Button buy=new Button();
            buy.setText("Buy");
            HBox productDetails=new HBox();
            buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(HelloApplication.emailId.equals(""))
                    {
                        Dialog<String> dialog = new Dialog<>();
                        dialog.setTitle("Login");
                        ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.setContentText("Please Login First!");
                        dialog.showAndWait();
                    }
                    else
                    {
                        System.out.printf("You're logged in as %s\n",HelloApplication.emailId);

                        Orders orders=new Orders();
                        try {
                            orders.placeOrder(productID.getText());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Buy Button is getting Clicked");
                }
            });

            name.setText(res.getString("productName"));
            price.setText(res.getString("price"));
            productID.setText(res.getString("productID"));
            productDetails.getChildren().addAll(productID,name,price,buy);
            productList.add(productDetails);
        }
        products.setItems(productList);
        return products;
    }

    ListView<HBox> productBySearch(String search) throws SQLException
    {
        products=new ListView<>();
        ObservableList<HBox>productList = FXCollections.observableArrayList();
        ResultSet res=HelloApplication.connection.executeQuery("Select * from Product;");
        while(res.next())
        {
            if(res.getString("productName").toLowerCase().contains(search.toLowerCase()))
            {
                Label name = new Label();
                name.setMinWidth(50);


                Label productID = new Label();
                productID.setMinWidth(50);


                Label price = new Label();
                price.setMinWidth(50);


                Button buy = new Button();
                buy.setText("Buy");
                HBox productDetails = new HBox();
                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent)
                    {
                        if (HelloApplication.emailId.equals(""))
                        {
                            Dialog<String> dialog = new Dialog<>();
                            dialog.setTitle("Login");
                            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.setContentText("Please Login First!");
                            dialog.showAndWait();
                        }
                        else
                        {
                            System.out.printf("You're logged in as %s\n", HelloApplication.emailId);

                            Orders orders = new Orders();
                            try {
                                orders.placeOrder(productID.getText());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("Buy Button is getting Clicked");
                    }
                });

                name.setText(res.getString("productName"));
                price.setText(res.getString("price"));
                productID.setText(res.getString("productID"));
                productDetails.getChildren().addAll(productID, name, price, buy);
                productList.add(productDetails);
            }
        }
        products.setItems(productList);
        return products;
    }
}
