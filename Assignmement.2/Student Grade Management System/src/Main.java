import java.util.Scanner;





public class Main {
    static void  showAllStudent (String [] students){
        for (int i = 0; i < students.length; i++){
            System.out.println("Students " + (i + 1) + ": " + students[i] );
        }
    }
    static void showAllStudWithGrade (String [] students , int [][] grades , String [] subj){
         /*
        System.out.println("student Name : " + students[1] + "\n"+ "[ \n" +
                "subject Name: " + subj[0]  + "  grade: " + grades[0][1] + "\n" +
                "subject Name: " + subj[1]  + "  grade: " + grades[1][1] + "\n" +
                "subject Name: " + subj[2]  + "  grade: " + grades[2][1] + "\n" + "]"
        );

        */

        for (int i = 0; i < students.length; i++){  // 0 -> 5


                System.out.println("student Name : " + students[i] + "\n"+ "[ \n" +
                        "subject Name: " + subj[0]  + "  grade: " + grades[0][i] + "\n" +
                        "subject Name: " + subj[1]  + "  grade: " + grades[1][i] + "\n" +
                        "subject Name: " + subj[2]  + "  grade: " + grades[2][i] + "\n" + "]"
                );


        }

    }
    static  void  searchStudent (String [] students , String studentName){
       boolean searchStudent =  false;
        for (String student : students){
            if (student.equals(studentName)){
                searchStudent = true;

            }
        }
         if (searchStudent == true){
             System.out.println("student found " + studentName);
         }else {
             System.out.println("student not found " + studentName);
         }
    }
    static  int [][] enterGrades(Scanner sc, String[] studentsName ,String[] subjects ) {

        int[][] Grade = new int[subjects.length][studentsName.length];
        for (int i = 0; i < subjects.length; i++) { // 0 -> 2
            for (int j = 0; j < studentsName.length; j++) { // 0 -> 5
                System.out.print("Enter grade of student "  +studentsName[j] + " in subject " + subjects[i] + ": ");
                while (true) {
                    if (sc.hasNextInt()) {

                        int value = sc.nextInt();
                        if (value < 0 || value > 100) {
                            System.out.println("Grade must be between 0 and 100");
                            System.out.print("Enter grade of student " + studentsName[j] + " in subject " + subjects[i] + ": ");
                            continue;
                        }
                        Grade[i][j] = value;
                        break;
                    } else {
                        System.out.println("Invalid grade");
                        sc.next();
                        System.out.print("Enter grade of student "  +studentsName[j] + " in subject " + subjects[i] + ": ");
                    }
                }
            }

        }
        return Grade ;
    }
    static String [][] convertGrade (int[][] grade){
        String  [][] convertGrade = new String[grade.length][grade[0].length];
        for (int i = 0; i < grade.length; i++) {
            for (int j = 0; j < grade[0].length; j++) {

                if (grade[i][j] >= 85) {
                    convertGrade[i][j] = "A";
                } else if (grade[i][j] >= 75) {
                    convertGrade[i][j] = "B";
                }else if (grade[i][j] >= 65) {
                    convertGrade[i][j] = "C";
                }else if (grade[i][j] >= 50) {
                    convertGrade[i][j] = "D";
                }else {
                    convertGrade[i][j] = "F";
                }

            }
        }
        return  convertGrade;
    }
    static double [] calcAvgSub (int [][] grade){
        double [] calcAvgSub = new double[grade.length]; // sum / avrg
        double sum = 0 ;
        for (int i = 0; i < grade.length; i++) {
            for (int j = 0; j < grade[0].length; j++) {
                sum+=grade[i][j];
                calcAvgSub[i]= sum / grade[i].length;
            }
            sum = 0;
        }
      return calcAvgSub;
    }
    static  void countPassed (String [] students , int [][] grades){
//        for (int i = 0; i < avrStudent.length; i++){
//            if (avrStudent[i] > 50){
//                System.out.println("Student Name " + studens[i] + " is Passed");
//            }else {
//                System.out.println("Student Name " + studens[i] + " is Not Passed");
//            }
//        }
        double valueOfSum = 0 ;
        double [] sum = new double [students.length] ;
        double avg = 0 ;
        int countPass = 0;
        for (int i = 0; i < students.length; i++){  // 0 -> 5

            valueOfSum+= grades[0][i] + grades[1][i] + grades[2][i] ;
            sum[i] = valueOfSum;
            valueOfSum = 0;


        }
        for (int i = 0; i < sum.length; i++){
            avg = sum[i] / 3 ;
            if (avg> 50){
                countPass++;
            }


        }
        System.out.println("Number of passed students : " + countPass);
    }
    public static double[] getHighestGrade(int [][] grades) {
        double[] highest = new double[grades.length];

        for (int i = 0; i < grades.length; i++) {
            double max = grades[i][0];
            for (int j = 1; j < grades[i].length; j++) {
                if (grades[i][j] > max) {
                    max = grades[i][j];
                }
            }
            highest[i] = max;
        }

        return highest;
    }
    public static void main(String[] args) {

       Scanner sc = new Scanner(System.in);
       String[] studentsName = new String[5];
       String[] subjects ={"s1","s2","s3"};
       for (int i = 0; i < 5; i++) {

           while (true) {
               System.out.print("Enter student name " + i + ": ");
               studentsName[i] = sc.nextLine().trim();
               if (studentsName[i].isEmpty()) {
                   System.out.println("Invalid student name due to empty string");
                   continue;
               } else if (!studentsName[i].matches("^[a-zA-Z\\\\s]+$")) {
                   System.out.println("Student name cannot contain numbers or special characters");
                   continue;
               }
               break;
           }
       }
        int [][] grades =  enterGrades(sc, studentsName ,subjects);
       /*
       subject len 3
       students len 5
       grades len 15
        */
//        for (int i = 0; i < grades.length; i++) {
//            int subCount = i +1;
//            System.out.print("s"+subCount + " { ");
//            for (int j = 0; j < grades[i].length; j++) {
//
//                System.out.print(studentsName[j]+" "+grades[i][j] + " , ");
//            }
//            System.out.print("}");
//            System.out.println();
//        }



        while (true){
            System.out.println(
                            "1. Show All Students names. \n"+
                            "2. Show all Students grades in each subject. \n"+
                            "3. Search Student by name.\n"+
                            "4. Count Passed Students  \n"+
                            "5. Display average for each subject. \n"+
                            "6. Display highest grade in each subject. \n"+
                            "7.Display letter grades \n"+
                            "8. Exit "
            );
            System.out.print("Choose from menu 1 to 8 : ");
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
                    showAllStudent(studentsName);
                    break;
                case 2 :
                    showAllStudWithGrade(studentsName,grades,subjects);
                    break;
                case 3 :
                    System.out.println("Enter student name : ");
                    String  studentName = sc.nextLine();
                    searchStudent(studentsName,studentName);
                    break;
                case  4 :
                    countPassed(studentsName,grades);
                    break;
                case 5:
                    double [] avrgGrade =  calcAvgSub(grades);
                    for (int i = 0; i < avrgGrade.length; i++) {
                        System.out.println(subjects[i]+" average = "+avrgGrade[i] );
                    }
                    break;
                case 6 :
                    double [] highestGrade = getHighestGrade(grades);
                    for (int i = 0; i < highestGrade.length; i++) {
                        System.out.println(subjects[i]+" "+highestGrade[i]);
                    }
                    break;
                case 7 :
                    String [][] convertGrades = convertGrade(grades);
                    for (int i = 0; i < convertGrades.length; i++) {
                        int subCount = i +1;
                        System.out.print("s"+subCount + " { ");
                        for (int j = 0; j < convertGrades[i].length; j++) {

                            System.out.print(studentsName[j]+" "+convertGrades[i][j] + " , ");
                        }
                        System.out.print("}");
                        System.out.println();
                    }
                    break;
                case 8 :
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");


            }
        }


    }
}