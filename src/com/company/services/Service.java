package com.company.services;

import java.time.LocalDate;
import java.util.*;

import com.company.classes.Subject;
import com.company.classes.Domain;
import com.company.classes.Group;
import com.company.classes.HighSchool;
import com.company.classes.Student;
import com.company.classes.Address;

public class Service
{
    private final List<Subject> subjects = new ArrayList<>();
    private final Set<Domain> domains = new HashSet();
    private final List<Group> groups = new ArrayList<>();
    private final List<HighSchool> highschools = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();

    public void addSubject()
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

        if (domain.equalsIgnoreCase("INFO"))
        {
            domain = "informatica";
        }
        else if (domain.equalsIgnoreCase("MATE"))
        {
            domain = "matematica";
        }
        else if (domain.equalsIgnoreCase("CTI"))
        {
            domain = "calculatoare si tehnologia informatiei";
        }

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

        Domain dom = new Domain(domain, numberOfYears);
        domains.add(dom);
    }

    public void printDomains()
    {
        if (domains.isEmpty())
        {
            System.out.println("No existing domains!");
        }
        else
        {
            for (Domain d :domains)
            {
                System.out.printf("Domain name: %s\n", d.getName());
                System.out.printf("Number of years: %s\n", d.getNumberOfYears());
                System.out.println();
            }
        }
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

        Group g = new Group(domain, number);
        groups.add(g);
    }

    public void printGroups()
    {
        if (groups.isEmpty())
        {
            System.out.println("No existing groups!");
        }
        else
        {
            for (Group g :groups)
            {
                System.out.printf("Group number: %d\n", g.getNumber());
                System.out.printf("Domain name: %s\n", g.getDomain());
                System.out.println();
            }
        }
    }

    public void addHighSchool()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("High School name: ");
        String name = reader.nextLine();

        System.out.print("Address of the high school: ");
        Address a = readAddress();

        HighSchool hs = new HighSchool(name, a);
        highschools.add(hs);
    }

    public void printHighSchools()
    {
        if (highschools.isEmpty())
        {
            System.out.println("No existing high schools!");
        }
        else
        {
            for (HighSchool hs :highschools)
            {
                System.out.printf("High School name: %s\n", hs.getName());
                System.out.printf("Address: %s\n", hs.getAddress().toString());
                System.out.println();
            }
        }
    }

    public void addStudent()
    {
        Scanner reader = new Scanner(System.in);

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

        System.out.print("ID Student: ");
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

        Student s = new Student(firstName, lastName, email, a, d, studentId, subjects, domeniu, grupa, liceu);
        students.add(s);
    }

    public void printStudents()
    {
        if (students.isEmpty())
        {
            System.out.println("No existing students!");
        }
        else
        {
            for (Student s: students)
            {
                String s1 = s.toString();
                System.out.println(s1);
            }
        }
    }

    public void sortStudent()
    {
        Collections.sort(students);
        System.out.println("Students succesfully sorted.");
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
}
