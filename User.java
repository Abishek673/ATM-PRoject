package com.ATM;
import java.util.ArrayList;
import java.util.List;

public class User {
	private int pin;
	private double balance;
	private List<String> miniStatement;
	
	public User(int pin, double initialBalance) {
		this.pin = pin;
		this.balance = initialBalance;
		this.miniStatement = new ArrayList<>();
	}
	
	public int getPin() {
		return pin;
	}
	public double getBalance() {
		return balance;
	}
	
	public void deposit(double amount) {
		balance += amount;
		miniStatement.add("Deposited: $" + amount);
	}
	
	public boolean withdraw(double amount) {
		if (amount > 0 && amount <= balance) {
			balance -= amount;
			miniStatement.add("Withdrew: $" + amount);
			return true;
		} else {
			return false;
		}
	}
	
	public List<String> getMiniStatement() {
		return miniStatement;
	}
}
