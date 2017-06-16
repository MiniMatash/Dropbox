package com.minimatash.servlets.pageLoaders;

import com.minimatash.encryption.Encrypt;
import com.minimatash.service.LoginService;
import com.minimatash.service.impl.LoginServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class  LoginServlet extends HttpServlet {

    private LoginService loginService = new LoginServiceImpl();
    private Logger logger = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("login")==null) {
            if(request.getRequestURI().equals("/"))
                request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
            else
                response.sendRedirect("/");
        }else {
            try {
                response.sendRedirect("/home");
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
            String login = request.getParameter("login");
            try {
                Integer password = Encrypt.encrypt(request.getParameter("password")).hashCode();
                if (loginService.login(login, password)) {
                    request.getSession();
                    request.getSession().setAttribute("login",login);
                    request.getSession().setAttribute("password",password);
                    request.getSession().setAttribute("homePath",System.getProperty("user.home")+"/dropbox/"+login);
                    response.sendRedirect("/home");
                } else{
                    request.setAttribute("failedLogin", login);
                    request.setAttribute("failedPassword", password);
                    request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
                }
            } catch (SQLException | IOException | NoSuchAlgorithmException | ServletException e) {
                logger.error(e.getMessage(),e);
            }
    }
}
