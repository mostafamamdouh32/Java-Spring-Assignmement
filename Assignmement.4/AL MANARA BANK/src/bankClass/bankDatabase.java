package bankClass;
import java.util.Scanner;

public class bankDatabase {
    int customerIdCapacity = 10;
    int currentAccCapacity = 10;
    int fixedDepositAccountCapacity = 10;
    int savingAccountCapacity = 10;
    int customerAccountCapacity = 30;
    int customerIdCount = 0;
    int currentAccCount = 0;
    int fixedDepositAccountCount = 0;
    int savingAccountCount = 0;
    int customerAccountCount = 0;

    double overdraftLimit= 1000.0;
    double minTransactionAmount = 10.0;
    customer[] customers = new customer[customerIdCapacity];
    currentAcc[] currentAccs = new currentAcc[currentAccCapacity];
    savingsAcc[] savingAccs = new savingsAcc[savingAccountCapacity];
    fixedDepositAcc[] fixedDepositAccs = new fixedDepositAcc[fixedDepositAccountCapacity];
    customerAccount[] customerAccounts = new customerAccount[customerAccountCapacity];

    public void addCustomer(Scanner sc) {
        if (customerIdCount >= customers.length) {
            System.out.println("Customer full capacity");
            return;
        }


            customers[customerIdCount] = new customer();
            customers[customerIdCount].setCustomerId();
            customers[customerIdCount].setCustomerName(sc, "Enter Customer Name: ");
            customers[customerIdCount].setCustomerTier(sc, "Enter Customer Tier (standard/silver/gold): ");
            customers[customerIdCount].setNationalId(sc, "Enter Customer National ID: ");
        for (int i = 0; i < customerIdCount; i++) {
            if (customers[i].getNationalId().equals(customers[customerIdCount].getNationalId())) {
                System.out.println("A customer with this National ID already exists. Registration cancelled.");
                return;
            }
        }
            customers[customerIdCount].setPhoneNumber(sc, "Enter Customer Phone Number: ");
            System.out.println("Customer registered successfully. Customer ID: " + customers[customerIdCount].getCustomerId());
            customerIdCount++;
    }
    public void addAccount(Scanner sc) {

        System.out.print("Enter Customer ID: ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid Customer ID");
            sc.nextLine();
            return;
        }
        int customerId = sc.nextInt();
        sc.nextLine();
        Boolean customerExists = false;
        for (int i = 0; i < customerIdCount; i++) {
            if (customers[i].getCustomerId() == customerId) {
                customerExists = true;
                break;
            }
        }
        if (!customerExists) {
            System.out.println("customer does not exist");
            return;
        }


