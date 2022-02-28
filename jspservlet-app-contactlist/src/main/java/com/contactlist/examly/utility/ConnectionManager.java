package com.contactlist.examly.utility;

import java.sql.*;

public class ConnectionManager{

    private String jdbcURL = "jdbc:mysql://localhost/db1";
    private String jdbcUsername = "root";
    private String jdbcPassword = "examly";

    public ConnectionManager(){

    }

    public ConnectionManager(String jdbcURL, String jdbcUsername, String jdbcPassword){
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    public Connection getConnection(){
        Connection connection=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection(this.jdbcURL, 
                this.jdbcUsername, this.jdbcPassword);
        }catch(SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }
}