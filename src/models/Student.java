package models;

import java.time.LocalDate;

public class Student extends MemberRecord {
    private String major;

    public Student(int memberId, String type, LocalDate dateOfMembership, String name, String address, String phoneNo, String major) {
        super(memberId, type, dateOfMembership, name, address, phoneNo);
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public String toString() {
        return "Student{" +
                super.toString() +
                ", major='" + major + '\'' +
                '}';
    }
}