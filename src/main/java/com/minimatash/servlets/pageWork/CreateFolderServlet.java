package com.minimatash.servlets.pageWork;

import com.minimatash.service.FileWorkService;
import com.minimatash.service.impl.FileWorkServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateFolderServlet extends HttpServlet {

    private FileWorkService fileWork = new FileWorkServiceImpl();
    private Logger logger = Logger.getLogger(this.getClass());

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String folderName = request.getParameter("folderName");
        String currentLocation = request.getParameter("currentLocation").substring(5);
        String path = request.getSession().getAttribute("homePath")+currentLocation+"/"+folderName;
        try {
            String result = fileWork.createFolder(path);
            if(result.equals("success")){
                response.setStatus(200);
            }else {
                response.setStatus(500);
            }
            response.getWriter().write(result);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }
    }
}
