package com.minimatash.servlets.fileWork;

import com.minimatash.service.FileWorkService;
import com.minimatash.service.impl.FileWorkServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class CreateFolderServlet extends HttpServlet {

    private FileWorkService fileWork = new FileWorkServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String folderName = request.getParameter("folderName");
        String currentLocation = request.getParameter("currentLocation").substring(5);
        String path = request.getSession().getAttribute("homePath")+currentLocation + File.separator + folderName;
        try {
            String result = fileWork.createFolder(path);
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
