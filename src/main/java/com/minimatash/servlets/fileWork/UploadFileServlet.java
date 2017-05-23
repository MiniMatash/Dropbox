package com.minimatash.servlets.fileWork;

import com.minimatash.service.FileWorkService;
import com.minimatash.service.impl.FileWorkServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UploadFileServlet extends HttpServlet {

    private FileWorkService fileWork = new FileWorkServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                DiskFileItemFactory factory = new DiskFileItemFactory();

                ServletFileUpload upload = new ServletFileUpload(factory);

                List<FileItem> items = upload.parseRequest(request);
                String path = request.getSession().getAttribute("homePath").toString();
                String result = fileWork.uploadFile(items,path);
                response.setHeader("uploadResult",result);
                response.setContentType("application/json");
                response.getWriter().write("{\"result\":\""+result+"\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
