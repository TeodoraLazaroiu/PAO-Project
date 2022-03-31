package com.company;

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
}
