package com.minimatash.persistence.impl;

import com.minimatash.persistence.FileWorkPersistence;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileWorkPersistenceImpl implements FileWorkPersistence {

    @Override
    public void createUserFolder(String login) throws IOException {
        Path userPath = Paths.get(System.getProperty("user.home") + File.separator + "dropbox" + File.separator + login);
        Files.createDirectory(userPath);
        Path firstFilePath = Paths.get(System.getProperty("user.home") + File.separator + "dropbox" +
                File.separator  + login + File.separator + "README.txt");
        Files.createFile(firstFilePath);
    }

    @Override
    public Boolean checkExistence(String url){
        File dir = new File(url);
        File[] files = dir.listFiles();
        return files != null;
    }

    @Override
    public List<Map<String, String>> getFileTree(String url) throws IOException {
        List<Map<String, String>> result = new ArrayList<>();
        File dir = new File(url);
        File[] files = dir.listFiles();
        for (File file : files) {
            Map<String, String> elem = new HashMap<>();
            elem.put("name", file.getName());
            if (file.getName().contains(".")) {
                String type =  Files.probeContentType(Paths.get(file.getAbsolutePath()));
                if(type.contains("application")){
                    type = type.substring(type.indexOf("/")+1);
                }else{
                    type = type.substring(0,type.indexOf("/"));
                }
                elem.put("type",type);
            } else {
                elem.put("type", "folder");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            elem.put("modificationDate", sdf.format(file.lastModified()));
            result.add(elem);
        }
        return result;
    }

    @Override
    public String createFolder(String path) throws IOException {
        if (!Files.exists(Paths.get(path))) {
            Files.createDirectory(Paths.get(path));
            return "success";
        } else {
            return "folder already exist";
        }
    }

    @Override
    public String deleteElement(String path) throws IOException {
        if (Files.exists(Paths.get(path))) {
            if(new File(path).isDirectory()){
                FileUtils.deleteDirectory(new File(path));
            }else {
                Files.delete(Paths.get(path));
            }
            return "success";
        } else {
            return "error";
        }
    }

    @Override
    public String uploadFile(List<FileItem> items, String path) throws Exception {
        String currentLocation;
        File file;
        FileItem fileItem = null;
        StringBuilder pathBuilder = new StringBuilder(path);
        for (FileItem elem : items) {
            if (elem.isFormField() && elem.getFieldName().equals("path")) {
                currentLocation = elem.getString().substring(5);
                pathBuilder.append(currentLocation);
            } else {
                fileItem = elem;
            }
        }
        path = pathBuilder.toString();
        if (fileItem != null) {
            String filePath = path + File.separator + fileItem.getName();
            if(new File(filePath).exists())
                return "File with name "+ fileItem.getName() +" already exist";
            file = new File(filePath);
            fileItem.write(file);
            return "success";
        }
        return "Something wrong, contact administrator";
    }

    @Override
    public String moveElement(String sourceFolder, String destinationFolder, String fileName){
        try {
            FileUtils.moveFileToDirectory(new File(sourceFolder + File.separator + fileName), new File(destinationFolder), false);
            return "success";
        }catch (IOException e){
            return "false";
        }
    }
}
