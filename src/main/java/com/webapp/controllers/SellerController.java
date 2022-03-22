package com.webapp.controllers;

import com.google.gson.Gson;
import com.webapp.models.SellerCreateDTO;
import com.webapp.models.SellerViewDTO;
import com.webapp.services.SellerService;
import com.webapp.utils.Utility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import org.json.JSONObject;

@WebServlet(urlPatterns = {"/seller"})
public class SellerController extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject json = new JSONObject(Utility.getBody(req));
        SellerCreateDTO sellerCreateDTO = new SellerCreateDTO(json.getString("username"), json.getString("password"));
        SellerService sellerService = new SellerService();
        try {
            SellerViewDTO sellerViewDTO = sellerService.insert(sellerCreateDTO);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            Gson gson = new Gson();
            resp.getWriter().print(gson.toJson(sellerViewDTO, SellerViewDTO.class));
            resp.getWriter().flush();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
