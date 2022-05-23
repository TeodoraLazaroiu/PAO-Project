package com.company.services;

import com.company.database.DatabaseConfiguration;

public class Main
{

    public static void main(String[] args)
    {
        Menu m = Menu.getInstance();
        m.showMenu();
    }
}
