package com.webapp.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import org.json.JSONObject;

import com.webapp.utils.Utility;

public class ProductInsertFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        JSONObject json = new JSONObject(Utility.getBody((HttpServletRequest) servletRequest));
        int quantity = json.getInt("product_quantity");
        if (quantity > 0) {
            if (Integer.parseInt(filterConfig.getInitParameter("threshold")) >= quantity) {
                servletResponse.getWriter().print("Product quantity is to low to get added...");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }

        } else {
            servletResponse.getWriter().print("Product quantity cannot be negative...");
        }
        servletResponse.getWriter().flush();
    }

    @Override
    public void destroy() {
    }
}
