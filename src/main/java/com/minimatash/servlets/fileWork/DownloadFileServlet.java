package com.minimatash.servlets.fileWork;

import com.minimatash.servlets.MainServlet;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created on 16.02.17.
 */
public class DownloadFileServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            String uri = request.getRequestURI();
            String fileName = uri.substring(uri.lastIndexOf("/")+1);
            String filePath = MainServlet.dropboxPath + request.getSession().getAttribute("login")+
                    uri.replace("/downloadFile/home","");
            String mimeType = Files.probeContentType(Paths.get(filePath));
            response.setContentType(mimeType);
            response.setHeader( "Content-Disposition", "filename=\"" + fileName+"\"");
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            response.setHeader("Content-Transfer-Encoding", "binary");
            File file = new File(filePath);
            response.setHeader("Content-Length",Long.toString(file.length()));
            FileInputStream fileInputStream  = new FileInputStream(file);
            ServletOutputStream servletOutputStream = response.getOutputStream();
            IOUtils.copy(fileInputStream,servletOutputStream);
            servletOutputStream.flush();
            servletOutputStream.close();
        }catch (Exception e){

        }
    }
}
