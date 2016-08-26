package com.minimatash.servlets.loginSystem;

import com.minimatash.service.LoginService;
import com.minimatash.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    private LoginService loginService = new LoginServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("login")==null) {
            request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
        }else {
            try {
                response.sendRedirect("/mainPage");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            try {
                if (loginService.getLog(login, password)) {
                    request.getSession();
                    request.getSession().setAttribute("login",login);
                    request.getSession().setAttribute("password",password);
                    response.sendRedirect("/mainPage");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
