package com.library;

public class Book extends LibraryItem implements Renewable {

    private static final int LOAN_PERIOD_DAYS =14;
    private static final double DAILY_FINE = 5.00;
    private static final int RENEWAL_LIMIT = 2;

    private String author;
    private int pageCount;

    public Book(int catalogueID, String title, int pageCount, String author) {
        super(catalogueID, title, Category.BOOK);
        this.pageCount = pageCount;
        this.author = author;
    }

    @Override
    public int getLoanPeriod() {
        return LOAN_PERIOD_DAYS;
    }

    @Override
    public double getDailyFine() {
        return DAILY_FINE;
    }

    @Override
    public boolean renewTheLone() {
        if (getStatus() != ItemStatus.ON_LOAN) {
            return false;
        }
        if (getNumberRenewals() >= RENEWAL_LIMIT) {
            return false;
        }
        recordRenewal();
        return true;
    }

    @Override
    public int reportTheRenewalLimit() {
        return RENEWAL_LIMIT;
    }

    public String getAuthor() {
        return author;
    }

    public int getPageCount() {
        return pageCount;
    }
}
