package com.webapp.services;

import com.webapp.models.Seller;
import com.webapp.models.SellerCreateDTO;
import com.webapp.models.SellerViewDTO;

import java.sql.*;

public class SellerService {

    private static final Connection connection = ModelService.getConnection();

    public SellerService() {
        createTableIfNotExists();
    }

    void createTableIfNotExists() {
        String query = "create table if not exists seller(id int(11) not null auto_increment,name varchar(120) not null,password varchar(40) not null,created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,primary key(id));";
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Seller deSerialize(ResultSet resultSet) {
        Seller seller = null;
        try {
            if (resultSet.next()) {
                seller = new Seller(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seller;
    }


    public Seller findById(int id) throws SQLException {
        String query = "select * from seller where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return deSerialize(resultSet);

    }

    public SellerViewDTO insert(SellerCreateDTO sellerCreateDTO) throws SQLException {
        System.out.println(sellerCreateDTO.toString());
        String query = "insert into seller(name,password) values(?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query, new String[]{"id"});
        preparedStatement.setString(1, sellerCreateDTO.name);
        preparedStatement.setString(2, sellerCreateDTO.password);
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        SellerViewDTO sellerViewDTO = new SellerViewDTO();
        if (resultSet.next()) {
            Seller seller = findById(resultSet.getInt(1));
            sellerViewDTO.setId(seller.getId());
            sellerViewDTO.setName(seller.getUsername());
        }
        return sellerViewDTO;
    }
}
