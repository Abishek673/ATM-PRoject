package com.ATM;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATM {
	private static Map<Integer, User> users = new HashMap<>();
	private static User currentUser = null;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		users.put(1234, new User(1234, 1000.0));
		users.put(5678, new User(5678, 500.0));
		users.put(4321, new User(4321, 1500.0));
		
		System.out.println("=== Welcome to Secure ATM ===");
		
		if (authenticate(scanner)) {
			int choice;
			do {
				System.out.println("\n1. Check Balance");
				System.out.println("2. Deposit Money");
				System.out.println("3. Withdraw Money");
				System.out.println("4. Mini Statement");
				System.out.println("5. Exit");
				System.out.println("Enter your choice: ");
				choice = scanner.nextInt();
				
				switch (choice) {
				case 1:
					checkBalance();
					break;
				case 2:
					depositMoney(scanner);
					break;
				case 3:
					withdrawMoney(scanner);
					break;
				case 4:
					printMiniStatement();
					break;
				case 5:
					System.out.println("Thank you for using ATM. Goodbye!");
					break;
				default:
					System.out.println("Invalid Choice. Please try again.");
				}
			} while (choice != 5);
		}
		
		scanner.close();
	}
	
	private static boolean authenticate(Scanner scanner) {
		System.out.println("Enter your 4-digit PIN: ");
		int pin = scanner.nextInt();
		if (users.containsKey(pin)) {
			currentUser = users.get(pin);
			System.out.println("Authentication successful.");
			return true;
		} else {
			System.out.println("Invalid Pin. Exiting...");
			return false;
		}
	}
	
	private static void checkBalance() {
		System.out.printf("Your current Balance is: $%.2f%n", currentUser.getBalance());
	}
	
	private static void depositMoney(Scanner scanner) {
		System.out.print("Enter amount to deposit: ");
		double amount = scanner.nextDouble();
		if (amount > 0) {
			currentUser.deposit(amount);
			System.out.printf("$%.2f deposited successfully.%n", amount);
		} else {
			System.out.println("Invalid amount.");
		}
	}
	
	private static void withdrawMoney(Scanner scanner) {
		System.out.println("Enter amount to withdraw: ");
		double amount = scanner.nextDouble();
		if (currentUser.withdraw(amount)) {
			System.out.printf("$%.2f withdrawn successfully.%n", amount);
		} else {
			System.out.println("Withdrawal failed. Check balance or amount entered.");
		}
	}
	
	private static void printMiniStatement() {
		System.out.println("=== Mini Statement ===");
		for (String transaction : currentUser.getMiniStatement()) {
			System.out.println(transaction);
		}
		if (currentUser.getMiniStatement().isEmpty()) {
			System.out.println("No transaction yet.");
		}
	}
}
