package com.company.classes;

import java.io.Serializable;

public class Domain implements Serializable
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

    @Override
    public String toString()
    {
        return "Domain: " + name + ", Number of years: " + numberOfYears;
    }
}
