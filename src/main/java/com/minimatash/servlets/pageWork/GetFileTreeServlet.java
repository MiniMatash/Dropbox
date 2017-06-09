package com.minimatash.servlets.pageWork;

import com.google.gson.Gson;
import com.minimatash.service.FileWorkService;
import com.minimatash.service.impl.FileWorkServiceImpl;
import com.minimatash.servlets.MainServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GetFileTreeServlet extends HttpServlet {

    private FileWorkService fileWork = new FileWorkServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Map<String, String>> files = null;
            String path = MainServlet.dropboxPath + request.getSession().getAttribute("login");
            if (!request.getParameter("path").equals("/home")) {
                files = fileWork.getFileTree(path + request.getParameter("path").substring(5));
            } else {
                files = fileWork.getFileTree(path);
            }
            if (files != null) {
                String json = new Gson().toJson(files);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
