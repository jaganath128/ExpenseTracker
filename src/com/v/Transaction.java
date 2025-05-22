package com.v;

import java.time.LocalDate;

public class Transaction {
	 LocalDate date;
	    String type; // Income or Expense
	    String category;
	    double amount;

	    public Transaction(LocalDate date, String type, String category, double amount) {
	        this.date = date;
	        this.type = type;
	        this.category = category;
	        this.amount = amount;
	    }
	    
	    
	    public LocalDate getDate() {
	        return date;
	    }

	    public String getType() {
	        return type;
	    }

	    public String getCategory() {
	        return category;
	    }

	    public double getAmount() {
	        return amount;
	    }

	    public String toCSV() {
	        return date + "," + type + "," + category + "," + amount;
	    }
}
