package com.company.classes;

import java.time.LocalDate;

public class Student extends Person
{
    private int studentId;
    private Domain domain;
    private Group group;
    private HighSchool highSchool;

    public Student() { }

    public Student(int studentId, String firstName, String lastName, String email, Address address,
                   LocalDate birthDate, Domain domain, Group group, HighSchool highSchool)
    {
        super(firstName, lastName, email, address, birthDate);
        this.studentId = studentId;
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
        s = "\nStudent ID: " + studentId + "\nFirst name: " + firstName + "\nLast name: " + lastName +
                "\nEmail: " + email + "\nAddress: " + address.toString() +
                "\nBirth Date: " + birthDate.toString();
        s = s + "\nDomain Name: " + domain.getName() + "\nGroup number: " + group.getNumber()
                + "\nHigh School: " + highSchool.getName() +"\n";

        return s;
    }
}
