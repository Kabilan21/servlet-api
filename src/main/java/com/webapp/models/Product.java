package com.webapp.models;

public class Product {
    int id;
    String name;
    int quantity;
    int sellerId;

    public Product(int id, String name, int quantity, int sellerId) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.sellerId = sellerId;
    }

    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getSellerId()
    {
        return sellerId;
    }

    public void setSellerId(int sellerId)
    {
        this.sellerId = sellerId;
    }
}
