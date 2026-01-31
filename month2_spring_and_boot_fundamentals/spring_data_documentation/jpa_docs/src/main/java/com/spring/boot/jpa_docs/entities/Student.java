package com.spring.boot.jpa_docs.entities;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "student_name", length = 50)
    private String studentName;

    @Column(name = "student_mobile")
    private long mobile;

    @Column(length = 50)
    private String email;

    @Column(name = "student_address")
    private String address;

    @Column(name = "student_birth_date")
    private Date birthDate;

    @Column(name = "student_score")
    private int score;

    public Student() {
    }

    public Student(Long id, String studentName, long mobile, String email, String address, Date birthDate, int score) {
        this.id = id;
        this.studentName = studentName;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", mobile=" + mobile +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", birthDate=" + birthDate +
                ", score=" + score +
                '}';
    }
}
