package com.company.classes;

public class Subject
{
    private String subjectName;
    private int mark;

    public Subject() { }

    public Subject(String subjectName, int mark)
    {
        this.subjectName = subjectName;
        this.mark = mark;
    }

    public String getSubjectName()
    {
        return subjectName;
    }

    public void setSubjectName(String subjectName)
    {
        this.subjectName = subjectName;
    }

    public int getMark()
    {
        return mark;
    }

    public void setMark(int mark)
    {
        this.mark = mark;
    }

    @Override
    public String toString()
    {
        return "Subject: " + subjectName + ", Mark: " + mark;
    }
}
