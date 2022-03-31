package com.company;

public class Domain
{
    private String name;
    private int numberOfYears;

    public Domain() { }

    public Domain(String name, int numberOfYears)
    {
        this.name = name;
        this.numberOfYears = numberOfYears;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getNumberOfYears()
    {
        return numberOfYears;
    }

    public void setNumberOfYears(int numberOfYears)
    {
        this.numberOfYears = numberOfYears;
    }
}
