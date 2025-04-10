package models;

import java.time.LocalDate;

public class Student extends MemberRecord {
    private String major;

    public Student(int id, String name, String type, LocalDate dateOfMembership, int noBooksIssued, int maxBookLimit, String address, String phoneNo) {
        super(id, name, type, dateOfMembership, noBooksIssued, maxBookLimit, address, phoneNo);
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