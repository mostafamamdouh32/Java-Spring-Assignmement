import bankClass.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        bankDatabase bank = new bankDatabase();
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("Enter option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid option");
                sc.nextLine();
                continue;
            }
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    bank.addCustomer(sc);
                    break;
                case 2:
                    bank.addAccount(sc);
                    break;
                case 3:
                    bank.depositMoney(sc);
                    break;
                case 4:
                    bank.withdrawMoney(sc);
                    break;
                case 5:
                    bank.transferMoney(sc);
                    break;
                case 6:
                    bank.displayCustomerAccounts(sc);
                    break;
                case 7:
                    bank.displayAllBranchAccounts();
                    break;
                case 8:
                    bank.searchAccountByNumber(sc);
                    break;
                case 9:
                    bank.searchAccountsByType(sc);
                    break;
                case 10:
                    bank.closeAnAccount(sc);
                    break;
                case 0:
                    System.out.println("Thank you for using Al Manara Bank. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("--------- AL MANARA BANK -----------");
        System.out.println("1  Register New Customer");
        System.out.println("2  Open New Account");
        System.out.println("3  Deposit Money");
        System.out.println("4  Withdraw Money");
        System.out.println("5  Transfer Between Accounts");
        System.out.println("6  Display Customer Accounts");
        System.out.println("7  Display All Branch Accounts");
        System.out.println("8  Search Account by Number");
        System.out.println("9  Search Accounts by Type");
        System.out.println("10 Close an Account");
        System.out.println("0  Exit");
        System.out.println("-----------------------------------");
    }
}