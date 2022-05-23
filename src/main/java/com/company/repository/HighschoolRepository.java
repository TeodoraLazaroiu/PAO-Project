package com.company.repository;

import com.company.classes.Address;
import com.company.classes.Domain;
import com.company.classes.HighSchool;
import com.company.database.DatabaseConfiguration;
import com.company.services.ReadWrite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class HighschoolRepository
{
    private static HighschoolRepository highschoolRepository;
    private HighschoolRepository() { }
    public static HighschoolRepository getInstance()
    {
        if (highschoolRepository == null) highschoolRepository = new HighschoolRepository();

        return highschoolRepository;
    }

    public void createTable()
    {
        String createTableSql = "CREATE TABLE IF NOT EXISTS HIGHSCHOOL " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "name varchar(60), " +
                "address varchar(100));";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            stmt.execute(createTableSql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addData()
    {
        String selectSql = "SELECT * FROM HIGHSCHOOL;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            // daca tabelul este gol se vor adauga date din CSV
            if (!resultSet.next())
            {
                List<HighSchool> highSchools = ReadWrite.readHighSchool();

                for (HighSchool hs : highSchools)
                {
                    addHighschool(hs.getName(), hs.getAddress());
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addHighschool(String name, Address a)
    {
        String insertPersonSql = "INSERT INTO HIGHSCHOOL(name, address) VALUES(\""
                + name + "\",\"" + a.toString() + "\");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate(insertPersonSql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void displayHighschools()
    {
        String selectSql = "SELECT * FROM HIGHSCHOOL;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println("High School Name: " + resultSet.getString(2));
                System.out.println("Address: " + resultSet.getString(3));
                System.out.println();
            }

            if (empty)
            {
                System.out.println("No existing high schools!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
