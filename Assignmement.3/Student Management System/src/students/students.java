package students;

import javafx.scene.transform.Scale;

import java.util.Scanner;

public class students {

    private String  studentId;
    private String  name;
    private int  grade;

    public students() {}
    public void setStudentId(String studentId) {this.studentId = studentId;}
    public void setName(String name) {this.name = name;}
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public String getStudentId() {

        return studentId;
    }
    public String getName() {
        return name;
    }
    public int getGrade() {
        return grade;
    }

    public String valStudentName(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String studentName = sc.nextLine().trim();
            if (studentName.isEmpty()) {
                System.out.println("Invalid input: name cannot be empty.");
                continue;
            }
            if (!studentName.matches("[a-zA-Z ]+")) {
                System.out.println("Invalid input: name must contain letters only.");
                continue;
            }
            return studentName;
        }
    }

    public String valStudentId(Scanner sc ,String prompt ,int i,students[] student) {
        while (true) {
            System.out.print(prompt);
            String studentId = sc.nextLine().trim();
            if (studentId.isEmpty()) {
                System.out.println("Invalid input: this field cannot be empty.");
                continue;
            }
            boolean isDuplicate = false;

            for (int j = 0; j < i; j++) {
                if (student[j].getStudentId().equals(studentId)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (isDuplicate) {
                System.out.println("Invalid input: this ID already exists, please enter a unique ID.");
                continue;
            }
            return studentId;
        }
    }
    public int valGrade(Scanner sc ,String prompt) {
        int grade;
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()){
                    grade = sc.nextInt();
                if (grade <= 0 || grade > 100){
                    System.out.println("Invalid input: grade must be between 0 and 100.");
                        continue;
                    }else {
                        break;
                    }
                }else {
                    System.out.println("Invalid input");
                    sc.next();
                }
    }
        return grade;
    }
    public void displayStudent(){
        System.out.println("Student ID: " + studentId + " Name: " + name + " Grade: " + grade);
    }
    public void avgGrade(int numberOfStudent,students[] student){
        double sum =0.0;
        double avg =0.0;
       for (int i=0;i<numberOfStudent;i++){
           sum += student[i].getGrade();
       }
       avg = sum/numberOfStudent;
       System.out.println("Average grade is " + avg);
    }
    public void highestGrade(int numberOfStudent,students[] student ){
        int highest =student[0].getGrade();
        for (int i = 1; i < numberOfStudent; i++) {
                if (student[i].getGrade() > highest) {
                    highest = student[i].getGrade();
                }
        }

        System.out.println("Highest grade is " + highest);
    }
    public void  searchStudent (int numberOfStudent,students[] student ,String studentId){
        boolean searchStudent =  false;
        for (int i=0;i<numberOfStudent;i++){
            if (studentId.equalsIgnoreCase(student[i].studentId)){
                searchStudent = true;
            }
        }
        if (searchStudent == true){
            System.out.println("student found id : " + studentId);
        }else {
            System.out.println("student not found id : " + studentId);
        }
    }
}
