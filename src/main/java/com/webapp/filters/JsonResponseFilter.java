package com.webapp.filters;


import com.google.gson.Gson;

import javax.servlet.*;
import java.io.IOException;

public class JsonResponseFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
        Gson gson = new Gson();
        servletResponse.setContentType("application/json");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.getWriter().write(gson.toJson(servletRequest.getAttribute("response")));
        servletResponse.getWriter().flush();
    }
}
