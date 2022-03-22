package com.webapp.services;

import com.webapp.models.Product;
import com.webapp.models.ProductCreateDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService
{
	private static Connection connection = ModelService.getConnection();

	public ProductService()
	{
		createTableIfNotExists();
	}

	void createTableIfNotExists()
	{
		String query = "create table if not exists product(id int(11) not null auto_increment,name varchar(120) not null,quantity int(5) default 0 ,seller_id int(11) not null,foreign key(seller_id) references seller(id),primary key(id));";
		try
		{
			Statement statement = connection.createStatement();
			statement.execute(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public Product deSerialize(ResultSet resultSet) throws SQLException
	{
		Product product = null;
		product = new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4));
		return product;
	}

	public Product insertProduct(ProductCreateDTO productCreateDTO)
	{
		String query = "insert into product(name,quantity,seller_id) values(?,?,?);";
		Product product = null;
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(query, new String[] {"id"});
			preparedStatement.setString(1, productCreateDTO.getName());
			preparedStatement.setInt(2, productCreateDTO.getQuantity());
			preparedStatement.setInt(3, productCreateDTO.getSellerId());
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if(resultSet.next())
				product = new Product(resultSet.getInt(1), productCreateDTO.getName(), productCreateDTO.getQuantity(), productCreateDTO.getSellerId());
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return product;
	}

	public Product getProductById(int id)
	{
		String query = "select * from product where id=?;";
		Product product = null;
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next())
				product = deSerialize(rs);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		return product;
	}

	public boolean deleteProductById(int id)
	{
		String query = "delete from product where id=?;";
		PreparedStatement preparedStatement = null;
		try
		{
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			int result = preparedStatement.executeUpdate();

			return result > 0;

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public Product updateProduct(Product product)
	{
		String query = "update product set name=? ,quantity = ? , seller_id = ? where id=?";
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setInt(2, product.getQuantity());
			preparedStatement.setInt(3, product.getSellerId());
			preparedStatement.setInt(4, product.getId());
			int res = preparedStatement.executeUpdate();
			if(res > 0)
				return getProductById(product.getId());
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public List<Product> getAllProducts()
	{
		List<Product> products = new ArrayList<>();
		String query = "select * from product;";

		try
		{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next())
			{
				products.add(deSerialize(resultSet));
			}

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		return products;

	}

}
