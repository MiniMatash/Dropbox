package com.minimatash.servlets;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainPageServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            if(request.getSession().getAttribute("login")!=null) {
                request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(request, response);
            }else{
                response.sendRedirect("/");
            }
        } catch (IOException | ServletException e) {
            logger.error(e.getMessage(),e);
        }
    }

}
