package bankClass;

import java.time.LocalDate;
import java.util.Scanner;

public class fixedDepositAcc extends account{
    private  double fixedBalance;
    private int  durationInMonths;
    private int monthsPassed=0;
    private LocalDate lastInterestRateDate;
    private double interestEarned  =0 ;
    private double monthlyRate = 0.01;

    public fixedDepositAcc() {
        this.lastInterestRateDate  = LocalDate.now();
    }

    public void setFixedBalance(Scanner sc , String prompt) {
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
        this.fixedBalance = balance;
    }
    public double getFixedBalance() {
        return fixedBalance;
    }
    public void setnewFixedBalance(double balance) {
        this.fixedBalance = balance;
    }

    public int getRemainingMonths() {
        return durationInMonths - monthsPassed;
    }

    public void setDurationInMonths(Scanner sc , String prompt) {
        int durationInMonths = 0 ;
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                durationInMonths = sc.nextInt();
                if (durationInMonths <= 0 ){
                    System.out.println("duration must be a positive number");
                    continue;
                }else {
                    break;
                }
            }else {
                System.out.println("invalid input");
                sc.next();
            }
        }
        this.durationInMonths = durationInMonths;
    }

    public int getMonthsPassed() {
        return monthsPassed;
    }



    public double getInterestEarned() {
        return interestEarned ;
    }

    public void calInterestRate() {
        LocalDate interestDate = LocalDate.now();
        while (!interestDate.isBefore(lastInterestRateDate.plusMonths(1)) && monthsPassed < durationInMonths) {
            double monthlyInterest = fixedBalance * monthlyRate;
            interestEarned  += monthlyInterest;
            monthsPassed++;
            lastInterestRateDate = lastInterestRateDate.plusMonths(1);

    }
        if (monthsPassed == durationInMonths) {
            fixedBalance += interestEarned;
            interestEarned = 0;
        }
    }
}
