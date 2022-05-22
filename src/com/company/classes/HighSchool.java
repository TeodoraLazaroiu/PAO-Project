package com.company.classes;

public class HighSchool
{
    private String name;
    private Address address = new Address();

    public HighSchool() { }

    public HighSchool(String name, Address address)
    {
        this.name = name;
        this.address = address;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    @Override
    public String toString()
    {
        return "Name: " + name + ", Address: " + address.toString();
    }

    public String toCsv() { return name + "," + address.toCsv(); }
}
