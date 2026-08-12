// Declaring class and variables
public class BankAccount {
    private String AccountNumber;
    private String AccountHolderName;
    private String AccountType;
    private double Balance;
    private String PIN;

    // Constructor with a default PIN
    public BankAccount(String AccountNumber, String AccountHolderName, String AccountType, double Balance) {
        this(AccountNumber, AccountHolderName, AccountType, Balance, "1234");
    }

    // Constructor that allows a custom PIN
    public BankAccount(String AccountNumber, String AccountHolderName, String AccountType, double Balance, String PIN) {
        this.AccountNumber = AccountNumber;
        this.AccountHolderName = AccountHolderName;
        this.AccountType = AccountType;
        this.Balance = Balance;
        this.PIN = PIN;
    }

    // Getters for the account details
    public String getAccountNumber() {
        return AccountNumber;
    }

    public String getAccountHolderName() {
        return AccountHolderName;
    }

    public double getBalance() {
        return Balance;
    }

    public void receiveTransfer(double amount) {
        if (amount > 0) {
            Balance += amount;
        }
    }

    public boolean validatePIN(String enteredPIN) {
        return this.PIN != null && this.PIN.equals(enteredPIN);
    }
    
    // Method to check the balance of the account
    public void CheckBalance(String AccountNumber) {
        if (this.AccountNumber.equals(AccountNumber)) {
            System.out.println("Current Balance: " + Balance);
        } else {
            System.out.println("Account not found.");
        }
    }
    
    // Method to deposit funds into the account
    public void deposit(double amount) {
        if (amount > 0) {
            Balance += amount;
            System.out.println("Deposited: " + amount);
            System.out.println("Current Balance: " + Balance);
        } else {
            System.out.println("Invalid input.");
        }
    }
    
    // Method to withdraw funds from the account
    public void withdraw(double amount) {
        if (amount > 0 && amount <= Balance) {
            Balance -= amount;
            System.out.println("Withdrew: " + amount);
            System.out.println("Current Balance: " + Balance);
        } else if (amount > Balance) {
            System.out.println("Insufficient funds.");
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }
    
    // Method to transfer funds to another account
    public void transfer(BankAccount recipient, double amount) {
        if (recipient == null) {
            System.out.println("Recipient account not found.");
        } else if (recipient == this) {
            System.out.println("You cannot transfer to the same account.");
        } else if (amount > 0 && amount <= Balance) {
            Balance -= amount;
            recipient.receiveTransfer(amount);
            System.out.println("Transferred: " + amount + " to " + recipient.getAccountHolderName());
            System.out.println("Current Balance: " + Balance);
        } else if (amount > Balance) {
            System.out.println("Insufficient funds for transfer.");
        } else {
            System.out.println("Transfer amount must be positive.");
        }
    }
    
    // Method to display account details
    public void displayAccountDetails(String AccountNumber) {
        if (this.AccountNumber.equals(AccountNumber)) {
            System.out.println("Account Number: " + AccountNumber);
            System.out.println("Account Holder Name: " + AccountHolderName);
            System.out.println("Account Type: " + AccountType);
            System.out.println("Balance: " + Balance);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Method to change the PIN of the account
    public void changePIN(String newPIN) {
        if (newPIN != null && newPIN.length() == 4 && newPIN.matches("\\d+")) {
            this.PIN = newPIN;
            System.out.println("PIN changed successfully to: " + newPIN);
        } else {
            System.out.println("Invalid PIN. Please enter a 4-digit numeric PIN.");
        }
    }
}