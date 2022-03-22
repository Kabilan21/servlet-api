package com.webapp.models;

public class SellerCreateDTO {
    public String name;
    public String password;

    public SellerCreateDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "SellerCreateDTO{" + "name='" + name + '\'' + ", password='" + password + '\'' + '}';
    }
}
