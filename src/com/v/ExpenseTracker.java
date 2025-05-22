package com.v;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class ExpenseTracker {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TransactionManager manager = new TransactionManager();

        System.out.println("Do you want to load transactions from a file? (yes/no)");
        String load = sc.nextLine();

        if (load.equalsIgnoreCase("yes")) {
            System.out.println("Enter filename:");
            String filename = sc.nextLine();
            try {
                manager.loadFromFile(filename);
                System.out.println("Transactions loaded successfully.");
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }

        boolean running = true;
        while (running) {
            System.out.println("\nChoose an option:\n1. Add Income\n2. Add Expense\n3. View Monthly Summary\n4. Save to File\n5. Exit");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                case 2:
                    String type = (choice == 1) ? "Income" : "Expense";
                    System.out.println("Enter date (YYYY-MM-DD):");
                    LocalDate date = LocalDate.parse(sc.nextLine());

                    if (type.equals("Income")) {
                        System.out.println("Select Category: Salary / Business");
                    } else {
                        System.out.println("Select Category: Food / Rent / Travel");
                    }

                    String category = sc.nextLine();
                    System.out.println("Enter amount:");
                    double amount = sc.nextDouble();
                    sc.nextLine(); // consume newline

                    manager.addTransaction(new Transaction(date, type, category, amount));
                    System.out.println(type + " added successfully.");
                    break;

                case 3:
                    System.out.println("Enter year (e.g., 2025):");
                    int year = sc.nextInt();
                    System.out.println("Enter month (1-12):");
                    int month = sc.nextInt();
                    sc.nextLine(); // consume newline
                    manager.printMonthlySummary(year, month);
                    break;

                case 4:
                    System.out.println("Enter filename to save:");
                    String saveFile = sc.nextLine();
                    try {
                        manager.saveToFile(saveFile);
                        System.out.println("Saved successfully to " + saveFile);
                    } catch (IOException e) {
                        System.out.println("Error saving file: " + e.getMessage());
                    }
                    break;

                case 5:
                    running = false;
                    break;
            }
        }

        sc.close();
    }
}
