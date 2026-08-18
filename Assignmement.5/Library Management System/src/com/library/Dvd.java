package com.library;

import java.io.Serializable;

public class Dvd extends LibraryItem  {
    private static final int LOAN_PERIOD_DAYS =14;
    private static final double DAILY_FINE = 5.00;

    private int runTime;

    public Dvd(int catalogueID, String title, int runTime) {
        super(catalogueID, title, Category.DVD);
        this.runTime = runTime;
    }

    @Override
    public int getLoanPeriod() {
        return LOAN_PERIOD_DAYS;
    }

    @Override
    public double getDailyFine() {
        return DAILY_FINE;
    }

    public void setRunTime(int runTime) { this.runTime = runTime; }

}
