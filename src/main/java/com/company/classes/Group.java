package com.company.classes;

public class Group
{
    private String domain;
    private int number;

    public Group() { }

    public Group(String domain, int number)
    {
        this.number = number;
        this.domain = domain;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public String getDomain()
    {
        return domain;
    }

    public void setDomain(String domain)
    {
        this.domain = domain;
    }

    @Override
    public String toString()
    {
        return "Number: " + number + ", Domain: " + domain;
    }
}
