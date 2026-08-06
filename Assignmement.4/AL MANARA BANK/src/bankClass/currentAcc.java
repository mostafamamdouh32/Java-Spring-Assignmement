package bankClass;

import java.util.Scanner;

public class currentAcc extends account{
private double balance;
    public currentAcc() {}


    public void setBalance(Scanner sc , String prompt) {
        double balance = 0.0;
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextDouble()) {
                balance = sc.nextDouble();
                if (balance <= 0 ){
                    System.out.println("Balance must be a positive number");
                    continue;
                }else {
                    break;
                }
            }else {
                System.out.println("Invalid Input");
                sc.next();
            }

        }
        this.balance = balance;
    }
    public double getBalance() {
        return balance;
    }
    public void setNewBalance(double balance) {
        this.balance = balance;
    }


}
