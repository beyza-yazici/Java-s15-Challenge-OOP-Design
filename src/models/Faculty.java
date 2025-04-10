package models;

import java.time.LocalDate;

public class Faculty extends MemberRecord {
    private String department;

    public Faculty(int id, String name, String type, LocalDate dateOfMembership, int noBooksIssued, int maxBookLimit, String address, String phoneNo) {
        super(id, name, type, dateOfMembership, noBooksIssued, maxBookLimit, address, phoneNo);
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