package com.minimatash.servlets.fileWork;

import com.minimatash.service.FileWorkService;
import com.minimatash.service.impl.FileWorkServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteElementServlet extends HttpServlet {

    private FileWorkService fileWork = new FileWorkServiceImpl();
    private Logger logger = Logger.getLogger(this.getClass());

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String currentLocation = request.getParameter("currentLocation").substring(5);
        String path = request.getSession().getAttribute("homePath")+currentLocation;
        try {
            String result = fileWork.deleteElement(path);
            response.getWriter().write(result);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }
    }

}
