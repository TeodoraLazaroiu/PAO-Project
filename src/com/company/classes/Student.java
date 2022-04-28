package com.company.classes;

import java.time.LocalDate;
import java.util.List;

public class Student extends Person implements Comparable<Student>
{
    private int studentId;
    private List<Subject> subjects;
    private Domain domain;
    private Group group;
    private HighSchool highSchool;

    public Student() { }

    public Student(String firstName, String lastName, String email, Address address, LocalDate birthDate,
                   int studentId, List<Subject> subjects, Domain domain, Group group, HighSchool highSchool)
    {
        super(firstName, lastName, email, address, birthDate);
        this.studentId = studentId;
        this.subjects = subjects;
        this.domain = domain;
        this.group = group;
        this.highSchool = highSchool;
    }

    public int getStudentId()
    {
        return studentId;
    }

    public void setStudentId(int studentId)
    {
        this.studentId = studentId;
    }

    public List<Subject> getSubjects()
    {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects)
    {
        this.subjects = subjects;
    }

    public Domain getDomain()
    {
        return domain;
    }

    public void setDomain(Domain domain)
    {
        this.domain = domain;
    }

    public Group getGroup()
    {
        return group;
    }

    public void setGroup(Group group)
    {
        this.group = group;
    }

    public HighSchool getHighSchool()
    {
        return highSchool;
    }

    public void setHighSchool(HighSchool highSchool)
    {
        this.highSchool = highSchool;
    }

    @Override
    public String toString()
    {
        String s;
        s = "\nFirst name: " + firstName + "\nLast name: " + lastName +
                "\nEmail: " + email + "\nAddress: " + address.toString() +
                "\nBirth Date: " + birthDate.toString() + "\nStudent ID: " + studentId;
        for (Subject subject: subjects)
        {
            s = s + "\n" + subject.toString();
        }
        s = s + "\nDomain: " + domain.getName() + "\nGroup: " + group.getNumber()
                + "\nHighSchool: " + highSchool.getName() +"\n";

        return s;
    }

    public float meanMark()
    {
        float mean = 0;

        for (Subject s : subjects)
        {
            mean = mean + s.getMark();
        }

        mean = mean / subjects.size();

        return mean;
    }

    @Override
    public int compareTo(Student s)
    {
        return (int)(this.meanMark() - s.meanMark());
    }
}
