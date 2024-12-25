package com.skola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class BookOperations
{

    private static int generateNextId()
    {
        String sql = "SELECT MAX(idknjiga) FROM knjiga";
        int nextId = 1;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery())
        {

            if (resultSet.next())
            {
                int maxId = resultSet.getInt(1);
                nextId = maxId + 1;
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error generating next ID: " + e.getMessage());
        }

        return nextId;
    }


    public static void addBook(String naziv, String autor, int godinaIzdanja, String status)
    {
        if (naziv == null || naziv.trim().isEmpty())
        {
            System.err.println("Naziv knjige ne smije biti prazan");
            return;
        }
        if (autor == null || autor.trim().isEmpty())
        {
            System.err.println("Autor knjige ne smije biti prazan");
            return;
        }
        if (godinaIzdanja <= 0)
        {
            System.err.println("Godina izdavanja mora biti pozitivan broj");
            return;
        }

        BookStatus bookStatus;
        try
        {
            bookStatus = BookStatus.fromString(status);
        }
        catch (IllegalArgumentException e)
        {
            System.err.println(e.getMessage());
            return;
        }

        String sql = "INSERT INTO knjiga (idknjiga, naziv, autor, godinaIzdanja, status, datumIzdavanja) VALUES (?, ?, ?, ?, ?, ?)";
        int nextId = generateNextId();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {

            preparedStatement.setInt(1, nextId);
            preparedStatement.setString(2, naziv);
            preparedStatement.setString(3, autor);
            preparedStatement.setInt(4, godinaIzdanja);
            preparedStatement.setString(5, bookStatus.getStatus());
            preparedStatement.setDate(6, java.sql.Date.valueOf(LocalDate.now()));

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0)
            {
                System.out.println("New book added!");
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error adding book: " + e.getMessage());
        }
    }


    public static void updateBook(int id, String naziv, String autor, int godinaIzdanja, String status)
    {
        String sql = "UPDATE knjiga SET naziv = ?, autor = ?, godinaIzdanja = ?, status = ? WHERE idknjiga = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {

            preparedStatement.setString(1, naziv);
            preparedStatement.setString(2, autor);
            preparedStatement.setInt(3, godinaIzdanja);
            preparedStatement.setString(4, status);
            preparedStatement.setInt(5, id);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0)
            {
                System.out.println("Book updated successfully");
            }
            else
            {
                System.out.println("Book wasnt found");
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error updating book: " + e.getMessage());
        }
    }

    public static void deleteBook(int id)
    {
        String sql = "DELETE FROM knjiga WHERE idknjiga = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {

            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0)
            {
                System.out.println("Book deleted successfully!");
            }
            else
            {
                System.out.println("book wasnt found");
            }
        }
        catch (SQLException e)
        {
            System.err.println("error deleting book: " + e.getMessage());
        }
    }


    public static void searchBooks(String naziv, String autor, Integer godinaIzdanja, String status)
    {
        StringBuilder sql = new StringBuilder("SELECT * FROM knjiga WHERE 1=1");

        if (naziv != null && !naziv.isEmpty())
        {
            sql.append(" AND naziv LIKE ?");
        }
        if (autor != null && !autor.isEmpty())
        {
            sql.append(" AND autor LIKE ?");
        }
        if (godinaIzdanja != null)
        {
            sql.append(" AND godinaIzdanja = ?");
        }
        if (status != null && !status.isEmpty())
        {
            sql.append(" AND status = ?");
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql.toString()))
        {

            int paramIndex = 1;
            if (naziv != null && !naziv.isEmpty())
            {
                preparedStatement.setString(paramIndex++, "%" + naziv + "%");
            }
            if (autor != null && !autor.isEmpty())
            {
                preparedStatement.setString(paramIndex++, "%" + autor + "%");
            }
            if (godinaIzdanja != null)
            {
                preparedStatement.setInt(paramIndex++, godinaIzdanja);
            }
            if (status != null && !status.isEmpty())
            {
                preparedStatement.setString(paramIndex++, status);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = false;

            while (resultSet.next())
            {
                found = true;
                Knjiga knjiga = new Knjiga(
                        resultSet.getInt("idknjiga"),
                        resultSet.getString("naziv"),
                        resultSet.getString("autor"),
                        resultSet.getInt("godinaIzdanja"),
                        BookStatus.fromString(resultSet.getString("status")),
                        resultSet.getDate("datumIzdavanja") != null
                                ? resultSet.getDate("datumIzdavanja").toLocalDate()
                                : null
                );
                System.out.println(knjiga);
            }

            if (!found)
            {
                System.out.println("No books found matching THIS criteria");
            }

        }
        catch (SQLException e)
        {
            System.err.println("Error searching books: " + e.getMessage());
        }
    }


}