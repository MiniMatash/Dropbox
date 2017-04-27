package com.minimatash.servlets.fileWork;

import com.minimatash.fileStructure.FileWork;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteElementServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String currentLocation = request.getParameter("currentLocation").substring(5);
        String path = request.getSession().getAttribute("homePath")+currentLocation;
        try {
            String result = FileWork.deleteElement(path);
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
