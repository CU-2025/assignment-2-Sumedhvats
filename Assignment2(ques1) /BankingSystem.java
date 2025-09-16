import java.util.Scanner;

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

public class BankingSystem {
    private double balance;
    
    public BankingSystem(double initialBalance) {
        this.balance = initialBalance;
    }
    
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance. Available balance: " + balance);
        }
        balance -= amount;
        System.out.println("Withdrawal successful. New balance: " + balance);
    }
    
    public double calculateInterest(double principal, double rate, int time) throws ArithmeticException {
        if (rate == 0) {
            throw new ArithmeticException("Interest rate cannot be zero");
        }
        return (principal * rate * time) / 100;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.print("Enter initial account balance: ");
            double initialBalance = sc.nextDouble();
            BankingSystem bank = new BankingSystem(initialBalance);
            
            System.out.print("Enter withdrawal amount: ");
            double withdrawAmount = sc.nextDouble();
            
            System.out.print("Enter interest rate: ");
            double interestRate = sc.nextDouble();
            
            System.out.print("Enter time period (years): ");
            int timePeriod = sc.nextInt();
            
            try {
                bank.withdraw(withdrawAmount);
            } catch (InsufficientBalanceException e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            try {
                double interest = bank.calculateInterest(bank.getBalance(), interestRate, timePeriod);
                System.out.println("Calculated interest: " + interest);
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            System.out.println("Final account balance: " + bank.getBalance());
            
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter valid numbers.");
        } finally {
            sc.close();
            System.out.println("Program execution completed.");
        }
    }
}