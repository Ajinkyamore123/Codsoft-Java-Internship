package ATMInterface;

import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankAccount account = new BankAccount(50000, 1234);

        System.out.println("================================");
        System.out.println("       WELCOME TO ATM");
        System.out.println("================================");

        System.out.print("Enter PIN : ");
        int pin = sc.nextInt();

        if (!account.verifyPin(pin)) {
            System.out.println("Incorrect PIN!");
            return;
        }

        int choice;

        do {

            System.out.println("\n============== MENU ==============");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Fast Cash");
            System.out.println("5. Mini Statement");
            System.out.println("6. Change PIN");
            System.out.println("7. Exit");
            System.out.println("==================================");

            System.out.print("Enter choice : ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    account.checkBalance();
                    break;

                case 2:
                    System.out.print("Enter amount : ₹");
                    double deposit = sc.nextDouble();
                    account.deposit(deposit);
                    break;

                case 3:
                    System.out.print("Enter amount : ₹");
                    double withdraw = sc.nextDouble();
                    account.withdraw(withdraw);
                    break;

                case 4:

                    System.out.println("\n----- FAST CASH -----");
                    System.out.println("1. ₹500");
                    System.out.println("2. ₹1000");
                    System.out.println("3. ₹2000");
                    System.out.println("4. ₹5000");

                    System.out.print("Choose option : ");
                    int option = sc.nextInt();

                    account.fastCash(option);
                    break;

                case 5:
                    account.miniStatement();
                    break;

                case 6:

                    System.out.print("Enter old PIN : ");
                    int oldPin = sc.nextInt();

                    System.out.print("Enter new PIN : ");
                    int newPin = sc.nextInt();

                    account.changePin(oldPin, newPin);

                    break;

                case 7:
                    System.out.println("\nThank you for using our ATM.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 7);

        sc.close();
    }
}