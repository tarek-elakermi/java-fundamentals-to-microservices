package org.example.learning.learning_Journey.parallelStreams.entities;

import java.util.Objects;

public class Student {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final int age;
    private final String major;
    private final double gpa;
    private final String year;

    public Student(int id, String firstName, String lastName, String email, int age, String major, double gpa, String year) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.major = major;
        this.gpa = gpa;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getMajor() {
        return major;
    }

    public double getGpa() {
        return gpa;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", major='" + major + '\'' +
                ", gpa=" + gpa +
                ", year='" + year + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && age == student.age && Double.compare(gpa, student.gpa) == 0 && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(email, student.email) && Objects.equals(major, student.major) && Objects.equals(year, student.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, age, major, gpa, year);
    }
}
