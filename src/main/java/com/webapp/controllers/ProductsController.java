package com.webapp.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.webapp.models.Product;
import com.webapp.services.ProductService;

public class ProductsController
	extends HttpServlet
{

	@Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		ProductService productService = new ProductService();
		List<Product> products = productService.getAllProducts();
		resp.setContentType("application/json");
		Gson gson = new Gson();
		resp.getWriter().print(gson.toJson(products));
		resp.getWriter().flush();
	}
}
