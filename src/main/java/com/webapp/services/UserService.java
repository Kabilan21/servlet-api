package com.webapp.services;

import java.sql.Connection;

public class UserService {
    private static final Connection connection = ModelService.getConnection();
    public UserService(){
        createTableIfNotExists();
    }

    void createTableIfNotExists(){
        String query = "create table user(id int(11) not null auto_increment,name varchar(120) not null,password varchar(40) not null,created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,primary key(id));";
    }

}
