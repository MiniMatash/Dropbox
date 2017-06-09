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

/**
 * Created by on 23.06.16.
 */
public class RegistrationServlet extends HttpServlet {

    private LoginService loginService = new LoginServiceImpl();
    private Logger logger = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("login")==null) {
            request.getRequestDispatcher("WEB-INF/jsp/registration.jsp").forward(request, response);
        }else {
            try {
                response.sendRedirect("/home");
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String login = request.getParameter("registerLogin");
        try {
            Integer password = Encrypt.encrypt(request.getParameter("registerPassword")).hashCode();
            if(loginService.registerLog(login,password)){
                request.getSession();
                request.getSession().setAttribute("login",login);
                request.getSession().setAttribute("password",password);
                request.getSession().setAttribute("homePath",System.getProperty("user.home")+"/dropbox/"+login);
                response.sendRedirect("/home");
            } else{
                request.setAttribute("failedLogin", login);
                request.setAttribute("failedPassword", password);
                request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request,response);
            }
        } catch (SQLException | IOException | ServletException | NoSuchAlgorithmException e) {
            logger.error(e.getMessage(),e);
        }
    }
}
