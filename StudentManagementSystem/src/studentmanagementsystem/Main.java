package studentmanagementsystem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentManagementSystem sms =
                new StudentManagementSystem();

        int choice;

        do {

        	System.out.println("\n");
        	System.out.println("======================================");
        	System.out.println("     STUDENT MANAGEMENT SYSTEM");
        	System.out.println("======================================");
        	System.out.println("1. Add Student");
        	System.out.println("2. Remove Student");
        	System.out.println("3. Search Student");
        	System.out.println("4. Display All Students");
        	System.out.println("5. Edit Student");
        	System.out.println("6. Exit");
        	System.out.println("======================================");
        	System.out.print("Enter Choice : ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter Roll Number: ");
                    int roll = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();

                    // Validation
                    if (name.isEmpty() || grade.isEmpty()) {
                        System.out.println("Fields cannot be empty.");
                        break;
                    }

                    Student student =
                            new Student(roll, name, grade, age);

                    sms.addStudent(student);

                    break;

                case 2:

                    System.out.print("Enter Roll Number to Remove: ");
                    int removeRoll = sc.nextInt();

                    sms.removeStudent(removeRoll);

                    break;

                case 3:

                    System.out.print("Enter Roll Number to Search: ");
                    int searchRoll = sc.nextInt();

                    Student foundStudent =
                            sms.searchStudent(searchRoll);

                    if (foundStudent != null) {
                        System.out.println(foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }

                    break;

                case 4:

                    sms.displayAllStudents();

                    break;

                case 5:

                    System.out.print("Enter Roll Number to Edit: ");
                    int editRoll = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter New Grade: ");
                    String newGrade = sc.nextLine();

                    System.out.print("Enter New Age: ");
                    int newAge = sc.nextInt();

                    sms.editStudent(editRoll,
                            newName,
                            newGrade,
                            newAge);

                    break;

                case 6:

                    System.out.println("Exiting Application...");
                    break;

                default:

                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);

        sc.close();
    }
}