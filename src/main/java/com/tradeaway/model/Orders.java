package com.tradeaway.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Item> items ;

    @NotNull
    private String address;

    private OrderStatus orderStatus=OrderStatus.PENDING;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "seller_id")
    private Seller seller;


    //private Date orderDate= new Date();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    /*
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    */
}
