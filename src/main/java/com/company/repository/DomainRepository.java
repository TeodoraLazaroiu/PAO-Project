package com.company.repository;

import com.company.classes.Domain;
import com.company.database.DatabaseConfiguration;
import com.company.services.ReadWrite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DomainRepository
{
    private static DomainRepository domainRepository;
    private DomainRepository() { }
    public static DomainRepository getInstance()
    {
        if (domainRepository == null) domainRepository = new DomainRepository();

        return domainRepository;
    }

    public void createTable()
    {
        String createTableSql = "CREATE TABLE IF NOT EXISTS DOMAIN " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "name varchar(40), " +
                "numberOfYears int);";

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
        String selectSql = "SELECT * FROM DOMAIN;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            // daca tabelul este gol se vor adauga date din CSV
            if (!resultSet.next())
            {
                List<Domain> domains = ReadWrite.readDomain();

                for (Domain d : domains)
                {
                    addDomain(d.getName(), d.getNumberOfYears());
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addDomain(String name, int numberOfYears)
    {
        String insertPersonSql = "INSERT INTO DOMAIN(name, numberOfYears) VALUES(\""
                + name + "\", " + numberOfYears + ");";

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

    public void displayDomains()
    {
        String selectSql = "SELECT * FROM DOMAIN;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println("Domain name: " + resultSet.getString(2));
                System.out.println("Number of years: " + resultSet.getInt(3));
                System.out.println();
            }

            if (empty)
            {
                System.out.println("No existing domains!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
