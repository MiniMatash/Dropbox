package com.minimatash.servlets;

import com.minimatash.servlets.fileWork.CreateFolderServlet;
import com.minimatash.servlets.fileWork.GetFileTreeServlet;
import com.minimatash.servlets.loginSystem.LoginServlet;
import com.minimatash.servlets.loginSystem.LogoutServlet;
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
        servletMap.put("/logout", new LogoutServlet());
        servletMap.put("/home", new MainPageServlet());
        servletMap.put("/getFileTree", new GetFileTreeServlet());
        servletMap.put("/createFolder", new CreateFolderServlet());
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServlet httpServlet;
        if(request.getRequestURI().indexOf("/",1)!=-1) {
            httpServlet = servletMap.get(request.getRequestURI().substring(0, request.getRequestURI().indexOf("/", 1)));
        }else{
            httpServlet = servletMap.get(request.getRequestURI());
        }
        if(httpServlet!=null)
            httpServlet.service(request,response);
    }
}
