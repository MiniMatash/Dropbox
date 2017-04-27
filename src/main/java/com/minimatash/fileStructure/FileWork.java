package com.minimatash.fileStructure;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileWork {
    public static void createUserFolder(String login) throws IOException {
        Path userPath = Paths.get(System.getProperty("user.home") + "/dropbox/" + login);
        Files.createDirectory(userPath);
        Path firstFilePath = Paths.get(System.getProperty("user.home") + "/dropbox/" + login + "/README.txt");
        Files.createFile(firstFilePath);
    }

    public static Boolean checkExistence(String url){
        File dir = new File(url);
        File[] files = dir.listFiles();
        return files != null;
    }

    public static List<Map<String, String>> getFileTree(String url) throws IOException {
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

    public static String createFolder(String path) throws IOException {
        if (!Files.exists(Paths.get(path))) {
            Files.createDirectory(Paths.get(path));
            return "success";
        } else {
            return "folder already exist";
        }
    }

    public static String deleteElement(String path) throws IOException {
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

    public static String uploadFile(List<FileItem> items, String path) throws Exception {
        String currentLocation;
        File file;
        FileItem fileItem = null;
        for (FileItem elem : items) {
            if (elem.isFormField() && elem.getFieldName().equals("path")) {
                currentLocation = elem.getString().substring(5);
                path += currentLocation;
            } else {
                fileItem = elem;
            }
        }
        if (fileItem != null) {
            String filePath = path +"/"+ fileItem.getName();
            if(new File(filePath).exists())
                return "File with name "+ fileItem.getName() +" already exist";
            file = new File(filePath);
            fileItem.write(file);
            return "success";
        }
        return "Something wrong, contact administrator";
    }

    public static String moveElement(String sourceFolder, String destinationFolder, String fileName){
        try {
            FileUtils.moveFileToDirectory(new File(sourceFolder + "/" + fileName), new File(destinationFolder), false);
            return "success";
        }catch (IOException e){
            return "false";
        }
    }
}
