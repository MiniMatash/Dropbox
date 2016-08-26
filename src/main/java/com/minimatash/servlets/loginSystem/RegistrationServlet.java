package com.minimatash.servlets.loginSystem;

import com.minimatash.fileStructure.FileWork;
import com.minimatash.service.LoginService;
import com.minimatash.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

/**
 * Created by on 23.06.16.
 */
public class RegistrationServlet extends HttpServlet {

    private LoginService loginService = new LoginServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/registration.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String login = request.getParameter("registerLogin");
        String password = request.getParameter("registerPassword");
        try {
            if(loginService.registerLog(login,password)){
                response.sendRedirect("/mainPage");
            } else{
                request.setAttribute("failedLogin", login);
                request.setAttribute("failedPassword", password);
                request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
