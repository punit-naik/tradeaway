package com.tradeaway.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "seller_id")
    private Seller sellerId;
    @NotNull
    private String name;
    @NotNull
    private Category category;
    private String imgUrl;
    @NotNull
    private String shortDesc;
    @Pattern(regexp="\\d+")
    private int quantity;
    @Pattern(regexp="\\d+")
    private int price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public Seller getSellerId() {
        return this.sellerId;
    }

    public void setSellerID(Seller sellerId) {
        this.sellerId = sellerId;
    }

}
