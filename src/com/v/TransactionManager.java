package com.v;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionManager {
	 private List<Transaction> transactions = new ArrayList<>();

	    public void addTransaction(Transaction t) {
	        transactions.add(t);
	    }

	    public void loadFromFile(String filename) throws IOException {
	        BufferedReader br = new BufferedReader(new FileReader(filename));
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] parts = line.split(",");
	            Transaction t = new Transaction(
	                    LocalDate.parse(parts[0]),
	                    parts[1],
	                    parts[2],
	                    Double.parseDouble(parts[3])
	            );
	            addTransaction(t);
	        }
	        br.close();
	    }

	    public void saveToFile(String filename) throws IOException {
	        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
	        for (Transaction t : transactions) {
	            bw.write(t.toCSV());
	            bw.newLine();
	        }
	        bw.close();
	    }

	    public void printMonthlySummary(int year, int month) {
	        double income = 0, expense = 0;
	        Map<String, Double> categoryMap = new HashMap<>();

	        for (Transaction t : transactions) {
	            if (t.getDate().getYear() == year && t.getDate().getMonthValue() == month) {
	                if (t.getType().equalsIgnoreCase("Income")) {
	                    income += t.getAmount();
	                } else {
	                    expense += t.getAmount();
	                }
	                categoryMap.put(t.getCategory(), categoryMap.getOrDefault(t.getCategory(), 0.0) + t.getAmount());
	            }
	        }

	        System.out.println("=== Monthly Summary for " + year + "-" + month + " ===");
	        System.out.println("Total Income: ₹" + income);
	        System.out.println("Total Expense: ₹" + expense);
	        System.out.println("Category Breakdown:");
	        for (String cat : categoryMap.keySet()) {
	            System.out.println(cat + ": ₹" + categoryMap.get(cat));
	        }
	    }
}
