package bankingPackage;

import java.io.*;
import java.util.*;

public class RBI {
	public static void main(String[] args) {
		System.out.println("********Welcome to National Banking System********");
		System.out.println("\n");
		System.out.println("Do you want to open an account : 1. YES 2. NO");

		Scanner sc = new Scanner(System.in);
		String choice = sc.nextLine();

		if (choice.equalsIgnoreCase("Yes")) {
			OpenAccount obj = new OpenAccount();
			obj.createAccount();
		}
		if (choice.equalsIgnoreCase("No")) {
			BankAccount obj1 = new BankAccount();
			obj1.showMenu();
		}
	}
}

class OpenAccount {
	String name;
	int accountName;
	String accountType;
	String dob;
	String bank;

	void createAccount() {
		Scanner sc = new Scanner(System.in);

		System.out.println("In which bank you want to open it : 1. SBI 2. PNB 3. ICICI");
		int choiceBank = sc.nextInt();
		if (choiceBank == 1) {
			bank = "SBI";
		}
		if (choiceBank == 2) {
			bank = "PNB";
		}
		if (choiceBank == 3) {
			bank = "ICICI";
		}

		System.out.println("Please enter your name: ");
		sc.nextLine();
		name = sc.nextLine();

		System.out.println("Please enter your date of birth: ");
		dob = sc.nextLine();

		System.out.println("What type of account you want to open: 1. Saving 2. Current");
		int choice = sc.nextInt();
		if (choice == 1) {
			accountType = "Saving";
		}
		if (choice == 1) {
			accountType = "Current";
		}

		System.out.println("Your account has been opened with following details");
		System.out.println("Bank: " + bank);
		System.out.println("Name: " + name);
		System.out.println("DOB: " + dob);
		System.out.println("Account Type: " + accountType);
		System.out.println("Account Number: " + Math.random());

		System.out.println("\n");

		BankAccount obj1 = new BankAccount();
		obj1.showMenu();
		sc.close();
	}
}

class BankAccount {
	int balance;
	int previousTransaction;
	String customerName;
	String customerId;
	String accountType;
	double totalInterest;

	void calculateInterest(double balance) {
		System.out.println("What type of account you have: 1. Saving 2. Current");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();

		if (choice == 1) {
			accountType = "Savings";
			int r = 5;
			int t;
			System.out.println("Enter year to calculate interest");
			t = sc.nextInt();

			double finalAmount = balance * (1 + r * t);

			totalInterest = finalAmount - balance;
			System.out.println("Total interest earned is: " + totalInterest);

		}
		if (choice == 2) {
			accountType = "Current";
			int r = 8;
			int t, n;
			System.out.println("Enter year to calculate interest");
			t = sc.nextInt();
			System.out.println("Enter the frequency that interest has been compound in a year");
			n = sc.nextInt();

			double finalAmount = balance * (Math.pow((1 + r / n), (n * t)));

			totalInterest = finalAmount - balance;
			System.out.println("Total interest earned is: " + totalInterest);
			sc.close();
		}
	}

	void deposit(int amount) {
		if (amount != 0)
			;
		{
			balance = balance + amount;
			System.out.println("Balance after deposit: " + balance);
			previousTransaction = amount;
		}
	}

	void withdraw(int amount) {
		if (amount != 0)
			;
		{
			balance = balance - amount;
			System.out.println("Balance after deposit: " + balance);
			previousTransaction = -amount;
		}
	}

	void getpreviousTransaction() {

		FileOutputStream out;
		PrintStream p;

		try {
			out = new FileOutputStream("C:\\Users\\Jaspal\\eclipse-workspace\\Project_2\\project_output\\previousTransaction.txt");
			p = new PrintStream(out);

			if (previousTransaction > 0) {
				p.append("Deposited: " + previousTransaction);
				System.out.println("Deposited: " + previousTransaction);
			} else if (previousTransaction < 0) {
				p.append("Withdrawn: " + previousTransaction);
				System.out.println("Withdrwn: " + Math.abs(previousTransaction));
			} else {
				System.out.println("No transaction occured");
			}
			p.close();
		} catch (Exception e) {
			System.out.println("Error in printing the data " + e);
		}
	}

	void showMenu() {
		char option = '\0';
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the menu");
		System.out.println("\n");
		System.out.println("A. Check Balance");
		System.out.println("B. Deposite Amount");
		System.out.println("C. Withdraw Amount");
		System.out.println("D. See Previous Transaction");
		System.out.println("E. Calculate Interest");
		System.out.println("F. Exit");

		do {
			System.out.println("************************************");
			System.out.println("Enetr an option");
			System.out.println("************************************");
			option = scanner.next().charAt(0);
			System.out.println("\n");

			switch (option) {

			case 'A':
				System.out.println("_________________________________");
				System.out.println("Balance = " + balance);
				System.out.println("\n");
				break;

			case 'B':
				System.out.println("_________________________________");
				System.out.println("Enter an amount to deposite: ");
				int amount = scanner.nextInt();
				System.out.println("\n");
				break;

			case 'C':
				System.out.println("_________________________________");
				System.out.println("Enter an amount to withdraw: ");
				int amount2 = scanner.nextInt();
				withdraw(amount2);
				System.out.println("\n");
				break;

			case 'D':
				System.out.println("_________________________________");
				getpreviousTransaction();
				System.out.println("\n");
				break;

			case 'E':
				System.out.println("_________________________________");
				calculateInterest(balance);
				System.out.println("\n");
				break;

			case 'F':
				System.out.println("_________________________________");
				break;

			default:
				System.out.println("Entered Invalid Option! please enter again");
				break;
			}
		} while (option != 'F');
		System.out.println("Thank you for using our services");
		scanner.close();
	}
}
