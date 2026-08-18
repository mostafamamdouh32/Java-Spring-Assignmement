import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {

    public static void main(String[] args) {
      // MENU
        int Coffee = 50;
        int Tea = 80 ;
        int hotChocolate = 60;
        int Water = 20 ;
        int total = 0 ;
        int CoffeeCount = 0;
        int TeaCount = 0;
        int hotChocolateCount = 0;
        int WaterCount = 0;
        double finalPrice = 0;
        double discountAmount =0;
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your Name : ");
        String cusName = sc.nextLine();
        //print Menu

        while (true) {
            myMeun(cusName);
            int choice = sc.nextInt();

            if (choice > 0) {
                if (choice == 1) {
                    int quan = quantity();
                    total += Coffee * quan;
                    CoffeeCount += quan;

                } else if (choice == 2) {
                    int quan = quantity();
                    total += Tea * quan;
                    TeaCount += quan;
                } else if (choice == 3) {
                    int quan = quantity();
                    total += hotChocolate * quan;
                    hotChocolateCount += quan;
                } else if (choice == 4) {
                    int quan = quantity();
                    total += Water * quan;
                    WaterCount += quan;

                } else if (choice == 5) {


                    System.out.println("========== RECEIPT ==========");

                    System.out.println("Customer Name : " + cusName);
                    if (CoffeeCount > 0) {
                        System.out.println("Coffee x" + CoffeeCount + " = $" + Coffee * CoffeeCount);
                    }
                    if (TeaCount > 0) {
                        System.out.println("Tea x" + TeaCount + " = $" + Tea * TeaCount);
                    }
                    if (hotChocolateCount > 0) {
                        System.out.println("Hot Chocolate x" + hotChocolateCount + " = $" + hotChocolate * hotChocolateCount);
                    }
                    if (WaterCount > 0) {
                        System.out.println("Water x" + WaterCount + " = $" + Water * WaterCount);
                    }
                    System.out.println("Subtotal : $" + total);
                    if (total >= 300) {
                        discountAmount = total * (15.0 / 100);
                        finalPrice = total - discountAmount;
                        System.out.println("Discount (15%): $" + discountAmount);

                    }else  if(total >= 300){
                        discountAmount = total * (10.0 / 100);
                        finalPrice = total - discountAmount;
                        System.out.println("Discount (10%): $" + discountAmount);
                    }
                    else {
                        finalPrice = total;
                    }
                    System.out.println("Total : $" + finalPrice);
                    if (CoffeeCount > 5) {
                        System.out.println("you have a Free Coffee");
                    }
                    System.out.println("=============================");



                    break;
                } else {
                    System.out.println("Invalid Option ");
                }
            }
        }
        System.out.print("Enter amount paid : ");
        while (true){
            double payment = sc.nextDouble();
            if (payment <= 0 || payment < finalPrice) {
                System.out.println("Insufficient Amount");
                System.out.print("please re enter a valid amount : ");

            }else {
                System.out.println("Thank you for your order!");
                sc.close();
                break;
            }
        }

    }
    static void myMeun(String cus){
        System.out.println("Customer Name : " + cus);
        System.out.println("========== MENU ==========");
        System.out.println("1. Coffee           -> $50");
        System.out.println("2. Tea              -> 35");
        System.out.println("3. Hot Chocolate    -> $60");
        System.out.println("4. Water            -> $20");
        System.out.println("5. Exit");
        System.out.println("==========================");
        System.out.print("Enter your choice :");
    }
    static int  quantity (){
        Scanner sca= new Scanner(System.in);

        System.out.print("Enter quantity: ");
        int quan = sca.nextInt();
        if(quan <= 0){
            System.out.println("Invalid quantity ");
        }
        return quan;
    }
}