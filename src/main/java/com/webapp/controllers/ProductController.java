package com.webapp.controllers;

import com.google.gson.Gson;
import com.webapp.models.Product;
import com.webapp.models.ProductCreateDTO;
import com.webapp.services.ProductService;
import com.webapp.utils.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.json.JSONObject;

public class ProductController extends HttpServlet
{
	@Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String[] s = req.getRequestURI().split("/");
		Gson gson = new Gson();
		ProductService productService = new ProductService();
		resp.setContentType("application/json");
		try
		{
			int id = Integer.parseInt(s[s.length - 1]);
			Product product = productService.getProductById(id);
			resp.getWriter().print(gson.toJson(product));
		}
		catch(Exception e)
		{
			resp.getWriter().print(gson.toJson(productService.getAllProducts()));
		}
		resp.getWriter().flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		ProductService productService = new ProductService();
		JSONObject json = new JSONObject(Utility.getBody(req));
		ProductCreateDTO productCreateDTO = new ProductCreateDTO(json.getString("product_name"),
			json.getInt("product_quantity"),
			json.getInt("seller_id")
		);
		Product product = productService.insertProduct(productCreateDTO);
		resp.setContentType("application/json");
		Gson gson = new Gson();
		resp.setStatus(HttpServletResponse.SC_CREATED);
		resp.getWriter().print(gson.toJson(product));
		resp.getWriter().flush();

	}

	@Override protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String[] s = req.getRequestURI().split("/");
		int id = Integer.parseInt(s[s.length - 1]);
		ProductService productService = new ProductService();
		JSONObject json = new JSONObject(Utility.getBody(req));
		Product product = new Product(id, json.getString("name"), json.getInt("quantity"), json.getInt("sellerId"));
		Product rProduct = productService.updateProduct(product);
		resp.setContentType("application/json");
		Gson gson = new Gson();
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.getWriter().print(gson.toJson(rProduct));
		resp.getWriter().flush();

	}

	@Override protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String[] s = req.getRequestURI().split("/");
		int id = Integer.parseInt(s[s.length - 1]);
		ProductService productService = new ProductService();
		boolean result = productService.deleteProductById(id);
		if(result)
		{
			resp.setStatus(HttpServletResponse.SC_OK);
		}
		resp.getWriter().flush();
	}
}
