package com.example.dictionary;

import java.sql.*;

public class DatabaseConnection {

    private static final String databaseurl ="jdbc:mysql://localhost:3006/dictionary";
    private static final String userName="root";
    private static final String password="Nidhi@123";
    Statement statement = null;
    DatabaseConnection()
    {
        Connection conn;
        try{
            conn = DriverManager.getConnection(databaseurl,userName, password);
            statement = conn.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet getQueryTable(String query)
    {
        //Statement statement = getStatement();//getstatement return statement above function.
        try{
            return statement.executeQuery(query);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int addMeaningToTable(String query) {
       try {
            return statement.executeUpdate(query);
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return 0;
    }

}
