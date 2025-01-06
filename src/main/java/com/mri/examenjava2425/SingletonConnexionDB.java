package com.mri.examenjava2425;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexionDB {
    static final private String url = "jdbc:mysql://localhost:3306/ExamenJava2425";
    static final private String user = "root";
    static final private String password = "yahya";

    public static Connection getConnection() throws SQLException {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection with DB established");
            return con;
        } catch (SQLException e) {
            System.out.println("Problem with DB");
            throw new SQLException(e);
        }
    }
}

