package com.company.repository;

import com.company.classes.Domain;
import com.company.classes.Group;
import com.company.database.DatabaseConfiguration;
import com.company.services.ReadWrite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GroupRepository
{
    private static GroupRepository groupRepository;
    private GroupRepository() { }
    public static GroupRepository getInstance()
    {
        if (groupRepository == null) groupRepository = new GroupRepository();

        return groupRepository;
    }

    public void createTable()
    {
        String createTableSql = "CREATE TABLE IF NOT EXISTS STUDENTGROUP " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "domain varchar(40), " +
                "number int);";

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
        String selectSql = "SELECT * FROM STUDENTGROUP;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            // daca tabelul este gol se vor adauga date din CSV
            if (!resultSet.next())
            {
                List<Group> groups = ReadWrite.readGroup();

                for (Group g : groups)
                {
                    addGroup(g.getDomain(), g.getNumber());
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addGroup(String domain, int number)
    {
        String insertPersonSql = "INSERT INTO STUDENTGROUP(domain, number) VALUES(\""
                + domain + "\", " + number + ");";

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

    public void displayGroups()
    {
        String selectSql = "SELECT * FROM STUDENTGROUP;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println("Domain name: " + resultSet.getString(2));
                System.out.println("Group number: " + resultSet.getInt(3));
                System.out.println();
            }

            if (empty)
            {
                System.out.println("No existing groups!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
