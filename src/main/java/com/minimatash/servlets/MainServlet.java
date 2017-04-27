package com.minimatash.servlets;

import com.minimatash.servlets.fileWork.*;
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

    public static final String dropboxPath = "/home/" + System.getProperty("user.name") + "/dropbox/" ;

    private Map<String, HttpServlet> servletMap = new HashMap<String, HttpServlet>();

    {
        servletMap.put("/", new LoginServlet());
        servletMap.put("/registration", new RegistrationServlet());
        servletMap.put("/logout", new LogoutServlet());
        servletMap.put("/home", new MainPageServlet());
        servletMap.put("/getFileTree", new GetFileTreeServlet());
        servletMap.put("/createFolder", new CreateFolderServlet());
        servletMap.put("/deleteElement", new DeleteElementServlet());
        servletMap.put("/uploadFile", new UploadFileServlet());
        servletMap.put("/downloadFile", new DownloadFileServlet());
        servletMap.put("/moveElement", new MoveElementServlet());
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServlet httpServlet = servletMap.get("/");
        String uri = request.getRequestURI();
        if(request.getSession().getAttribute("login") != null) {
            if (uri.lastIndexOf("/") != 0) {
                httpServlet = servletMap.get(uri.substring(0, uri.indexOf("/", 1)));
            } else {
                httpServlet = servletMap.get(uri);
            }
        }
        httpServlet.service(request, response);
    }
}
