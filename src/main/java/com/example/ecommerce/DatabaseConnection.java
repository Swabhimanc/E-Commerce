package com.example.ecommerce;
import java.sql.*;

public class DatabaseConnection    {
    Connection con=null;
    String SQLURL="jdbc:mysql://localhost:3306/ecommerce?useSSL=false";
    String userName="root";
    String password="123456789";

    DatabaseConnection() throws SQLException {
        con= DriverManager.getConnection(SQLURL,userName,password);
        if(con!=null)
        {
            System.out.println("Connection Established with the Database");
        }
    }
    public ResultSet executeQuery(String query) throws SQLException
    {
        ResultSet result = null;
        try {
            Statement statement = con.createStatement();
            result = statement.executeQuery(query);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }
    public int executeUpdate(String query)
    {
        int row = 0;
        try
        {
            Statement statement = con.createStatement();

            row = statement.executeUpdate(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return row;
    }
}
