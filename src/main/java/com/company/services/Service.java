package com.company.services;

import com.company.database.DatabaseConfiguration;

import java.io.IOException;
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
    SubjectRepository subjectRepository = SubjectRepository.getInstance();

    AuditService auditService = AuditService.getInstance();

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
        subjectRepository.addData();
        domainRepository.addData();
        groupRepository.addData();
        highschoolRepository.addData();
        studentRepository.addData();

        try
        {
            auditService.logAction("load data");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void configureTables()
    {
        subjectRepository.createTable();
        domainRepository.createTable();
        groupRepository.createTable();
        highschoolRepository.createTable();
        studentRepository.createTable();

        try
        {
            auditService.logAction("configure tables");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addSubject()
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

        System.out.print("Name of the subject: ");
        String subjectName = reader.nextLine().toUpperCase();

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

        subjects.add(new Subject(studentId, subjectName, mark));
        ReadWrite.writeSubject(studentId, subjectName, mark);
        subjectRepository.addSubject(studentId, subjectName, mark);

        try
        {
            auditService.logAction("add subject");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printSubjects()
    {
        subjectRepository.displaySubjects();

        try
        {
            auditService.logAction("print subjects");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addDomain()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Name of the domain: ");
        String domain = reader.nextLine();
        domain = domain.substring(0,1).toUpperCase() + domain.substring(1).toLowerCase();

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

        try
        {
            auditService.logAction("add domain");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printDomains()
    {
        domainRepository.displayDomains();

        try
        {
            auditService.logAction("print domains");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addGroup()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Domain: ");
        String domain = reader.nextLine();
        domain = domain.substring(0,1).toUpperCase() + domain.substring(1).toLowerCase();

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

        try
        {
            auditService.logAction("add group");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printGroups()
    {
        groupRepository.displayGroups();

        try
        {
            auditService.logAction("print groups");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addHighSchool()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("High School name: ");
        String name = reader.nextLine();
        name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();

        System.out.print("Address of the high school: ");
        Address a = readAddress();

        highschools.add(new HighSchool(name, a));
        ReadWrite.writeHighSchool(name, a);
        highschoolRepository.addHighschool(name, a);

        try
        {
            auditService.logAction("add high school");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printHighSchools()
    {
        highschoolRepository.displayHighschools();

        try
        {
            auditService.logAction("print high schools");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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

                boolean check = true;
                for (Student s : students)
                {
                    if (s.getStudentId() == studentId)
                    {
                        check = false;
                        break;
                    }
                }

                if (!check) throw new Exception();
                else break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Expecting an integer value. Try again!");
                System.out.print("ID Student: ");
            }
            catch (Exception e)
            {
                System.out.println("Student with given id already exists. Try again!");
                System.out.print("ID Student: ");
            }
        }

        System.out.print("First name: ");
        String firstName = reader.nextLine();
        firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();

        System.out.print("Last name: ");
        String lastName = reader.nextLine();
        lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();

        String email;
        while(true)
        {
            System.out.print("Email: ");
            email = reader.nextLine().toLowerCase();

            if (!email.contains("@")) System.out.println("Not a valid email address! Try again!");
            else break;
        }

        System.out.print("Address: ");
        Address a = readAddress();

        System.out.print("Birth date: ");
        LocalDate d = readDate();

        Domain domeniu = new Domain();
        boolean check = true;

        while(check)
        {
            System.out.print("Domain name: ");
            String domain = reader.nextLine();
            domain = domain.substring(0,1).toUpperCase() + domain.substring(1).toLowerCase();

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
                if (gr.getNumber() == g && domeniu.getName().equals(gr.getDomain()))
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
            h = h.substring(0,1).toUpperCase() + h.substring(1).toLowerCase();

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

        students.add(new Student(studentId, firstName, lastName, email, a, d, domeniu, grupa, liceu));
        ReadWrite.writeStudent(studentId, firstName, lastName, email, a, d,
                domeniu.getName(), grupa.getNumber(), liceu.getName());
        studentRepository.addStudent(studentId, firstName, lastName, email, a, d,
                domeniu.getName(), grupa.getNumber(), liceu.getName());

        try
        {
            auditService.logAction("add student");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printStudents()
    {
        studentRepository.displayStudents();

        try
        {
            auditService.logAction("prints students");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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

        try
        {
            auditService.logAction("print student by id");
        }
        catch (IOException e)
        {
            e.printStackTrace();
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
            firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();

            System.out.print("New last name: ");
            String lastName = reader.nextLine();
            lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();

            studentRepository.updateStudentFullName(firstName, lastName, studentId);
            System.out.println("Student updated succesfully!");
        }
        else
        {
            System.out.println("No existing student with this id!");
        }

        try
        {
            auditService.logAction("update student");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteStudentById()
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
            studentRepository.deleteStudentById(studentId);
            System.out.println("Student deleted succesfully!");
        }
        else
        {
            System.out.println("No existing student with this id!");
        }

        try
        {
            auditService.logAction("delete student");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printSortedStudents()
    {
        // stream and lambda expression
        List<Student> sortedStudents = students.stream().
                sorted(Comparator.comparing(Person::getLastName)).toList();

        System.out.println("Students succesfully sorted.");

        for (Student s : sortedStudents)
        {
            System.out.print(s.toString());
        }
    }

    public Address readAddress()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("City: ");
        String city = reader.nextLine();
        city = city.substring(0,1).toUpperCase() + city.substring(1).toLowerCase();

        System.out.print("County: ");
        String county = reader.nextLine();
        county = county.substring(0,1).toUpperCase() + county.substring(1).toLowerCase();

        System.out.print("Street: ");
        String street = reader.nextLine();
        street = street.substring(0,1).toUpperCase() + street.substring(1).toLowerCase();

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

        try
        {
            auditService.logAction("close connection with database");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
