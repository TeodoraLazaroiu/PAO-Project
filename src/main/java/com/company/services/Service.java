package com.company.services;

import com.company.database.DatabaseConfiguration;

import java.time.LocalDate;
import java.util.*;

import com.company.classes.*;
import com.company.repository.*;

public class Service
{
    public static List<Subject> subjects = new ArrayList<>();
    public static List<Domain> domains = new ArrayList<>();
    public static List<Group> groups = new ArrayList<>();
    public static List<HighSchool> highschools = new ArrayList<>();
    public static List<Student> students = new ArrayList<>();

    DomainRepository domainRepository = DomainRepository.getInstance();
    GroupRepository groupRepository = GroupRepository.getInstance();
    HighschoolRepository highschoolRepository = HighschoolRepository.getInstance();
    StudentRepository studentRepository = StudentRepository.getInstance();

    // functie ca incarca datele din fisierele csv
    // in array-uri la inceputul programului
    public void loadData()
    {
        // salveaza din CSV in liste de obiecte
        subjects = ReadWrite.readSubject();
        domains = ReadWrite.readDomain();
        groups = ReadWrite.readGroup();
        highschools = ReadWrite.readHighSchool();
        students = ReadWrite.readStudent();

        // salveaza din CSV in baza de date
        // daca aceasta este goala
        domainRepository.addData();
        groupRepository.addData();
        highschoolRepository.addData();
        studentRepository.addData();
    }

    public void configureTables()
    {
        domainRepository.createTable();
        groupRepository.createTable();
        highschoolRepository.createTable();
        studentRepository.createTable();
    }

    public Subject addSubject()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Name of the subject: ");
        String subjectName = reader.nextLine();

        int mark;

        System.out.print("Mark: ");
        while(true)
        {
            try
            {
                mark = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Mark: ");
            }
        }

        Subject sub = new Subject(subjectName, mark);
        subjects.add(sub);

