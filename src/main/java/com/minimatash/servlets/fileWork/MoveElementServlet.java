package com.minimatash.servlets.fileWork;

import com.minimatash.service.FileWorkService;
import com.minimatash.service.impl.FileWorkServiceImpl;
import com.minimatash.servlets.MainServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class MoveElementServlet extends HttpServlet {

    private FileWorkService fileWork = new FileWorkServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String sourcePath = MainServlet.dropboxPath + request.getSession().getAttribute("login")+request.getParameter("currentPath").substring(5);
        String destinationPath = MainServlet.dropboxPath + request.getSession().getAttribute("login")+ File.separator +request.getParameter("destination");
        String fileName = request.getParameter("fileName");
        String result = fileWork.moveElement(sourcePath,destinationPath,fileName);
        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}