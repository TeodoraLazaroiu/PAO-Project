package com.company;

import java.util.List;
import java.util.Scanner;

public class Menu extends Service
{
    // singleton class for an interactive menu

    private static Menu menu = null;
    private static final Service serviciu = new Service();

    // private constructor
    private Menu() { }

    public static void showMenu()
    {
        System.out.println("-----------------------------------");
        System.out.println("Choose an action.");
        System.out.println("1: Add new subject");
        System.out.println("2: Print all subjects");
        System.out.println("3: Add new domain");
        System.out.println("4: Print all domains");
        System.out.println("5: Add new group");
        System.out.println("6: Print all groups");
        System.out.println("7: Add new high school");
        System.out.println("8: Print all high schools");
        System.out.println("9: Add new student");
        System.out.println("10: Print all students");
        System.out.println("11: Sort students by average grade");
        System.out.println("12: Average grade of a student");
        System.out.println("0: Exit");
        System.out.println("-----------------------------------");

        Scanner reader = new Scanner(System.in);
        int option = reader.nextInt();

        while (option != 0)
        {
            switch(option)
            {
                case 0:
                {
                    System.out.println("Exiting program.");
                    break;
                }
                case 1:
                {
                    serviciu.addSubject();
                    System.out.println("-----------------------------------");
                    break;
                }
                case 2:
                {
                    serviciu.printSubjects();
                    System.out.println("-----------------------------------");
                    break;
                }
                case 3:
                {
                    serviciu.addDomain();
                    System.out.println("-----------------------------------");
                    break;
                }
                case 4:
                {
                    serviciu.printDomains();
                    System.out.println("-----------------------------------");
                    break;
                }
                case 5:
                {
                    serviciu.addGroup();
                    System.out.println("-----------------------------------");
                    break;
                }
                case 6:
                {
                    serviciu.printGroups();
                    System.out.println("-----------------------------------");
                    break;
                }
                case 7:
                {
                    serviciu.addHighSchool();
                    System.out.println("-----------------------------------");
                    break;
                }
                case 8:
                {
                    serviciu.printHighSchools();
                    System.out.println("-----------------------------------");
                    break;
                }
                case 9:
                {
                    serviciu.addStudent();
                    System.out.println("-----------------------------------");
                    break;
                }
                case 10:
                {
                    serviciu.printStudents();
                    System.out.println("-----------------------------------");
                    break;
                }
                case 11:
                {
                    serviciu.sortStudent();
                    System.out.println("-----------------------------------");
                    break;
                }
                case 12:
                {
                    serviciu.meanMarkStudent();
                    System.out.println("-----------------------------------");
                    break;
                }
                default:
                {
                    System.out.println("Invalid option. Try again.");
                    break;
                }
            }

            option = reader.nextInt();
        }
        reader.close();
    }

    public static Menu getInstance()
    {
        if (menu == null) menu = new Menu();

        return menu;
    }
}