        return sub;
    }

    public void printSubjects()
    {
        if (subjects.isEmpty())
        {
            System.out.println("No existing subjects!");
        }
        else
        {
            for (Subject s : subjects)
            {
                System.out.printf("Subject name: %s\n", s.getSubjectName());
                System.out.printf("Student's mark: %s\n", s.getMark());
                System.out.println();
            }
        }
    }

    public void addDomain()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Name of the domain: ");
        String domain = reader.nextLine();

        System.out.print("Number of year for the domain: ");
        int numberOfYears;

        while(true)
        {
            try
            {
                numberOfYears = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Number of year for the domain: ");
            }
        }

        domains.add(new Domain(domain, numberOfYears));
        ReadWrite.writeDomain(domain, numberOfYears);
        domainRepository.addDomain(domain, numberOfYears);
    }

    public void printDomains()
    {
        domainRepository.displayDomains();
    }

    public void addGroup()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Domain: ");
        String domain = reader.nextLine();

        System.out.print("Group number: ");
        int number;

        while(true)
        {
            try
            {
                number = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Group number: ");
            }
        }

        groups.add(new Group(domain, number));
        ReadWrite.writeGroup(domain, number);
        groupRepository.addGroup(domain, number);
    }

    public void printGroups()
    {
        groupRepository.displayGroups();
    }

    public void addHighSchool()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("High School name: ");
        String name = reader.nextLine();

        System.out.print("Address of the high school: ");
        Address a = readAddress();

        highschools.add(new HighSchool(name, a));
        ReadWrite.writeHighSchool(name, a);
        highschoolRepository.addHighschool(name, a);
    }

    public void printHighSchools()
    {
        highschoolRepository.displayHighschools();
    }

    public void addStudent()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Student ID: ");
        int studentId;

        while(true)
        {
            try
            {
                studentId = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("ID Student: ");
            }
        }

        System.out.print("First name: ");
        String firstName = reader.nextLine();

        System.out.print("Last name: ");
        String lastName = reader.nextLine();

        String email;
        do
        {
            System.out.print("Email: ");
            email = reader.nextLine();

        } while(!email.contains("@"));

        System.out.print("Address: ");
        Address a = readAddress();

        System.out.print("Birth date: ");
        LocalDate d = readDate();

        System.out.print("Number of subjects: ");
        int n;

        while(true)
        {
            try
            {
                n = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Number of subjects: ");
            }
        }

        List<Subject> subjects = new ArrayList<>();

        for (int i = 0; i < n; i ++)
        {
            System.out.print("Subject name: ");
            String name = reader.nextLine();

            System.out.print("Subject mark: ");
            int mark;

            while(true)
            {
                try
                {
                    mark = Integer.parseInt(reader.nextLine());
                    break;
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Expecting an integer value. Try again!");
                    System.out.print("Subject mark: ");
                }
            }

            Subject s = new Subject(name, mark);
            subjects.add(s);
        }

        Domain domeniu = new Domain();
        boolean check = true;

        while(check)
        {
            System.out.print("Domain name: ");
            String domain = reader.nextLine();

            for (Domain dom : domains)
            {
                if (dom.getName().equalsIgnoreCase(domain))
                {
                    domeniu = dom;
                    check = false;
                }
            }

            if (check)
            {
                System.out.println("Domain doesn't exist. Try again!");
            }
        }

        Group grupa = new Group();
        check = true;
        while(check)
        {
            System.out.print("Group number: ");
            int g;

            while(true)
            {
                try
                {
                    g = Integer.parseInt(reader.nextLine());
                    break;
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Expecting an integer value. Try again!");
                    System.out.print("Group number: ");
                }
            }

            for (Group gr : groups)
            {
                if (gr.getNumber() == g)
                {
                    grupa = gr;
                    check = false;
                }
            }

            if (check)
            {
                System.out.println("Group doesn't exist. Try again!");
            }
        }

        HighSchool liceu = new HighSchool();
        check = true;
        while(check)
        {
            System.out.print("High School name: ");
            String h = reader.nextLine();

            for (HighSchool hs : highschools)
            {
                if (hs.getName().equalsIgnoreCase(h))
                {
                    liceu = hs;
                    check = false;
                }
            }

            if (check)
            {
                System.out.println("High school doesn't exist. Try again!");
            }
        }

        students.add(new Student(studentId, firstName, lastName, email, a, d, subjects, domeniu, grupa, liceu));
        ReadWrite.writeStudent(studentId, firstName, lastName, email, a, d,
                domeniu.getName(), grupa.getNumber(), liceu.getName());
        studentRepository.addStudent(studentId, firstName, lastName, email, a, d,
                domeniu.getName(), grupa.getNumber(), liceu.getName());
    }

    public void printStudents()
    {
        studentRepository.displayStudents();
    }

    public void printStudentById()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Student ID: ");
        int studentId;

        while(true)
        {
            try
            {
                studentId = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("ID Student: ");
            }
        }

        if (studentRepository.getStudentById(studentId) != null)
        {
            System.out.println(studentRepository.getStudentById(studentId).toString());
        }
        else
        {
            System.out.println("No existing student with this id!");
        }
    }

    public void updateStudent()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Student ID: ");
        int studentId;

        while(true)
        {
            try
            {
                studentId = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Student ID: ");
            }
        }

        boolean check = false;
        for (Student s : students)
        {
            if (studentId == s.getStudentId())
            {
                check = true;
                break;
            }
        }

        if (check)
        {

            System.out.print("New first name: ");
            String firstName = reader.nextLine();

            System.out.print("New last name: ");
            String lastName = reader.nextLine();

            studentRepository.updateStudentFullName(firstName, lastName, studentId);
        }
        else
        {
            System.out.println("No existing student with this id!");
        }
    }

    public void printSortedStudent()
    {
        Collections.sort(students);
        System.out.println("Students succesfully sorted.");

        for (Student s : students)
        {
            System.out.println(s.toString());
            System.out.println();
        }
    }

    public void meanMarkStudent()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Student ID: ");
        int id;

        while(true)
        {
            try
            {
                id = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Group number: ");
            }
        }

        boolean check = false;
        for (Student s: students)
        {
            if (s.getStudentId() == id)
            {
                System.out.printf("Mean grade: %f\n", s.meanMark());
                check = true;
                break;
            }
        }
        if (!check)
        {
            System.out.println("No student found with this ID");
        }
    }

    public Address readAddress()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("City: ");
        String city = reader.nextLine();

        System.out.print("County: ");
        String county = reader.nextLine();

        System.out.print("Street: ");
        String street = reader.nextLine();

        System.out.print("Number: ");
        int number;

        while(true)
        {
            try
            {
                number = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Number: ");
            }
        }

        return new Address(city, county, street, number);
    }

    public LocalDate readDate()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Day: ");
        int day;

        while(true)
        {
            try
            {
                day = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Day: ");
            }
        }

        System.out.print("Month: ");
        int month;

        while(true)
        {
            try
            {
                month = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Month: ");
            }
        }

        System.out.print("Year: ");
        int year;

        while(true)
        {
            try
            {
                year = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("Year: ");
            }
        }

        return LocalDate.of(year, month, day);
    }

    public void closeConnection()
    {
        DatabaseConfiguration.closeDatabaseConnection();
    }
}
