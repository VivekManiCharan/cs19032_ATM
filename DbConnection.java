package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    //   This method is to establish the connection to the database

    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:test.db");

        } catch (ClassNotFoundException | SQLException e) {

            System.out.println(e + "");
        }
        return conn;
    }
}
