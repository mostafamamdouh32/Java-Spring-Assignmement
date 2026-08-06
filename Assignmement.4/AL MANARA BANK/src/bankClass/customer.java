package bankClass;

import java.util.Scanner;

public class customer {
    private int customerId ;
    private String customerName ;
    private String nationalId ;
    private String phoneNumber ;
    private customerTier customerTier;

    public customer() {

    }

    public void setCustomerId() {
        int max = 999999;
        int min = 100000;
        int customerId = (int) (Math.random() * (max - min + 1)) + min;

        this.customerId = customerId;
    }

    public void setCustomerName(Scanner sc , String prompt) {
        String  customerName;
        while (true) {
            System.out.print(prompt);
            customerName = sc.nextLine().trim();
            if (customerName.isEmpty()) {
                System.out.println("Invalid input: name cannot be empty.");
                continue;
            }
            if (!customerName.matches("[a-zA-Z ]+")) {
                System.out.println("Invalid input: name must contain letters only.");
                continue;
            }
            break;
        }
        this.customerName = customerName;
    }

    public void setNationalId(Scanner sc , String prompt) {
        //15 digits
        String nationalId;
        while (true) {
            System.out.print(prompt);
            nationalId = sc.nextLine().trim();
            if (nationalId.length() != 15 || !nationalId.matches("[0-9]+")) {
                System.out.println("Invalid input: nationalId must contain at least 15 characters and numbers only.");
                continue;
            }
            break;
        }
        this.nationalId = nationalId;
    }

    public void setCustomerTier(Scanner sc ,String prompt) {
        while (true) {
            System.out.print(prompt);
            String customerTier = sc.nextLine().trim().toLowerCase();
        switch (customerTier){
           case "standard":
               this.customerTier=bankClass.customerTier.standard;
               return;
           case "silver":
               this.customerTier=bankClass.customerTier.silver;
               return;
           case "gold":
               this.customerTier=bankClass.customerTier.gold;
               return;
           default:
               System.out.println("Invalid input: customer tier must be standard or silver or gold.");
}
        }

    }

    public void setPhoneNumber(Scanner sc ,  String prompt) {
        /*
         010
         011
         012
         015
         11 digits
         */
        System.out.print("Do you want to enter a phone number? (yes/no): ");
        String option = sc.nextLine().trim().toLowerCase();
        switch (option) {
            case "no":
                this.phoneNumber = "";
                System.out.println("Okay.");
                break;
            case "yes":
                while (true) {
                    System.out.print(prompt);
                    String phoneNumber = sc.nextLine().trim();
                    if (!phoneNumber.matches("^01[0125]\\d{8}$")) {
                        System.out.println("Invalid phone number.");
                        continue;
                    }

                    this.phoneNumber = phoneNumber;
                    break;
                }
                break;
            default:
                System.out.println("Invalid input.");
        }
    }
    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public customerTier getCustomerTier() {
        return customerTier;
    }
}
