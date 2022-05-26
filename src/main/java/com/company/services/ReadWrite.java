package com.company.services;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.company.classes.*;

import static com.company.services.Service.domains;
import static com.company.services.Service.groups;
import static com.company.services.Service.highschools;

public class ReadWrite
{
    public static void writeSubject(int studentId, String name, int mark)
    {
        String file = "src\\main\\java\\com\\company\\files\\subjects.csv";
        try
        {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            String subjectString = studentId + "," + name + "," + mark;
            pw.println(subjectString);

            pw.flush();
            pw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static List<Subject> readSubject()
    {
        List<Subject> subjects = new ArrayList<>();
        String file = "src\\main\\java\\com\\company\\files\\subjects.csv";
        BufferedReader reader;
        String line;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while((line = reader.readLine()) != null)
            {
                String[] row = line.split(",");

                int studentId = Integer.parseInt(row[0]);
                String name = row[1];
                int mark = Integer.parseInt(row[2]);

                subjects.add(new Subject(studentId, name, mark));
            }

            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return subjects;
    }

    public static void writeDomain(String name, int numberOfYears)
    {
        String file = "src\\main\\java\\com\\company\\files\\domains.csv";
        try
        {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            String domainString = name + "," + numberOfYears;
            pw.println(domainString);

            pw.flush();
            pw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static List<Domain> readDomain()
    {
        List<Domain> domains = new ArrayList<>();
        String file = "src\\main\\java\\com\\company\\files\\domains.csv";
        BufferedReader reader;
        String line;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while((line = reader.readLine()) != null)
            {
                String[] row = line.split(",");

                String name = row[0];
                int years = Integer.parseInt(row[1]);

                domains.add(new Domain(name, years));
            }

            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return domains;
    }

    public static void writeGroup(String domain, int number)
    {
        String file = "src\\main\\java\\com\\company\\files\\groups.csv";
        try
        {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            String groupString = domain + "," + number;
            pw.println(groupString);

            pw.flush();
            pw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static List<Group> readGroup()
    {
        List<Group> groups = new ArrayList<>();
        String file = "src\\main\\java\\com\\company\\files\\groups.csv";
        BufferedReader reader;
        String line;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while((line = reader.readLine()) != null)
            {
                String[] row = line.split(",");

                String domain = row[0];
                int number = Integer.parseInt(row[1]);

                Group group = new Group(domain, number);
                groups.add(group);
            }

            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return groups;
    }

    public static void writeHighSchool(String name, Address a)
    {
        String file = "src\\main\\java\\com\\company\\files\\highschools.csv";
        try
        {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            String hsString = name + "," + a.toString();
            pw.println(hsString);

            pw.flush();
            pw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static List<HighSchool> readHighSchool()
    {
        List<HighSchool> highschools = new ArrayList<>();
        String file = "src\\main\\java\\com\\company\\files\\highschools.csv";
        BufferedReader reader;
        String line;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while((line = reader.readLine()) != null)
            {
                String[] row = line.split(",");

                String name = row[0];
                String city = row[1];
                String county = row[2];
                String street = row[3];
                int number = Integer.parseInt(row[4]);
                Address a = new Address(city, county, street, number);

                HighSchool hs = new HighSchool(name, a);
                highschools.add(hs);
            }

            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return highschools;
    }

    public static void writeStudent(int studentId, String firstName, String lastName, String email,
                                    Address a, LocalDate d, String domeniu, int grupa, String liceu)
    {
        String file = "src\\main\\java\\com\\company\\files\\student.csv";
        try
        {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            String studentString = studentId + "," + firstName + "," + lastName + "," + email + "," +
                    a.toString() + "," + d.toString() + "," + domeniu + "," + grupa + "," + liceu;
            pw.println(studentString);

            pw.flush();
            pw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static List<Student> readStudent()
    {
        List<Student> students = new ArrayList<>();
        String file = "src\\main\\java\\com\\company\\files\\student.csv";
        BufferedReader reader;
        String line;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while((line = reader.readLine()) != null)
            {
                String[] row = line.split(",");

                int id = Integer.parseInt(row[0]);
                String firstName = row[1];
                String lastName = row[2];
                String email = row[3];
                String city =  row[4];
                String county = row[5];
                String street = row[6];
                int number = Integer.parseInt(row[7]);
                LocalDate date = LocalDate.parse(row[8]);
                String domeniu = row[9];
                int grupa = Integer.parseInt(row[10]);
                String hs = row[11];

                Address a = new Address(city, county, street, number);
                Domain dom = new Domain();

                for (Domain d: domains)
                {
                    if (domeniu.equals(d.getName()))
                    {
                        dom = d;
                        break;
                    }
                }

                Group gr = new Group();

                for (Group g: groups)
                {
                    if (grupa == g.getNumber())
                    {
                        gr = g;
                        break;
                    }
                }

                HighSchool highschool = new HighSchool();

                for (HighSchool h : highschools)
                {
                    if (hs.equals(h.getName()))
                    {
                        highschool = h;
                        break;
                    }
                }

                Student s = new Student(id, firstName, lastName, email, a, date, dom, gr, highschool);

                students.add(s);
            }

            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return students;
    }
}
