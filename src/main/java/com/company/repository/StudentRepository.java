package com.company.repository;

import com.company.classes.*;
import com.company.database.DatabaseConfiguration;
import com.company.services.ReadWrite;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static com.company.services.Service.*;

public class StudentRepository
{
    private static StudentRepository studentRepository;
    private StudentRepository() { }
    public static StudentRepository getInstance()
    {
        if (studentRepository == null) studentRepository = new StudentRepository();

        return studentRepository;
    }

    public void createTable()
    {
        String createTableSql = "CREATE TABLE IF NOT EXISTS STUDENT " +
                "(id int PRIMARY KEY, " +
                "firstname varchar(40), " +
                "lastname varchar(30), " +
                "email varchar(40), " +
                "address varchar(100), " +
                "birthdate date, " +
                "domain varchar(40), " +
                "groupnumber int, " +
                "highschool varchar(60));";

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
        String selectSql = "SELECT * FROM STUDENT;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(selectSql);

            // daca tabelul este gol se vor adauga date din CSV
            if (!resultSet.next())
            {
                List<Student> students = ReadWrite.readStudent();

                for (Student s : students)
                {
                    addStudent(s.getStudentId(), s.getFirstName(), s.getLastName(), s.getEmail(),
                            s.getAddress(), s.getBirthDate(), s.getDomain().getName(),
                            s.getGroup().getNumber(), s.getHighSchool().getName());
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addStudent(int id, String firstname, String lastname, String email, Address a,
                           LocalDate date, String domain, int groupnumber, String highschool)
    {
        String s = id + ",\"" + firstname + "\", \"" + lastname +
                "\", \"" + email + "\", \"" + a.toString() + "\", '" +
                date.toString() + "', \"" + domain + "\", " +
                groupnumber + ", \"" + highschool + "\"";

        String insertStudentSql = "INSERT INTO STUDENT(id, firstname, lastname, email, address, birthdate, " +
                "domain, groupnumber, highschool) VALUES(" + s + ");";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate(insertStudentSql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void displayStudents()
    {
        String selectSql = "SELECT * FROM STUDENT;";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement())
        {
            boolean empty = true;
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                empty = false;
                System.out.println("Student ID: " + resultSet.getInt(1));
                System.out.println("First name: " + resultSet.getString(2));
                System.out.println("Last name: " + resultSet.getString(3));
                System.out.println("Email: " + resultSet.getString(4));
                System.out.println("Address: " + resultSet.getString(5));
                System.out.println("Birth Date: " + resultSet.getDate(6).toString());
                System.out.println("Domain name: " + resultSet.getString(7));
                System.out.println("Group number: " + resultSet.getInt(8));
                System.out.println("High School: " + resultSet.getString(9));
                System.out.println();
            }

            if (empty)
            {
                System.out.println("No existing students!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Student getStudentById(int id)
    {
        String selectSql = "SELECT * FROM STUDENT WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql))
        {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToStudent(resultSet);
        }
        catch (SQLException e)
        {
            return null;
        }

    }

    public void updateStudentFullName(String firstname, String lastname, int id)
    {
        String updateNameSql = "UPDATE STUDENT SET firstname=?, lastname=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql))
        {
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteStudentById(int id)
    {
        String deleteStudentSql = "DELETE FROM STUDENT WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteStudentSql))
        {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private Student mapToStudent(ResultSet resultSet) throws SQLException
    {
        if (resultSet.next())
        {
            String[] a = resultSet.getString(5).split(",");
            Address address = new Address(a[0], a[1], a[2], Integer.parseInt(a[3]));

            LocalDate date = LocalDate.parse(resultSet.getString(6));

            Domain dom = new Domain();
            String domeniu = resultSet.getString(7);

            for (Domain d: domains)
            {
                if (domeniu.equals(d.getName()))
                {
                    dom = d;
                    break;
                }
            }

            Group gr = new Group();
            int grupa = resultSet.getInt(8);

            for (Group g: groups)
            {
                if (grupa == g.getNumber())
                {
                    gr = g;
                    break;
                }
            }

            HighSchool highschool = new HighSchool();
            String hs = resultSet.getString(9);

            for (HighSchool h : highschools)
            {
                if (hs.equals(h.getName()))
                {
                    highschool = h;
                    break;
                }
            }

            return new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), address, date, dom, gr, highschool);
        }
        return null;
    }
}
