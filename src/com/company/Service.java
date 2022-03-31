package com.company;

import java.util.*;

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

        System.out.print("Mark: ");
        int mark = reader.nextInt();

        Subject sub = new Subject(subjectName, mark);
        subjects.add(sub);
    }

    public void printSubjects()
    {
        for (Subject s :subjects)
        {
            System.out.printf("Subject name: %s\n", s.getSubjectName());
            System.out.printf("Student's mark: %s\n", s.getMark());
            System.out.println();
        }
    }

    public void addDomain()
    {
        Scanner reader = new Scanner(System.in);


        System.out.print("Name of the domain: ");
        String domain = reader.nextLine();

        if (domain.equalsIgnoreCase("INFO"))
        {
            domain = "Informatica";
        }
        else if (domain.equalsIgnoreCase("MATE"))
        {
            domain = "Matematica";
        }
        else if (domain.equalsIgnoreCase("CTI"))
        {
            domain = "Calculatoare si Tehnologia informatiei";
        }

        System.out.print("Number of year for the domain: ");
        int numberOfYears = reader.nextInt();

        Domain dom = new Domain(domain, numberOfYears);
        domains.add(dom);
    }

    public void printDomains()
    {
        for (Domain d :domains)
        {
            System.out.printf("Domain name: %s\n", d.getName());
            System.out.printf("Number of years: %s\n", d.getNumberOfYears());
            System.out.println();
        }
    }

    public void addGroup()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Domain: ");
        String domain = reader.nextLine();

        System.out.print("Group number: ");
        int number = reader.nextInt();

        Group g = new Group(domain, number);
        groups.add(g);
    }

    public void printGroups()
    {
        for (Group g :groups)
        {
            System.out.printf("Group number: %d\n", g.getNumber());
            System.out.printf("Domain name: %s\n", g.getDomain());
            System.out.println();
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
        for (HighSchool hs :highschools)
        {
            System.out.printf("High School name: %s\n", hs.getName());
            System.out.printf("Address: %s\n", hs.getAddress().toString());
            System.out.println();
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
        Date d = readDate();

        System.out.print("ID Student: ");
        int studentId = reader.nextInt();

        System.out.print("Number of subjects: ");
        int n = reader.nextInt();

        List<Subject> subjects = new ArrayList<>();

        for (int i = 0; i < n; i ++)
        {
            System.out.print("Subject name: ");
            reader.nextLine();
            String name = reader.nextLine();

            System.out.print("Subject mark: ");
            int mark = reader.nextInt();

            Subject s = new Subject(name, mark);
            subjects.add(s);
        }

        Domain domeniu = new Domain();
        boolean check = true;

        while(check)
        {
            System.out.print("Domain name: ");
            reader.nextLine();
            String domain = reader.nextLine();

            for (Domain dom : domains)
            {
                if (dom.getName().equals(domain))
                {
                    domeniu = dom;
                    check = false;
                }
            }
        }

        Group grupa = new Group();
        check = true;
        while(check)
        {
            System.out.print("Group number: ");
            int g = reader.nextInt();

            for (Group gr : groups)
            {
                if (gr.getNumber() == g)
                {
                    grupa = gr;
                    check = false;
                }
            }
        }

        HighSchool liceu = new HighSchool();
        check = true;
        while(check)
        {
            System.out.print("High School name: ");
            reader.nextLine();
            String h = reader.nextLine();

            for (HighSchool hs : highschools)
            {
                if (hs.getName().equals(h))
                {
                    liceu = hs;
                    check = false;
                }
            }
        }

        Student s = new Student(firstName, lastName, email, a, d, studentId, subjects, domeniu, grupa, liceu);
        students.add(s);
    }

    public void printStudents()
    {
        for (Student s: students)
        {
            String s1 = s.toString();
            System.out.println(s1);
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
        int id = reader.nextInt();

        for (Student s: students)
        {
            if (s.getStudentId() == id)
            {
                System.out.printf("Mean grade: %f\n", s.meanMark());
                break;
            }
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
        int number = reader.nextInt();

        return new Address(city, county, street, number);
    }

    public Date readDate()
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Day: ");
        int day = reader.nextInt();

        System.out.print("Month: ");
        int month = reader.nextInt();

        System.out.print("Year: ");
        int year = reader.nextInt();

        return new Date(day, month, year);
    }
}
