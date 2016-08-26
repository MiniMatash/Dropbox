package com.minimatash.servlets;

import com.google.gson.Gson;
import com.minimatash.fileStructure.FileWork;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GetFileTreeServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Map<String,String>> files= FileWork.getFileTree("/home/"+System.getProperty("user.name")+"/dropbox/admin");
            String json = new Gson().toJson(files);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
