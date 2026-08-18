import com.library.Library;

void main() {
    Scanner sc = new Scanner(System.in);
    Library library = new Library();
    while (true) {
        System.out.println(
                "\n===== Bayt Al Hekma Library =====\n" +
                        " 1  View catalogue\n" +
                        " 2  Register member\n" +
                        " 3  Borrow item\n" +
                        " 4  Return item\n" +
                        " 5  Renew loan\n" +
                        " 6  Search item by ID\n" +
                        " 7  View items by status\n" +
                        " 8  Pay outstanding fines\n" +
                        " 9  View all members\n" +
                        "10  Library report\n" +
                        "11  Mark item as lost\n" +
                        "12  Register new item (add to catalogue)\n" +
                        " 0  Exit\n"+
                        "-----------------------------------------------"
        );
        System.out.print("Choose an option: ");
        String choice = sc.nextLine().trim();

        switch (choice) {
            case "1": library.viewCatalogue(); break;
            case "2": library.registerMember(); break;
            case "3": library.borrowItem(); break;
            case "4": library.returnItem(); break;
            case "5": library.renewLoan(); break;
            case "6": library.searchItem(); break;
            case "7": library.viewItemsByStatus(); break;
            case "8": library.payOutStandingFines(); break;
            case "9": library.viewMembers(); break;
            case "10": library.libraryReport(); break;
            case "11": library.markItemAsLost(); break;
            case "12": library.registerItem(); break;
            case "0":
                System.out.println("Goodbye!");
                return;
            default:
                System.out.println("Invalid option, try again.");
        }
    }

}
