package ATMInterface;

import java.util.ArrayList;

public class BankAccount {

    private double balance;
    private int pin;
    private double dailyLimit = 25000;
    private double withdrawnToday = 0;

    private ArrayList<String> history = new ArrayList<>();

    public BankAccount(double balance, int pin) {
        this.balance = balance;
        this.pin = pin;
    }

    public boolean verifyPin(int enteredPin) {
        return enteredPin == pin;
    }

    public void checkBalance() {
        System.out.printf("Current Balance : ₹%.2f%n", balance);
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        balance += amount;

        history.add("Deposited ₹" + amount);

        System.out.println("₹" + amount + " deposited successfully.");
    }

    public void withdraw(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient Balance.");
            return;
        }

        if (withdrawnToday + amount > dailyLimit) {
            System.out.println("Daily withdrawal limit exceeded.");
            return;
        }

        balance -= amount;
        withdrawnToday += amount;

        history.add("Withdrawn ₹" + amount);

        System.out.println("Please collect your cash.");
    }

    public void fastCash(int option) {

        switch(option) {

            case 1:
                withdraw(500);
                break;

            case 2:
                withdraw(1000);
                break;

            case 3:
                withdraw(2000);
                break;

            case 4:
                withdraw(5000);
                break;

            default:
                System.out.println("Invalid option.");
        }
    }

    public void changePin(int oldPin, int newPin) {

        if (oldPin != pin) {
            System.out.println("Incorrect old PIN.");
            return;
        }

        pin = newPin;
        System.out.println("PIN changed successfully.");
    }

    public void miniStatement() {

        System.out.println("\n------ MINI STATEMENT ------");

        if (history.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (String transaction : history) {
            System.out.println(transaction);
        }
    }
}