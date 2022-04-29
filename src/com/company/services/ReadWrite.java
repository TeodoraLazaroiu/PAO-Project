package com.company.services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.company.classes.*;

public class ReadWrite
{
    private static final Service serviciu = new Service();

    public static void writeSubject()
    {
        String file = "src\\com\\company\\files\\subjects.csv";
        try
        {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            Subject subject = serviciu.addSubject();
            String subjectString = subject.toCsv();
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
        String file = "src\\com\\company\\files\\subjects.csv";
        BufferedReader reader = null;
        String line = "";

        try
        {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while((line = reader.readLine()) != null)
            {
                String[] row = line.split(",");

                String name = row[0];
                int number = Integer.parseInt(row[1]);

                Subject subject = new Subject(name, number);
                subjects.add(subject);
                System.out.println(subject.toString());
            }

            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return subjects;
    }

    public static void writeDomain()
    {
        String file = "src\\com\\company\\files\\domains.csv";
        try
        {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            Domain domain = serviciu.addDomain();
            String domainString = domain.toCsv();
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
        String file = "src\\com\\company\\files\\domains.csv";
        BufferedReader reader = null;
        String line = "";

        try
        {
            reader = new BufferedReader(new FileReader(file));
            line = reader.readLine();
            while((line = reader.readLine()) != null)
            {
                String[] row = line.split(",");

                String name = row[0];
                int years = Integer.parseInt(row[1]);

                Domain domain = new Domain(name, years);
                domains.add(domain);
                System.out.println(domain.toString());
            }

            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return domains;
    }

    public static void writeGroup()
    {
        String file = "src\\com\\company\\files\\groups.csv";
        try
        {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            Group group = serviciu.addGroup();
            String groupString = group.toCsv();
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
        String file = "src\\com\\company\\files\\groups.csv";
        BufferedReader reader = null;
        String line = "";

        try
        {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while((line = reader.readLine()) != null)
            {
                String[] row = line.split(",");

                String domain = row[1];
                int number = Integer.parseInt(row[0]);

                Group group = new Group(domain, number);
                groups.add(group);
                System.out.println(group.toString());
            }

            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return groups;
    }

    public static void writeHighSchool()
    {
        String file = "src\\com\\company\\files\\highschools.csv";
        try
        {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            HighSchool hs = serviciu.addHighSchool();
            String hsString = hs.toCsv();
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
        String file = "src\\com\\company\\files\\highschools.csv";
        BufferedReader reader = null;
        String line = "";

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
                System.out.println(hs.toString());
            }

            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return highschools;
    }
}
