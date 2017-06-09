package com.minimatash.servlets.pageWork;

import com.minimatash.service.FileWorkService;
import com.minimatash.service.impl.FileWorkServiceImpl;
import com.minimatash.servlets.MainServlet;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MoveElementServlet extends HttpServlet {

    private FileWorkService fileWork = new FileWorkServiceImpl();
    private Logger logger = Logger.getLogger(this.getClass());

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String sourcePath = MainServlet.dropboxPath + request.getSession().getAttribute("login")+request.getParameter("currentPath").substring(5);
        String destinationPath;
        if(request.getParameter("destination").equals(" "))
            destinationPath = MainServlet.dropboxPath + request.getSession().getAttribute("login");
        else
            destinationPath = MainServlet.dropboxPath + request.getSession().getAttribute("login")+"/"+request.getParameter("destination");
        String fileName = request.getParameter("fileName");
        String result = fileWork.moveElement(sourcePath,destinationPath,fileName);
        if(result.equals("success")){
            response.setStatus(200);
        }else {
            response.setStatus(500);
        }
        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }
    }
}