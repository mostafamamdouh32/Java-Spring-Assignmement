package com.library;

public enum MembershipType {
    STUDENT(0.75),
    STAFF(0.90),
    PUBLIC(1.00);

    private final double waiverRate;
    MembershipType(double waiverRate) {
        this.waiverRate = waiverRate;
    }
    public double getWaiverRate() {
        return waiverRate;
    }
}
