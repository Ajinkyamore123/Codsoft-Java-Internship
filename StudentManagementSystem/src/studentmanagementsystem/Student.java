package studentmanagementsystem;

import java.io.Serializable;

public class Student implements Serializable {

    private int rollNumber;
    private String name;
    private String grade;
    private int age;

    public Student(int rollNumber, String name, String grade, int age) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.grade = grade;
        this.age = age;
    }

    // Getters
    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public int getAge() {
        return age;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("%-10d %-20s %-10s %-5d",
                rollNumber, name, grade, age);
    }
}