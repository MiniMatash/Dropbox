package com.minimatash.servlets.fileWork;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateFolderServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String folderName = request.getParameter("folderName");

    }
}
