package models;

import java.time.LocalDate;

public class Faculty extends MemberRecord {
    private String department;

    public Faculty(int memberId, String type, LocalDate dateOfMembership, String name, String address, String phoneNo, String department) {
        super(memberId, type, dateOfMembership, name, address, phoneNo);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                super.toString() +
                ", department='" + department + '\'' +
                '}';
    }
}