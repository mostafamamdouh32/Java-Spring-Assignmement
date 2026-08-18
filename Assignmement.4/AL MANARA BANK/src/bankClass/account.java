package bankClass;

import java.time.LocalDate;
import java.util.Scanner;

public class account {
private int accountNumber;
private int customerId;
private customerTier customerTier;
private String accountStatus;
private branch branch;
private LocalDate accountStartDate ;

    public account() {
        this.accountStartDate = LocalDate.now();
    }

    public void setAccountNumber() {
        int max = 999999;
        int min = 100000;
        int accountNumber = (int) (Math.random() * (max - min + 1)) + min;
        this.accountNumber = accountNumber;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCustomerTier(customerTier customerTier) {
        this.customerTier = customerTier;
    }

    public void setAccountStatus() {
        this.accountStatus = "active";
    }
    public void closedAnAccount(){
        this.accountStatus = "closed";
    }
    public void changeAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
    public void setBranch(Scanner sc , String prompt) {
        while (true) {
            System.out.print(prompt);
            String customerTier = sc.nextLine().trim().toLowerCase();
            switch (customerTier){
                case "branch1":
                    this.branch = bankClass.branch.branch1;
                    return;
                case "branch2":
                    this.branch = bankClass.branch.branch2;
                    return;
                case "branch3":
                    this.branch = bankClass.branch.branch3;
                    return;
                default:
                    System.out.println("Invalid input: Branch must be branch1 or branch2 or branch3.");
            }
        }
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public customerTier getCustomerTier() {
        return customerTier;
    }



    public branch getBranch() {
        return branch;
    }

    public LocalDate getAccountStartDate() {
        return accountStartDate;
    }


}

