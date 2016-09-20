package com.minimatash.servlets.loginSystem;

import com.minimatash.encryption.Encrypt;
import com.minimatash.service.LoginService;
import com.minimatash.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    private LoginService loginService = new LoginServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("login")==null) {
            request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
        }else {
            try {
                response.sendRedirect("/home");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
            String login = request.getParameter("login");
            try {
                Integer password = Encrypt.encrypt(request.getParameter("password")).hashCode();
                if (loginService.getLog(login, password)) {
                    request.getSession();
                    request.getSession().setAttribute("login",login);
                    request.getSession().setAttribute("password",password);
                    request.getSession().setAttribute("homePath","/home/"+System.getProperty("user.name")+"/dropbox/"+login);
                    response.sendRedirect("/home");
                } else{
                    request.setAttribute("failedLogin", login);
                    request.setAttribute("failedPassword", password);
                    request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            }
    }
}
