package com.library;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    Scanner sc =  new Scanner(System.in);
    private static final int MAX_MEMBERS = 100;
    private static final int MAX_ITEMS = 200;

    ArrayList<Member> members = new ArrayList<>();
    ArrayList<LibraryItem> items = new ArrayList<>();
    int mamberID=0;
   int catalogueID=0;

    public LibraryItem  checkItem(int itemID){
        for(LibraryItem item:items){
            if (item.getCatalogueID()==itemID){
                return item;
            }
        }
        return null;

    }
    public Member checkMemberID(int memberID){
        for (Member member : members) {
            if (member.getMemberID() == memberID) {
                return member;
            }
        }
        return null;
    }
    //  1 View catalogue No input. Display every item: ID, category, title, status, borrower member ID, loan period, and one-day fine.
    public void viewCatalogue(){
        if (items.isEmpty()) {
            System.out.println("Catalogue is empty.");
            return;
        }
        for (LibraryItem item : items){
            item.display();
        }
    }
    //--------------------------------------------------------------------------------------------------------------------------------
    //2 Register member Ask for name, membership ID, and category. Reject duplicate IDs or a full register. Start with a zero balance.
    public void registerMember(){
        if (members.size() >= MAX_MEMBERS) {
            System.out.println("Registration is full");
            return;
        }
        Member member = new Member();
        member.setMemberID(mamberID);
        member.setName(sc,"Enter Member Name : ");
        member.setMembershipType(sc,"Enter Membership Type : ");
        members.add(member);
        System.out.println("Member registered with ID: " + mamberID);
        mamberID++;

    }
    //--------------------------------------------------------------------------------------------------------------------------------
    //3 Borrow item Ask for item ID and membership ID. Reject missing IDs, an unavailable item, or a member who holds 3 items or owes more then.
     public void borrowItem(){
         System.out.print("Item id : ");
         if (!sc.hasNextInt()){
             System.out.println("Invalid Item Id");
             return;
         }
         int itemID = sc.nextInt();
         LibraryItem item = checkItem(itemID);
         if (item == null) {
             System.out.println("Item not found.");
             return;
         }
         System.out.print("Enter Member id : ");
         if (!sc.hasNextInt()){
             System.out.println("Invalid Member Id");
             return;
         }
         int memberID = sc.nextInt();
         Member member = checkMemberID(memberID);

         if (member == null) {
             System.out.println("Member not found.");
             return;
         }
         if (item.getStatus() != ItemStatus.AVAILABLE) {
             System.out.println("Item is currently unavailable (status: " + item.getStatus() + ")");
             return;
         }
         if (!member.canBorrow()) {
             System.out.println("Member cannot borrow (holds 3 items or owes more than 100 EGP).");
             return;
         }
         item.borrowItem(memberID);
         item.setBorrowDate();
         member.borrow();
         System.out.println("Item borrowed successfully.");

     }
    //--------------------------------------------------------------------------------------------------------------------------------
    //4 Return item Ask for item ID and overdue days. Zero means on time. Reject negative days. Calculate the fine, charge it, display the break
    public void returnItem(){
        System.out.print("Item id : ");
        if (!sc.hasNextInt()){
            System.out.println("Invalid Item Id");
            return;
        }
        int itemID = sc.nextInt();
        LibraryItem item = checkItem(itemID);

        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        if (item.getStatus() != ItemStatus.ON_LOAN) {
            System.out.println("This item is not currently on loan.");
            return;
        }
        item.setReturnDate();
        int borrowerId = item.getBorrowerMemberId();
        Member member = checkMemberID(borrowerId);

        double fine= item.calculateLoan(member.getMembershipType());
        if (fine > 0) {
            member.receiveFine(fine);
            member.returnItem();
            item.resetAfterReturn();
            System.out.println("Returned item successfully.");
        }
    }
    //--------------------------------------------------------------------------------------------------------------------------------
    //5 Renew loan Ask for an item ID. Check whether its type can be renewed and whether its renewal limit has been reached.
    public void renewLoan() {
        System.out.print("Item id : ");
        if (!sc.hasNextInt()){
            System.out.println("Invalid Item Id");
            return;
        }
        int itemID = sc.nextInt();
        LibraryItem item = checkItem(itemID);

        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        if (!(item instanceof Renewable)) {
            System.out.println("This item type cannot be renewed.");
            return;
        }
        Renewable renewable = (Renewable) item;
        boolean success = renewable.renewTheLone();
        if (success) {
            System.out.println("Renewed successfully. (" + item.getNumberRenewals()
                    + "/" + renewable.reportTheRenewalLimit() + " renewals used)");
        }else {
            System.out.println("Cannot renew: renewal limit (" + renewable.reportTheRenewalLimit() + ") reached.");
        }
    }
        //--------------------------------------------------------------------------------------------------------------------------------
    //6 Search item by ID Display that item's details, or report that it was not found.
    public void searchItem(){
        System.out.print("Item id : ");
        if (!sc.hasNextInt()){
            System.out.println("Invalid Item Id");
            return;
        }
        int itemID = sc.nextInt();
        LibraryItem item = checkItem(itemID);

        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
       item.display();
    }
    //--------------------------------------------------------------------------------------------------------------------------------
    //7 View items by status Ask for AVAILABLE, ON_LOAN, or LOST and display matching items, or report that there are none.
    public void viewItemsByStatus(){
        System.out.print("Item status (AVAILABLE / ON_LOAN / LOST): ");
        String input = sc.nextLine().trim().toUpperCase();
        ItemStatus targetStatus;
        switch (input){
            case "AVAILABLE":
                targetStatus = ItemStatus.AVAILABLE;
                break;
            case "ON_LOAN":
                targetStatus = ItemStatus.ON_LOAN;
                break;
            case "LOST":
                targetStatus = ItemStatus.LOST;
                break;
            default:
                System.out.println("Invalid Status");
                return;
        }

        boolean found = false;
        for (LibraryItem item : items){
            if (item.getStatus() == targetStatus){
                item.display();
                found = true;
            }
        }
        if (!found){
            System.out.println("No items found with status: " + targetStatus);
        }
    }
    //--------------------------------------------------------------------------------------------------------------------------------
    //8 Pay outstanding fines Ask for membership ID and payment amount. Reject non-positive payments and payments greater than the balance.
    public void payOutStandingFines(){
        System.out.print("MemberShip ID : ");
        if (!sc.hasNextInt()){
            System.out.println("Invalid MemberShip Id");
            return;
        }
        int membershipID = sc.nextInt();
        Member member = checkMemberID(membershipID);
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }
        System.out.print("Enter payment amount: ");
        if (!sc.hasNextDouble()){
            System.out.println("Invalid Payment Amount");
            return;
        }
        double amount = sc.nextDouble();
        sc.nextLine();
        if (member.makePayment(amount)) {
            System.out.println("Payment successful. Remaining balance: " + member.getBalanceOwed());
        } else {
            System.out.println("Invalid payment amount (must be positive and not exceed the balance owed).");
        }
    }
    //--------------------------------------------------------------------------------------------------------------------------------
    //9 View all members Display name, ID, category, items held, and balance owed.
    public void viewMembers(){
        if (members.isEmpty()){
            System.out.println("No members registered");
            return;
        }

            for (Member member:members){
                member.reportAllInfo();
            }

    }
    //--------------------------------------------------------------------------------------------------------------------------------
    //10 Library report Display catalogue size, items ever added, items on loan, loan rate, total outstanding, and projected fines for a 5-day overdue
    public void libraryReport(){
        /*
        catalogue size - > items.size()
        items ever added -> items.size()
        items on loan ->the number of items currently borrowed
        loan rate ->  the percentage of catalogue items currently on loan
        total outstanding-> the total balance owed by all members
        projected fines for a 5-day overdue ->  if every item currently on loan is returned a given number of days late, calculate the fines before
        membership waivers.
         */
        if (items.isEmpty()) {
            System.out.println("No items found.");
            return;
        }
        int catalogueSize = items.size();
        int itemsEverAdded = LibraryItem.getTotalNumberOfItems();
        int itemsOnLoan = 0;
        double totalOutstanding = 0.0;
        double projectedFines = 0.0;
        final int PROJECTED_OVERDUE_DAYS = 5;
        for (Member member : members){
            totalOutstanding += member.getBalanceOwed();
        }
        for (LibraryItem item : items){
            if (item.getStatus() == ItemStatus.ON_LOAN){
                itemsOnLoan++;
                projectedFines += item.projectedFines(PROJECTED_OVERDUE_DAYS, MembershipType.PUBLIC);

            }
        }
        double loanRate = ((double) itemsOnLoan / items.size()) * 100;
        System.out.println(

                        "Catalogue size : " +  catalogueSize + "\n" +
                        "Items ever added : " +  itemsEverAdded + "\n" +
                        "Items on loan : " + itemsOnLoan + "\n" +
                        "Loan rate : " + loanRate + "\n" +
                        "Total outstanding : " + totalOutstanding + "\n" +
                        "Projected fines (5 days overdue, before waivers) : " +  projectedFines
        );
    }
    //--------------------------------------------------------------------------------------------------------------------------------
    //11 Mark item as lost Ask for an item ID. The item must currently be AVAILABLE. Change its status to LOST. A LOST item cannot be borrowed.
    public void markItemAsLost(){
        System.out.print("Item id : ");
        if (!sc.hasNextInt()){
            System.out.println("Invalid Item Id");
            return;
        }
        int itemID = sc.nextInt();
        LibraryItem item = checkItem(itemID);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        if (item.markLost()) {
            System.out.println("Item marked as lost successfully.");
        } else {
            System.out.println("Item cannot be marked lost (status: " + item.getStatus() + ")");
        }

    }
    //--------------------------------------------------------------------------------------------------------------------------------
    //register item
    public void registerItem() {
        if (items.size() >= MAX_ITEMS) {
            System.out.println("Catalogue is full.");
            return;
        }
        System.out.print("Category (book / dvd / magazine): ");
        String category = sc.nextLine().trim().toLowerCase();

        System.out.print("Title: ");
        String title = sc.nextLine().trim();

        LibraryItem newItem;
        switch (category) {
            case "book": {
                System.out.print("Author: ");
                String author = sc.nextLine().trim();
                System.out.print("Page count: ");
                if(!sc.hasNextInt()){
                    System.out.println("Invalid Page count");
                    sc.nextLine();
                    return;
                }
                int pageCount =  sc.nextInt();
                newItem = new Book(catalogueID, title, pageCount, author);
                break;
            }
            case "dvd": {
                System.out.print("Run time : ");
                if(!sc.hasNextInt()){
                    System.out.println("Invalid run time");
                    sc.nextLine();
                    return;
                }
                int runTime = sc.nextInt();
                newItem = new Dvd(catalogueID, title, runTime);
                break;
            }
            case "magazine": {
                System.out.print("Issue number : ");
                if(!sc.hasNextInt()){
                    System.out.println("Invalid issue number");
                    sc.nextLine();
                    return;
                }
                int issueNumber = sc.nextInt();
                newItem = new Magazine(catalogueID, title, issueNumber);
                break;
            }
            default:
                System.out.println("Invalid category.");
                return;
        }
        items.add(newItem);
        System.out.println("Item registered with catalogue ID: " + catalogueID);
        catalogueID++;
    }
    //--------------------------------------------------------------------------------------------------------------------------------

}
