package models;

import java.time.LocalDate;

public class MemberRecord extends Person {

    private String type;
    private LocalDate dateOfMembership;
    private int noBooksIssued;
    private int maxBookLimit;
    private String address;
    private String phoneNo;

    public MemberRecord(int id, String name, String type, LocalDate dateOfMembership, int noBooksIssued, int maxBookLimit, String address, String phoneNo) {
        super(id, name);
        this.type = type;
        this.dateOfMembership = dateOfMembership;
        this.noBooksIssued = noBooksIssued;
        this.maxBookLimit = maxBookLimit;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDateOfMembership() {
        return dateOfMembership;
    }

    public int getNoBooksIssued() {
        return noBooksIssued;
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void incBookIssued() {
        if (noBooksIssued < maxBookLimit) {
            noBooksIssued++;
        } else {
            System.out.println("Cannot issue more than " + maxBookLimit + " books.");
        }
    }

    public void decBookIssued() {
        if (noBooksIssued > 0) {
            noBooksIssued--;
        }
    }

    public void payBill(double amount) {
        System.out.println("Bill of $" + amount + " paid by " + name);
    }

    @Override
    public String toString() {
        return "MemberRecord{" +
                "type='" + type + '\'' +
                ", dateOfMembership=" + dateOfMembership +
                ", noBooksIssued=" + noBooksIssued +
                ", maxBookLimit=" + maxBookLimit +
                ", address='" + address + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

