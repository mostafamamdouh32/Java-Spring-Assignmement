import students.students;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numStudent = 0;
        while (true) {
            System.out.print("Enter number of students: ");
            if (sc.hasNextInt()){
                 numStudent = sc.nextInt();
                 if (numStudent < 1 ) {
                     System.out.println("Invalid input");
                     continue;
                 }else {
                     break;
                 }
            }else {
                System.out.println("Invalid input");
                sc.next();
            }
        } // Enter student number
        students [] student = new students[numStudent];
        students val = new students();
        sc.nextLine();
        for (int i = 0; i < numStudent; i++) {
            student[i] = new students();
            String studentID = student[i].valStudentId(sc,"Enter student ID: ",i,student);
            student[i].setStudentId(studentID);
            String studentName = student[i].valStudentName(sc,"Enter student name : ");
            student[i].setName(studentName);
            int grade = student[i].valGrade(sc,"Enter student grade : ");
            sc.nextLine();
            student[i].setGrade(grade);
        }  //Enter student





        while (true){
            System.out.println(
                    "1.  Display Students. \n"+
                            "2. Calculate Average Grade. \n"+
                            "3. Find Highest Grade.\n"+
                            "4. Search Student by ID  \n"+
                            "5. Exit "
            );
            System.out.print("Choose from menu 1 to 5 : ");
            int option ;
            if (sc.hasNextInt()) {
                option = sc.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }
            sc.nextLine();
            switch (option){
                case 1:
                    for (int i = 0; i < numStudent; i++) {
                        //System.out.println("Student ID: " + student[i].getStudentId() + " Name: " + student[i].getName() + " Grade: " + student[i].getGrade());
                        student[i].displayStudent();
                    }  // display student
                    break;
                case 2 :
                    student[0].avgGrade(numStudent,student);
                break;
                case 3 :
                    student[0].highestGrade(numStudent,student);
                    break;
                case  4 :
                    System.out.print("Enter student ID to search: ");
                    String studentName = sc.nextLine();
                    student[0].searchStudent(numStudent,student,studentName);
                    break;
                case 5 :
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");


            }
        }


    }
}