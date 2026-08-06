package bankClass;
import java.time.LocalDate;
import java.util.Scanner;

public class savingsAcc extends account{

    private double balance;
    private int countSuccessfulWithdraw = 0;
    private LocalDate lastAnnualFeeDate;

    public savingsAcc(){
        this.lastAnnualFeeDate = LocalDate.now();
    }
    public double getBalance() {
        return balance;
    }
    public void  incrementSuccessfulWithdrawCount(){

       this.countSuccessfulWithdraw++;
}
    public int getCountSuccessfulWithdraw() {
        return countSuccessfulWithdraw;
    }
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

    public void annualFee(){
        LocalDate annualDate = LocalDate.now();
        double fee = 0;
        if (annualDate.isAfter(lastAnnualFeeDate.plusYears(1))
                || annualDate.isEqual(lastAnnualFeeDate.plusYears(1))) {
            switch (getCustomerTier()) {
                case standard:
                    fee =  0.01;
                    break;
                case silver:
                    fee = 0.0075;
                    break;
                case gold:
                    fee = 0.005;
                    break;
            }

        }
        balance -= balance * fee;
        lastAnnualFeeDate = annualDate;
    }
    public void setNewBalance(double balance) {
        this.balance = balance;
    }

}
