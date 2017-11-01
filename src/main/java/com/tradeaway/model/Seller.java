package com.tradeaway.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
public class Seller extends User {

    @NotNull
    private String pan;
    @NotNull
    private int experience;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="seller_id")
    private Set<Inventory> items;

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

    public Set<Inventory> getItems()
    {
        return this.items;
    }

    public void setItems(Set<Inventory> items)
    {
        this.items = items;
    }
}
