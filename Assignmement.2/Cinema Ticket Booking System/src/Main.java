import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static char [][] initiallySeats (){
        /*
                c0  c1  c2  c3  c4  c5
            r0  0   0   0   0   0   0
            r1  0   0   0   0   0   0
            r2  0   0   0   0   0   0
            r3  0   0   0   0   0   0
            r5  0   0   0   0   0   0
         */
        char [][] seats = new char[5][6];
        for (int i=0; i< seats.length; i++){
        for (int j=0; j< seats[i].length; j++){
             seats[i][j] = '0';
           }
        }
        return  seats;
    }
    static char [][] bookSeat (Scanner sc,char [][] seats){
        // 12  -> 1 row  , 2 column
        
        int column;
        int row;
        while (true) {
            System.out.print("Please enter number of seat : ");
            if (sc.hasNextInt()) {
                int numOfSeat = sc.nextInt();
                if( numOfSeat < 99 ) {
                    column = numOfSeat % 10;
                    row = numOfSeat / 10;
                }else {
                    column = seats[0].length;
                    row = seats.length;
                }

                if (row >= 0 && row < seats.length && column >= 0 && column < seats[0].length) {
                    break;
            }else {
                    System.out.println("Invalid seat");
                }
            } else {
                System.out.println("Invalid seat");
                sc.next();
            }
        }
      if(seats[row][column] == 'X'){
          System.out.println("Seat is already booked");
      }else {
          seats[row][column] = 'X';
          System.out.println("Seat is now booked");
      }

        return seats;
    }

    static char [][] cancelSeat (Scanner sc,char [][] seats){
        int row ;
        int column;

        while (true) {
            System.out.print("Please enter number of seat you want to cancel : ");
            if (sc.hasNextInt()) {
                int numOfSeat = sc.nextInt();
                if(numOfSeat < 99 ) {
                    column = numOfSeat % 10;
                    row = numOfSeat / 10;
                }else {
                    column = seats[0].length;
                    row = seats.length;
                }

                if (row >= 0 && row < seats.length && column >= 0 && column < seats[0].length) {
                    break;
                }else {
                    System.out.println("Invalid seat");
                }
            } else {
                System.out.println("Invalid seat");
                sc.next();
            }
        }

        if (seats[row][column] == 'X') {
            seats[row][column] = '0';
            System.out.println("Seats are canceled");
        }else {
            System.out.println("Invalid seat");
        }
        return seats;
    }
static void showAllMoves (){
    String[] movieNames = {"Superman","Avatar","Minecraft","Inside Out","F1"};
int count = 1;
    for (int i=0; i< movieNames.length; i++){
        System.out.println(count+". " +movieNames[i]);
        count++;
    }
}
static int showAvailableBookedseats (char [][] seats){
        int availableseats = 0;
        int bookedseats = 0;
        for (int i=0; i< seats.length; i++){
            for (int j=0; j< seats[i].length; j++){
                if(seats[i][j] == 'X'){
                    bookedseats ++;
                }else {
                    availableseats ++;
                }
            }
        }
        System.out.println("Available seats: " + availableseats);
        System.out.println("Booked seats: " + bookedseats);
        return bookedseats ;
}
static  void showSeats (char [][] seats){
    for(int i=0; i< seats.length; i++){

        for (int j=0; j< seats[i].length; j++){

            if (seats[i][j] == 'X'){
                System.out.print(seats[i][j] + "  ");
            }else {
                System.out.print(i + ""+j +" ");
            }
        }
        System.out.println();
    }
}
static void chechFulloccupancy(char [][] seats , int bookedseats){
        int lenOfSeats = seats.length * seats[0].length;

        double occupancy =lenOfSeats * 0.8;

    if (bookedseats >= occupancy){
        System.out.println("*********************************");
        System.out.println("********Seat Almost Full*********");
        System.out.println("*********************************");

    }


}
    public static void main(String[] args) {
        /*
        1. Display Seats
        2. Book Seat
        3. Cancel Booking
        4. Show all movies.
        5. Show number of available and booked seats
        0. Exit
         */
        char[][] seats = initiallySeats();
        Scanner sc = new Scanner(System.in);
        while (true) {
            int bookedseats = showAvailableBookedseats(seats);
            chechFulloccupancy(seats, bookedseats);
            System.out.println(
                    "1. Display Seats. \n" +
                            "2. Book Seat. \n" +
                            "3. Cancel Booking.\n" +
                            "4. Show all movies.  \n" +
                            "5. Show number of available and booked seats. \n" +
                            "6. Exit "
            );
            System.out.print("Choose from menu 1 to 6 : ");
            int option;
            if (sc.hasNextInt()) {
                option = sc.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }
            sc.nextLine();
            switch (option) {
                case 1:
                    showSeats(seats);
                    System.out.println("----------------------");
                    break;
                case 2:
                    bookSeat(sc, seats);
                    System.out.println("----------------------");
                    break;
                case 3:
                    cancelSeat(sc, seats);
                    System.out.println("----------------------");
                    break;
                case 4:
                    showAllMoves();
                    System.out.println("----------------------");
                    break;
                case 5:
                    showAvailableBookedseats(seats);
                    System.out.println("----------------------");

                    break;
                case 6:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }


    }

}
