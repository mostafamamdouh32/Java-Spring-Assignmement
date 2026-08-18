package com.library;

import java.io.Serializable;

public class Magazine extends LibraryItem implements Renewable {
    private int issueNumber;
    private static final int LOAN_PERIOD_DAYS = 7;
    private static final double DAILY_FINE = 3.00;
    private static final int RENEWAL_LIMIT = 1;

    public Magazine(int catalogueID, String title,int issueNumber) {
        super(catalogueID, title,Category.MAGAZINE);
        this.issueNumber = issueNumber;
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


    public int getIssueNumber() {
        return issueNumber;
    }
}
