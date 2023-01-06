package com.example.ecommerce;


import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;

public class HeaderArea {
   public AnchorPane root;
   HeaderArea() throws IOException {
       root= FXMLLoader.load(getClass().getResource("header.fxml"));
   }
}