                System.out.print("Enter Account type (current/fixed/saving): ");
                String accountType = sc.nextLine().trim().toLowerCase();
                switch (accountType) {
                    case "current":
                        if (currentAccCount >= currentAccCapacity) {
                            System.out.println("Current account capacity is full");
                            return;
                        }
                        if (customerAccountCount >= customerAccountCapacity) {
                            System.out.println("Account storage is full");
                            return;
                        }
                        currentAccs[currentAccCount] = new currentAcc();
                        currentAccs[currentAccCount].setAccountNumber();
                        currentAccs[currentAccCount].setCustomerId(customerId);
                        currentAccs[currentAccCount].setAccountStatus();
                        currentAccs[currentAccCount].setBalance(sc, "Enter Account Balance: ");
                        sc.nextLine();
                        currentAccs[currentAccCount].setBranch(sc, "Enter A branch (branch1/branch2/branch3): ");
                        customerAccounts[customerAccountCount] = new customerAccount(
                                currentAccs[currentAccCount].getAccountNumber(),
                                currentAccs[currentAccCount].getCustomerId(),
                                "current",
                                currentAccs[currentAccCount].getBranch(),
                                currentAccs[currentAccCount].getBalance(),
                                currentAccs[currentAccCount].getAccountStatus()
                        );
                        currentAccCount++;
                        customerAccountCount++;
                        System.out.println("Current account created successfully. Account Number: "
                                + currentAccs[currentAccCount - 1].getAccountNumber());                        return;
                    case "fixed":
                        if (fixedDepositAccountCount >= fixedDepositAccountCapacity) {
                            System.out.println("Fixed account capacity is full");
                            return;
                        }
                        if (customerAccountCount >= customerAccountCapacity) {
                            System.out.println("Account storage is full");
                            return;
                        }
                        fixedDepositAccs[fixedDepositAccountCount] = new fixedDepositAcc();
                        fixedDepositAccs[fixedDepositAccountCount].setAccountNumber();
                        fixedDepositAccs[fixedDepositAccountCount].setCustomerId(customerId);
                        fixedDepositAccs[fixedDepositAccountCount].setAccountStatus();
                        fixedDepositAccs[fixedDepositAccountCount].setFixedBalance(sc, "Enter Fixed Balance: ");
                        sc.nextLine();
                        fixedDepositAccs[fixedDepositAccountCount].setDurationInMonths(sc, "Enter Duration Months: ");
                        sc.nextLine();
                        fixedDepositAccs[fixedDepositAccountCount].setBranch(sc, "Enter A branch (branch1/branch2/branch3): ");
                        customerAccounts[customerAccountCount] = new customerAccount(
                                fixedDepositAccs[fixedDepositAccountCount].getAccountNumber(),
                                fixedDepositAccs[fixedDepositAccountCount].getCustomerId(),
                                "fixed",
                                fixedDepositAccs[fixedDepositAccountCount].getBranch(),
                                fixedDepositAccs[fixedDepositAccountCount].getFixedBalance(),
                                fixedDepositAccs[fixedDepositAccountCount].getAccountStatus()
                        );
                        fixedDepositAccountCount++;
                        customerAccountCount++;
                        System.out.println("Fixed account created successfully. Account Number: "
                                + fixedDepositAccs[fixedDepositAccountCount - 1].getAccountNumber());                        return;
                    case "saving":
                        if (savingAccountCount >= savingAccountCapacity) {
                            System.out.println("Saving account capacity is full");
                            return;
                        }
                        if (customerAccountCount >= customerAccountCapacity) {
                            System.out.println("Account storage is full");
                            return;
                        }
                        savingAccs[savingAccountCount] = new savingsAcc();
                        savingAccs[savingAccountCount].setAccountNumber();
                        savingAccs[savingAccountCount].setCustomerId(customerId);
                        savingAccs[savingAccountCount].setAccountStatus();
                        savingAccs[savingAccountCount].setBalance(sc, "Enter Account Balance: ");
                        sc.nextLine();
                        savingAccs[savingAccountCount].setBranch(sc, "Enter A branch (branch1/branch2/branch3): ");
                        customerAccounts[customerAccountCount] = new customerAccount(
                                savingAccs[savingAccountCount].getAccountNumber(),
                                savingAccs[savingAccountCount].getCustomerId(),
                                "saving",
                                savingAccs[savingAccountCount].getBranch(),
                                savingAccs[savingAccountCount].getBalance(),
                                savingAccs[savingAccountCount].getAccountStatus()
                        );
                        savingAccountCount++;
                        customerAccountCount++;
                        System.out.println("Saving account created successfully. Account Number: "
                                + savingAccs[savingAccountCount - 1].getAccountNumber());
                        return;

                                default:
                        System.out.println("Invalid Account Type");

                }

    }
    public void withdrawMoney(Scanner sc){
        System.out.print("Enter Account Number: ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid Account Number");
            sc.nextLine();
            return;
        }
        int  accountNumber = sc.nextInt();
        String accountType = "";
        int idCustomerAccountNumber = -1;
        boolean foundAcc = false;
        for(int i = 0; i < customerAccountCount; i++) {
      if(accountNumber == customerAccounts[i].getAccountNumber()){
          foundAcc = true;
          accountType = customerAccounts[i].getAccountType();
          idCustomerAccountNumber = i;
          break;
      }
        }
        if(!foundAcc){
            System.out.println("Invalid Account Number");
            return;
        }
        String status = customerAccounts[idCustomerAccountNumber].getStatus();
        if (status.equalsIgnoreCase("closed") || status.equalsIgnoreCase("frozen")) {
            System.out.println("Transaction rejected: account is " + status);
            return;
        }
            System.out.print("Enter Amount to be withdraw: ");
            if (!sc.hasNextDouble()) {
                System.out.println("Invalid Amount to be withdraw");
                sc.nextLine();
                return;
            }
                double withdrawAmount = sc.nextDouble();
            if (withdrawAmount <= 0) {
                System.out.println("Amount must be positive");
                return;
            }
            if (withdrawAmount < minTransactionAmount) {
                System.out.println("Amount is below the minimum transaction amount ($" + minTransactionAmount + ")");
                return;
            }

            switch (accountType){
                case "current":
                    for (int i = 0; i < currentAccCount; i++) {
                        if (currentAccs[i].getAccountNumber() == accountNumber) {
                            double currentAmount = currentAccs[i].getBalance();
                            double newAmount = currentAmount - withdrawAmount;
                            if (newAmount < -overdraftLimit) {
                                System.out.println("Withdrawal rejected: exceeds overdraft limit of $" + overdraftLimit);
                                return;
                            }
                                currentAccs[i].setNewBalance(newAmount);
                                customerAccounts[idCustomerAccountNumber].incrementTran();
                                customerAccounts[idCustomerAccountNumber].setNewBalance(newAmount);
                            System.out.println("Current balance = " + currentAmount + ", withdraw = " + withdrawAmount
                                    + ", new balance = " + newAmount);
                            return;

                        }
                    }
                    break;
                case  "saving":
                    for (int i = 0; i < savingAccountCount; i++) {
                        if (savingAccs[i].getAccountNumber() == accountNumber) {
                            double currentAmount = savingAccs[i].getBalance();
                            if (withdrawAmount > currentAmount) {
                                System.out.println("Insufficient Funds");
                                return;
                            }
                                double newAmount = currentAmount - withdrawAmount;                                savingAccs[i].setNewBalance(newAmount);
                                savingAccs[i].incrementSuccessfulWithdrawCount();
                                customerAccounts[idCustomerAccountNumber].incrementTran();
                                customerAccounts[idCustomerAccountNumber].setNewBalance(newAmount);
                                System.out.println("Saving balance = " + currentAmount + ", withdraw = " + withdrawAmount
                                        + ", new balance = " + newAmount);
                                return;

                        }
                    }
                    break;

                default:
                    System.out.println("Unknown account type.");


        }
    }

    public void depositMoney(Scanner sc){
        System.out.print("Enter Account Number: ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid Account Number");
            sc.nextLine();
            return;
        }
        boolean foundAcc = false;
        int  accountNumber = sc.nextInt();
        String accountType = "";
       int  idCustomerAccountNumber = -1;
        for(int i = 0; i < customerAccountCount; i++) {

            if(accountNumber == customerAccounts[i].getAccountNumber()){
                foundAcc = true;
                accountType = customerAccounts[i].getAccountType();
                idCustomerAccountNumber = i ;
                break;
            }
        }
        if(!foundAcc){
            System.out.println("Invalid Account Number");
            return;
        }
            String status = customerAccounts[idCustomerAccountNumber].getStatus();
            if (status.equalsIgnoreCase("closed") || status.equalsIgnoreCase("frozen")) {
                System.out.println("Transaction rejected: account is " + status);
                return;
            }

            if (accountType.equalsIgnoreCase("fixed")) {
                System.out.println("Fixed deposit account can't receive deposits.");
                return;
            }
            System.out.print("Enter Amount to be deposit: ");
            if (!sc.hasNextDouble()) {
                System.out.println("Invalid Amount to be deposit");
                sc.nextLine();
                return;
            }

            double depositAmount = sc.nextDouble();
            if (depositAmount <= 0) {
                System.out.println("Amount must be positive");
                return;
            }
            if (depositAmount < minTransactionAmount) {
                System.out.println("Amount is below the minimum transaction amount ($" + minTransactionAmount + ")");
                return;
            }
            switch (accountType){
                case "current":
                    for (int i = 0; i < currentAccCount; i++) {
                        if (currentAccs[i].getAccountNumber() == accountNumber) {
                            double currentAmount = currentAccs[i].getBalance();
                            double newAmount = depositAmount + currentAmount;
                            currentAccs[i].setNewBalance(newAmount);
                            customerAccounts[idCustomerAccountNumber].incrementTran();
                            customerAccounts[idCustomerAccountNumber].setNewBalance(newAmount);
                            System.out.println("Current balance = " + currentAmount + ", deposit = " + depositAmount
                                    + ", new balance = " + newAmount);
                            return;

                        }
                    }
                    break;
                case  "saving":
                    for (int i = 0; i < savingAccountCount; i++) {
                        if (savingAccs[i].getAccountNumber() == accountNumber) {
                            double currentAmount = savingAccs[i].getBalance();

                            double newAmount = currentAmount + depositAmount;                                savingAccs[i].setNewBalance(newAmount);
                            customerAccounts[idCustomerAccountNumber].incrementTran();
                            customerAccounts[idCustomerAccountNumber].setNewBalance(newAmount);

                            System.out.println("Saving balance = " + currentAmount + ", deposit = " + depositAmount
                                    + ", new balance = " + newAmount);
                            return;

                        }
                    }
                    break;
                default:
                    System.out.println("Unknown account type.");

            }



    }

    public void transferMoney(Scanner sc) {
        System.out.print("Enter Source Account Number: ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid Account Number");
            sc.nextLine();
            return;
        }
        int sourceAccNumber = sc.nextInt();

        System.out.print("Enter Destination Account Number: ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid Account Number");
            sc.nextLine();
            return;
        }
        int destAccNumber = sc.nextInt();
        sc.nextLine();

        if (sourceAccNumber == destAccNumber) {
            System.out.println("Source and destination accounts must be different");
            return;
        }

        int sourceAccIndex = -1;
        int destAccIndex = -1;
        for (int i = 0; i < customerAccountCount; i++) {
            if (customerAccounts[i].getAccountNumber() == sourceAccNumber) {
                sourceAccIndex = i;
            }
            if (customerAccounts[i].getAccountNumber() == destAccNumber) {
                destAccIndex = i;
            }
        }

        if (sourceAccIndex == -1 || destAccIndex == -1) {
            System.out.println("One or both account numbers do not exist");
            return;
        }

        String sourceStatus = customerAccounts[sourceAccIndex].getStatus();
        String destStatus = customerAccounts[destAccIndex].getStatus();
        if (sourceStatus.equalsIgnoreCase("closed") || sourceStatus.equalsIgnoreCase("frozen")) {
            System.out.println("Transfer rejected: source account is " + sourceStatus);
            return;
        }
        if (destStatus.equalsIgnoreCase("closed") || destStatus.equalsIgnoreCase("frozen")) {
            System.out.println("Transfer rejected: destination account is " + destStatus);
            return;
        }
        if (customerAccounts[destAccIndex].getAccountType().equalsIgnoreCase("fixed")) {
            System.out.println("Transfer rejected: fixed deposit accounts can't receive transfers");
            return;
        }

        System.out.print("Enter Amount to transfer: ");
        if (!sc.hasNextDouble()) {
            System.out.println("Invalid Amount");
            sc.nextLine();
            return;
        }
        double amount = sc.nextDouble();
        if (amount <= 0) {
            System.out.println("Amount must be positive");
            return;
        }

        double sourceBalanceBefore = customerAccounts[sourceAccIndex].getBalance();
        boolean withdrawn = applyWithdrawal(sourceAccIndex, sourceAccNumber, amount);
        if (!withdrawn) {
            System.out.println("Transfer failed: could not withdraw from source account");
            return;
        }

        boolean deposited = applyDeposit(destAccIndex, destAccNumber, amount);
        if (!deposited) {
            applyDeposit(sourceAccIndex, sourceAccNumber, amount);
            System.out.println("Transfer failed: could not deposit into destination account. Source balance restored.");
            return;
        }

        System.out.println("Transfer successful. Source balance: " + sourceBalanceBefore + "  "
                + customerAccounts[sourceAccIndex].getBalance()
                + " | Destination balance increased by " + amount);
    }

    private boolean applyWithdrawal(int accIndex, int accountNumber, double amount) {
        String accountType = customerAccounts[accIndex].getAccountType();
        switch (accountType) {
            case "current":
                for (int i = 0; i < currentAccCount; i++) {
                    if (currentAccs[i].getAccountNumber() == accountNumber) {
                        double newAmount = currentAccs[i].getBalance() - amount;
                        if (newAmount < -overdraftLimit) return false;
                        currentAccs[i].setNewBalance(newAmount);
                        customerAccounts[accIndex].incrementTran();
                        customerAccounts[accIndex].setNewBalance(newAmount);
                        return true;
                    }
                }
                return false;
            case "saving":
                for (int i = 0; i < savingAccountCount; i++) {
                    if (savingAccs[i].getAccountNumber() == accountNumber) {
                        if (amount > savingAccs[i].getBalance()) return false;
                        double newAmount = savingAccs[i].getBalance() - amount;
                        savingAccs[i].setNewBalance(newAmount);
                        savingAccs[i].incrementSuccessfulWithdrawCount();
                        customerAccounts[accIndex].incrementTran();
                        customerAccounts[accIndex].setNewBalance(newAmount);
                        return true;
                    }
                }
                return false;
            case "fixed":
                for (int i = 0; i < fixedDepositAccountCount; i++) {
                    if (fixedDepositAccs[i].getAccountNumber() == accountNumber) {
                        if (fixedDepositAccs[i].getRemainingMonths() > 0) return false;
                        if (amount > fixedDepositAccs[i].getFixedBalance()) return false;
                        double newAmount = fixedDepositAccs[i].getFixedBalance() - amount;
                        fixedDepositAccs[i].setnewFixedBalance(newAmount);
                        customerAccounts[accIndex].incrementTran();
                        customerAccounts[accIndex].setNewBalance(newAmount);
                        return true;
                    }
                }
                return false;
            default:
                return false;
        }
    }

    private boolean applyDeposit(int accIndex, int accountNumber, double amount) {
        String accountType = customerAccounts[accIndex].getAccountType();
        switch (accountType) {
            case "current":
                for(int i = 0; i < currentAccCount; i++) {
                    if (currentAccs[i].getAccountNumber() == accountNumber) {
                        double newAmount = currentAccs[i].getBalance() + amount;
                        currentAccs[i].setNewBalance(newAmount);
                        customerAccounts[accIndex].incrementTran();
                        customerAccounts[accIndex].setNewBalance(newAmount);
                        return true;
                    }
                }
                return false;
            case "saving":
                for(int i = 0; i < savingAccountCount; i++) {
                    if (savingAccs[i].getAccountNumber() == accountNumber) {
                        double newAmount = savingAccs[i].getBalance() + amount;
                        savingAccs[i].setNewBalance(newAmount);
                        customerAccounts[accIndex].incrementTran();
                        customerAccounts[accIndex].setNewBalance(newAmount);
                        return true;
                    }
                }
                return false;
            default:
                return false;
        }
    }
    public void displayCustomerAccounts(Scanner sc){
        System.out.print("Enter Customer ID: ");
        if (!sc.hasNextInt()){
            System.out.println("Invalid Customer ID");
            sc.nextLine();
        }
        int customerId = sc.nextInt();
        /*
• Account number
• Account type
• Balance
• Status
• Transaction count
 */
        boolean customerExists = false;
        for (int i = 0; i < customerIdCount; i++) {
            if (customers[i].getCustomerId() == customerId) {
                customerExists = true;
                System.out.println("Customer: " + customers[i].getCustomerName() + " (ID: " + customerId + ")");
                break;
            }
        }
        if (!customerExists) {
            System.out.println("Customer ID does not exist");
            return;
        }
        boolean hasAccounts = false;
        for (int i = 0; i < customerAccountCount; i++) {
            if (customerId == customerAccounts[i].getCustomerId()){
                System.out.println(
                        "Account number : "  + customerAccounts[i].getAccountNumber() + "\n"+
                        "Account type : "  + customerAccounts[i].getAccountType() + "\n"+
                        "Balance : "  + customerAccounts[i].getBalance()   + "\n"+
                        "Status : "  + customerAccounts[i].getStatus() + "\n"+
                        "Transaction count : " + customerAccounts[i].getTransactionCount()
                );
                hasAccounts = true;
            }
        }
        if (!hasAccounts) {
            System.out.println("This customer has no accounts.");
        }

    }

    public void displayAllBranchAccounts(){
        /*
        branch 1
        branch 2
        branch 3
         */
        if (customerAccountCount == 0) {
            System.out.println("There are no accounts in the system.");
            return;
        }
        int countbranch1 = 0;
        int countbranch2 = 0;
        int countbranch3 = 0;

        for(int i = 0; i < customerAccountCount; i++){
            branch branches =customerAccounts[i].getBranchName();
            if (branches.equals(branch.branch1)){
                countbranch1++;
            } else if (branches.equals(branch.branch2)) {
                countbranch2++;
            }else if (branches.equals(branch.branch3)){
                countbranch3++;
            }
        }
        /*
        account number, owner name,
type, balance, status, and transaction count
         */
        if (countbranch1 > 0) {
            System.out.println("Branch 1 " + "\n" + "[" );
        for(int i = 0; i < customerAccountCount; i++) {
            branch branches =customerAccounts[i].getBranchName();

                if (branches.equals(branch.branch1)){
                    System.out.println(i + "\n"+
                            "Account number : " + customerAccounts[i].getAccountNumber() +"\n"+
                            "Account type : " + customerAccounts[i].getAccountType() +"\n"+
                            "Account balance : " + customerAccounts[i].getBalance() +"\n"+
                            "Account status : " + customerAccounts[i].getStatus() +"\n"+
                            "Account transaction count : " + customerAccounts[i].getTransactionCount()
                    );
                }
            }
            System.out.println("]");
            }else {
                System.out.println("there are no accounts in this branch");
            }
            if (countbranch2 > 0) {
                System.out.println("Branch 2 " + "\n" + "[" );
                for(int i = 0; i < customerAccountCount; i++) {
                    branch branches =customerAccounts[i].getBranchName();

                    if (branches.equals(branch.branch2)){
                        System.out.println(i + "\n"+
                                "Account number : " + customerAccounts[i].getAccountNumber() +"\n"+
                                "Account type : " + customerAccounts[i].getAccountType() +"\n"+
                                "Account balance : " + customerAccounts[i].getBalance() +"\n"+
                                "Account status : " + customerAccounts[i].getStatus() +"\n"+
                                "Account transaction count : " + customerAccounts[i].getTransactionCount()
                        );
                    }
                }
                System.out.println("]");
            }else {
                System.out.println("there are no accounts in this branch");
            }
            if (countbranch3 > 0) {
                System.out.println("Branch 3 " + "\n" + "[" );
                for(int i = 0; i < customerAccountCount; i++) {
                    branch branches =customerAccounts[i].getBranchName();
                    if (branches.equals(branch.branch3)){
                        System.out.println(i + "\n"+
                                "Account number : " + customerAccounts[i].getAccountNumber() +"\n"+
                                "Account type : " + customerAccounts[i].getAccountType() +"\n"+
                                "Account balance : " + customerAccounts[i].getBalance() +"\n"+
                                "Account status : " + customerAccounts[i].getStatus() +"\n"+
                                "Account transaction count : " + customerAccounts[i].getTransactionCount()
                        );
                    }
                }
                System.out.println("]");
            } else {
                System.out.println("there are no accounts in this branch");
            }
    }

    public void searchAccountByNumber(Scanner sc) {
        System.out.print("Enter Account Number: ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid Account Number");
            sc.nextLine();
            return;
        }
        int accountNumber = sc.nextInt();

        for (int i = 0; i < customerAccountCount; i++) {
            if (accountNumber == customerAccounts[i].getAccountNumber()) {
                System.out.println(
                        "Account number : " + customerAccounts[i].getAccountNumber() + "\n" +
                                "Customer Id : " + customerAccounts[i].getCustomerId() + "\n" +
                                "Account type : " + customerAccounts[i].getAccountType() + "\n" +
                                "Balance : " + customerAccounts[i].getBalance() + "\n" +
                                "Status : " + customerAccounts[i].getStatus() + "\n" +
                                "Transaction count : " + customerAccounts[i].getTransactionCount()
                );

                if (customerAccounts[i].getAccountType().equalsIgnoreCase("current")) {
                    System.out.println("Overdraft limit : $" + overdraftLimit);
                } else if (customerAccounts[i].getAccountType().equalsIgnoreCase("fixed")) {
                    for (int j = 0; j < fixedDepositAccountCount; j++) {
                        if (fixedDepositAccs[j].getAccountNumber() == accountNumber) {
                            int remaining = fixedDepositAccs[j].getRemainingMonths();
                            System.out.println("Months passed : " + fixedDepositAccs[j].getMonthsPassed());
                            System.out.println("Remaining months : " + remaining);
                            System.out.println("Matured : " + (remaining <= 0 ? "Yes" : "No"));
                        }
                    }
                }
                return;
            }
        }
        System.out.println("Invalid Account Number");
    }

    public void searchAccountsByType(Scanner sc){
        System.out.print("Enter Account Type (current/saving/fixed): ");

        String accountType=sc.nextLine().trim().toLowerCase();

        if (!accountType.equals("current") && !accountType.equals("saving") && !accountType.equals("fixed")) {
            System.out.println("Invalid Account Type");
            return;
        }
        System.out.println(accountType + " accounts:\n[");
        for (int i = 0; i < customerAccountCount; i++) {
            if (customerAccounts[i].getAccountType().equalsIgnoreCase(accountType)) {
                System.out.println(
                        "Account number : " + customerAccounts[i].getAccountNumber() +
                        ", Balance : " + customerAccounts[i].getBalance() +
                        ", Status : " + customerAccounts[i].getStatus()
                );
            }
        }
        System.out.println("]");
    }

    public void closeAnAccount(Scanner sc){
        System.out.print("Enter Account Number: ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid Account Number");
            sc.nextLine();
            return;
        }
        int accountNumber = sc.nextInt();

        int accIndex = -1;
        for (int i = 0; i < customerAccountCount; i++) {
            if (accountNumber == customerAccounts[i].getAccountNumber()) {
                accIndex = i;
                break;
            }
        }

        if (accIndex == -1) {
            System.out.println("Invalid Account Number");
            return;
        }

        if (customerAccounts[accIndex].getStatus().equalsIgnoreCase("closed")) {
            System.out.println("Account already closed");
            return;
        }

        if (customerAccounts[accIndex].getBalance() != 0) {
            System.out.println("Account has a non-zero balance and cannot be closed");
            return;
        }

        String accountType = customerAccounts[accIndex].getAccountType();

        if (accountType.equalsIgnoreCase("fixed")) {
            for (int j = 0; j < fixedDepositAccountCount; j++) {
                if (fixedDepositAccs[j].getAccountNumber() == accountNumber) {
                    if (fixedDepositAccs[j].getRemainingMonths() > 0) {
                        System.out.println("Fixed Deposit Account is still locked and cannot be closed");
                        return;
                    }
                    fixedDepositAccs[j].closedAnAccount();
                    break;
                }
            }
        } else if (accountType.equalsIgnoreCase("current")) {
            for (int j = 0; j < currentAccCount; j++) {
                if (currentAccs[j].getAccountNumber() == accountNumber) {
                    currentAccs[j].closedAnAccount();
                    break;
                }
            }
        } else if (accountType.equalsIgnoreCase("saving")) {
            for (int j = 0; j < savingAccountCount; j++) {
                if (savingAccs[j].getAccountNumber() == accountNumber) {
                    savingAccs[j].closedAnAccount();
                    break;
                }
            }
        }

        customerAccounts[accIndex].closedAnAccount();
        System.out.println("Account closed successfully.");
    }
}
