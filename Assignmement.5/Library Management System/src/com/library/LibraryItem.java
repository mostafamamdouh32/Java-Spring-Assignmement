package com.library;

import java.time.LocalDate;

abstract class LibraryItem {
    public static final double ADMINISTRATIVE_CHARGE = 10.00;
    private static int totalNumberOfItems = 0;
    private int catalogueID;
    private String title;
    private Category category;
    private ItemStatus status;
    private int borrowerMemberId;
    private int numberRenewals;
    private LocalDate borrowDate;
    private LocalDate  returnDate;

    protected LibraryItem(int catalogueID, String title, Category category) {
        this.catalogueID = catalogueID;
        this.title = title;
        this.category = category;
        this.status = ItemStatus.AVAILABLE;
        this.borrowerMemberId = 0;
        this.numberRenewals = 0;
        totalNumberOfItems++;
    }
    public abstract int getLoanPeriod();
    public abstract double getDailyFine();

    public final boolean borrowItem(int memberId) {
        if (status != ItemStatus.AVAILABLE) {
            return false;
        }
        this.status = ItemStatus.ON_LOAN;
        this.borrowerMemberId = memberId;
        this.numberRenewals = 0;
        return true;
    }
    public final void resetAfterReturn() {
        this.status = ItemStatus.AVAILABLE;
        this.borrowerMemberId = 0;
        this.numberRenewals = 0;
    }
    public final boolean markLost() {
        if (status != ItemStatus.AVAILABLE) {
            return false;
        }
        this.status = ItemStatus.LOST;
        return true;
    }
    public double  calculateLoan(MembershipType membershipType) {
        if (status != ItemStatus.ON_LOAN) {

            System.out.println("This item is not currently on loan.");
            return 0.0;
        }
        long overdueDays = getReturnDate().toEpochDay() - getBorrowDate().toEpochDay();
        if (overdueDays <= 0) {
         return 0.0;
     }
     double fine = overdueDays * getDailyFine() * membershipType.getWaiverRate();
     return fine + ADMINISTRATIVE_CHARGE;
    }
    public double projectedFines(long overdueDays ,  MembershipType membershipType) {
        if (overdueDays <= 0) {
            return 0.0;
        }
        double fine = overdueDays * getDailyFine() * membershipType.getWaiverRate();
        return fine + ADMINISTRATIVE_CHARGE;
    }



    protected final void recordRenewal() {
        this.numberRenewals++;
    }

    public final void display() {
        System.out.println(
                "Catalogue ID : " + catalogueID + "\n" +
                "Category     : " + category + "\n" +
                "Title        : " + title + "\n" +
                "Status       : " + status + "\n" +
                "Borrower ID  : " + borrowerMemberId + "\n" +
                "Loan period  : " + getLoanPeriod() + " day(s)\n" +
                "Daily fine   : " + getDailyFine() + " EGP\n"
        );
    }
    public void setBorrowDate() {
        this.borrowDate = LocalDate.now();
    }
    public void setReturnDate() {
        this.returnDate = LocalDate.now();
    }

    public int getCatalogueID() { return catalogueID; }
    public String getTitle() { return title; }
    public Category getCategory() { return category; }
    public ItemStatus getStatus() { return status; }
    public int getBorrowerMemberId() { return borrowerMemberId; }
    public int getNumberRenewals() { return numberRenewals; }
    public static int getTotalNumberOfItems() { return totalNumberOfItems; }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public LocalDate getBorrowDate() {
        return borrowDate;
    }





}
