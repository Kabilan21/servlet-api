package com.webapp.services;

import com.webapp.utils.Constants;

import java.sql.Connection;
import java.sql.DriverManager;

public class ModelService {
    public static Connection connection;

    public static Connection getConnection() {

        if (connection != null)
            return connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Constants.dbName, Constants.username, Constants.password);
        } catch (Exception e) {
            System.out.println("Unable to connect to database...");
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
