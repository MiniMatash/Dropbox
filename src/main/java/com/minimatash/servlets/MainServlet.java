package com.minimatash.servlets;

import com.minimatash.servlets.loginSystem.LoginServlet;
import com.minimatash.servlets.loginSystem.RegistrationServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {

    Map<String, HttpServlet> servletMap = new HashMap<String, HttpServlet>();
    {
        servletMap.put("/",new LoginServlet());
        servletMap.put("/registration",new RegistrationServlet());
        servletMap.put("/mainPage", new MainPageServlet());
        servletMap.put("/getFileTree", new GetFileTreeServlet());
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServlet httpServlet = servletMap.get(request.getRequestURI());
        if(httpServlet!=null)
            httpServlet.service(request,response);
    }
}
