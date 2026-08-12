// Importing the scanner
import java.util.Scanner;

// Main class to run the ATM system
public class Main {
    public static void main(String[] args) {
        
        // Creating a scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        
        // Creating a sample bank account for demonstration
        BankAccount account = new BankAccount("12345", "John Doe", "Savings", 1000.0, "1234");
        BankAccount recipientAccount = new BankAccount("67890", "Jane Doe", "Checking", 500.0, "5678");
        int choice;

        int attempts = 0;
        String enteredPIN = "";

        while (attempts < 3) {
            System.out.print("Enter your PIN: ");
            enteredPIN = scanner.next();

            if (account.validatePIN(enteredPIN)) {
                break;
            }

            attempts++;

            if (enteredPIN.length() != 4 || !enteredPIN.matches("\\d+")) {
                System.out.println("Invalid PIN format. Please enter a 4-digit numeric PIN.");
            } else {
                System.out.println("Incorrect PIN. Please try again.");
            }

            if (attempts >= 3) {
                System.out.println("Too many incorrect attempts. Exiting.");
                scanner.close();
                return;
            }
        }

        if (!account.validatePIN(enteredPIN)) {
            scanner.close();
            return;
        }

        System.out.println("PIN verified. Access granted.");
        
        // Main loop for the ATM menu
        while (true) {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Display Account Details");
            System.out.println("6. Change PIN");            
            System.out.println("7. Exit");
            System.out.println("======================");
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            
            // Switch case to handle user choices
            switch (choice) {
                case 1:
                    account.CheckBalance(account.getAccountNumber());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient account number: ");
                    String recipientAccountNumber = scanner.next();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();

                    BankAccount transferRecipient = recipientAccountNumber.equals(recipientAccount.getAccountNumber())
                            ? recipientAccount
                            : null;
                    account.transfer(transferRecipient, transferAmount);
                    break;
                case 5:
                    account.displayAccountDetails(account.getAccountNumber());
                    break;
                case 6:
                    System.out.print("Enter new PIN: ");
                    String newPIN = scanner.next();
                    account.changePIN(newPIN);
                    break;
                case 7:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}