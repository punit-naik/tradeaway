package com.tradeaway.model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Seller extends User {

    private String pan;
    private int experience;

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
