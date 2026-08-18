package com.library;

import java.util.Scanner;

public class Member {
    public static final int MAX_ITEMS_HELD = 3;
    public static final double MAX_BALANCE_OWED = 100.00;
    private String name;
    private int memberID;
    private MembershipType membershipType;
    private double balanceOwed;
    private int itemsHeld;


    public Member() {
        this.balanceOwed = 0.0;
        this.itemsHeld = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(Scanner sc , String prompt) {
        String  name;
        while (true) {
            System.out.print(prompt);
            name = sc.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Invalid input: name cannot be empty.");
                continue;
            }
            if (!name.matches("[a-zA-Z ]+")) {
                System.out.println("Invalid input: name must contain letters only.");
                continue;
            }
            break;
        }

        this.name = name;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(Scanner sc ,String prompt) {
        while (true) {
            System.out.print(prompt);
            String MembershipType = sc.nextLine().trim().toLowerCase();
            switch (MembershipType){
                case "student":
                    this.membershipType=com.library.MembershipType.STUDENT;
                    return;
                case "public":
                    this.membershipType=com.library.MembershipType.PUBLIC;
                    return;
                case "staff":
                    this.membershipType=com.library.MembershipType.STAFF;
                    return;
                default:
                    System.out.println("Invalid input: Member ship type must be student or public or staff.");
            }
        }
    }

    public double getBalanceOwed() {
        return balanceOwed;
    }
    public int getItemsHeld() {
        return itemsHeld;
    }
    public void updateBalanceOwed(double balanceOwed) {
        this.balanceOwed = balanceOwed;
    }


    public void reportAllInfo(){
        System.out.println(
            "Member ID: " + memberID + "\n"+
            "Member Name : "+ name + "\n"+
            "Member ship type : " +   membershipType+ "\n"+
            "Items held : " + itemsHeld  + "\n"+
            "Balance owed : " + balanceOwed
        );
    }
    public boolean canBorrow() {
        return itemsHeld  < MAX_ITEMS_HELD && balanceOwed <= MAX_BALANCE_OWED;
    }
    public boolean receiveFine(double fine) {
        if (fine <= 0) {
            return false;
        }
        balanceOwed += fine;
        return true;
    }
    public boolean makePayment(double amount ){
        if (amount <= 0 || amount > balanceOwed) {
            return false;
        }
        balanceOwed -= amount;
        return true;
    }

    public boolean borrow(){
        if (!canBorrow()) {
            System.out.println("can holds fewer than 3 ");
            return false;
        }
        itemsHeld++;
        return true;
    }
    public void returnItem(){
        if (itemsHeld > 0) {
            itemsHeld--;
        }
        }


}
