package com.minimatash.servlets.fileWork;

import com.minimatash.fileStructure.FileWork;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

public class UploadFileServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                DiskFileItemFactory factory = new DiskFileItemFactory();


                ServletFileUpload upload = new ServletFileUpload(factory);

                List<FileItem> items = upload.parseRequest(request);
                String currentLocation = null;
                for (FileItem elem : items) {
                    if (elem.isFormField() && elem.getFieldName().equals("path")) {
                        currentLocation = elem.getString();
                        String path = request.getSession().getAttribute("homePath") + currentLocation;
                        File repository = new File(path);
                        factory.setRepository(repository);
                    }
                    else{

                    }
                }

            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
}