package bankClass;

public class customerAccount {
    private int accountNumber;
    private int customerId;
    private String accountType;
    private branch branchName;
    private double balance;
    private String status;
    private int transactionCount = 0;




    public customerAccount(int accountNumber, int customerId, String accountType, branch branchName , double balance, String status) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.accountType = accountType;
        this.branchName = branchName;
        this.balance = balance;
        this.status = status;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
    public void closedAnAccount(){
        this.status = "closed";
    }
    public int getCustomerId() {
        return customerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public branch getBranchName() {
        return branchName;
    }

    public double getBalance() {
        return balance;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public String getStatus() {
        return status;
    }

    public void setNewBalance(double balance) {
        this.balance = balance;
    }
    public void incrementTran(){
        transactionCount++;
    }
}
