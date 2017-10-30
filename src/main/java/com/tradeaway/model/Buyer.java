package com.tradeaway.model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Buyer extends User {

    private Gender gender;
    private Date dob;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
