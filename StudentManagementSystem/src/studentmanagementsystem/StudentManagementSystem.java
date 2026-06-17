package studentmanagementsystem;

import java.io.*;
import java.util.ArrayList;

public class StudentManagementSystem {

    private ArrayList<Student> students;
    private final String FILE_NAME = "students.dat";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadStudentsFromFile();
    }

    // Add Student
    public void addStudent(Student student) {
        students.add(student);
        saveStudentsToFile();
        System.out.println("Student added successfully.");
    }

    // Remove Student
    public void removeStudent(int rollNumber) {

        Student student = searchStudent(rollNumber);

        if (student != null) {
            students.remove(student);
            saveStudentsToFile();
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Search Student
    public Student searchStudent(int rollNumber) {

        for (Student student : students) {

            if (student.getRollNumber() == rollNumber) {
                //return student;
            	System.out.println("\n------------------------------------------");
            	System.out.println("STUDENT FOUND");
            	System.out.println("------------------------------------------");

            	System.out.printf("%-10s %-20s %-10s %-5s%n",
            	        "ROLL NO", "NAME", "GRADE", "AGE");
            	return student;
            	//System.out.println("------------------------------------------");
            	//System.out.println(foundStudent);
            	
            }System.out.println("------------------------------------------");
        }

        return null;
    }

    // Display All Students
    public void displayAllStudents() {

        if (students.isEmpty()) {
            System.out.println("\nNo students available.");
            return;
        }

        System.out.println("\n==============================================================");
        System.out.printf("%-10s %-20s %-10s %-5s%n",
                "ROLL NO", "NAME", "GRADE", "AGE");
        System.out.println("==============================================================");

        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("==============================================================");
    }

    // Edit Student
    public void editStudent(int rollNumber, String name, String grade, int age) {

        Student student = searchStudent(rollNumber);

        if (student != null) {

            student.setName(name);
            student.setGrade(grade);
            student.setAge(age);

            saveStudentsToFile();

            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Save Data to File
    private void saveStudentsToFile() {

        try {

            ObjectOutputStream oos =
                    new ObjectOutputStream(new FileOutputStream(FILE_NAME));

            oos.writeObject(students);

            oos.close();

        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    // Load Data from File
    @SuppressWarnings("unchecked")
    private void loadStudentsFromFile() {

        try {

            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream(FILE_NAME));

            students = (ArrayList<Student>) ois.readObject();

            ois.close();

        } catch (Exception e) {
            students = new ArrayList<>();
        }
    }
}