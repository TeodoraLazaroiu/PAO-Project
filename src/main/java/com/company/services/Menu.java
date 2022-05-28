package com.company.services;

import java.util.Scanner;

public class Menu
{
    // singleton class for an interactive menu

    private static Menu menu = null;
    private static final Service serviciu = new Service();

    // private constructor
    private Menu()
    {
        serviciu.configureTables();
        serviciu.loadData();
    }

    private void showMenu()
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
        System.out.println("11: Print students sorted alphabetically");
        System.out.println("12: Print student by a given id");
        System.out.println("13: Update student name by a given id");
        System.out.println("14: Delete student by a given id");
        System.out.println("0: Exit");
        System.out.println("-----------------------------------");
    }

    public void runMenu()
    {
        showMenu();

        Scanner reader = new Scanner(System.in);
        int option;
        String check;

        do
        {
            option = reader.nextInt();
            reader.nextLine();

            switch (option) {
                case 0 -> {
                    serviciu.closeConnection();
                    System.out.println("Exiting program..");
                }
                case 1 -> {
                    serviciu.addSubject();
                    System.out.println("-----------------------------------");
                }
                case 2 -> {
                    serviciu.printSubjects();
                    System.out.println("-----------------------------------");
                }
                case 3 -> {
                    serviciu.addDomain();
                    System.out.println("-----------------------------------");
                }
                case 4 -> {
                    serviciu.printDomains();
                    System.out.println("-----------------------------------");
                }
                case 5 -> {
                    serviciu.addGroup();
                    System.out.println("-----------------------------------");
                }
                case 6 -> {
                    serviciu.printGroups();
                    System.out.println("-----------------------------------");
                }
                case 7 -> {
                    serviciu.addHighSchool();
                    System.out.println("-----------------------------------");
                }
                case 8 -> {
                    serviciu.printHighSchools();
                    System.out.println("-----------------------------------");
                }
                case 9 -> {
                    serviciu.addStudent();
                    System.out.println("-----------------------------------");
                }
                case 10 -> {
                    serviciu.printStudents();
                    System.out.println("-----------------------------------");
                }
                case 11 -> {
                    serviciu.printSortedStudents();
                    System.out.println("-----------------------------------");
                }
                case 12 -> {
                    serviciu.printStudentById();
                    System.out.println("-----------------------------------");
                }
                case 13 -> {
                    serviciu.updateStudent();
                    System.out.println("-----------------------------------");
                }
                case 14 -> {
                    serviciu.deleteStudentById();
                    System.out.println("-----------------------------------");
                }
                default -> System.out.println("Invalid option. Try again.");
            }

            if (option != 0)
            {
                System.out.println("Do you want another action? y/n");
                check = reader.nextLine();
                check = check.toLowerCase();

                if (check.equals("y")) showMenu();
                else {
                    option = 0;
                    serviciu.closeConnection();
                    System.out.println("Exiting program..");
                }
            }

        } while (option != 0);

        reader.close();
    }

    public static Menu getInstance()
    {
        if (menu == null) menu = new Menu();

        return menu;
    }
}
