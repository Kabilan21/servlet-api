package com.webapp.controllers;
import com.google.gson.Gson;
import com.webapp.models.Product;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/user")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("hello buddy");
        Product product = new Product(1, "Iphone 6s", 1, 1);
        Gson gson = new Gson();
        HashMap map = new HashMap<String,Object>();
        map.put("id",21);
        int arr[] = {1,2,34};

        String json = gson.toJson(arr);
        resp.getWriter().println(json);

    }
}
