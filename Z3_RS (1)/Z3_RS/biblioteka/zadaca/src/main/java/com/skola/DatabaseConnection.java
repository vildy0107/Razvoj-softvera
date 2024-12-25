package com.skola;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection
{
    private static final String korisnik = "root";
    //private static final String sifra = "hamza_7890";
    private static final String sifra = "abcd1234";
    private static final String kon = "jdbc:mysql://localhost:3306/biblioteka";


    public static Connection getConnection()
    {
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection(kon, korisnik, sifra);
        }
        catch (SQLException e)
        {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
        return connection;
    }
}
