package com.minimatash.fileStructure;

import org.apache.commons.fileupload.FileItem;

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

    public static List<Map<String, String>> getFileTree(String url) throws IOException {
        List<Map<String, String>> result = new ArrayList<>();
        File dir = new File(url);
        File[] files = dir.listFiles();
        for (File file : files) {
            Map<String, String> elem = new HashMap<>();
            elem.put("name", file.getName());
            if (file.getName().contains(".")) {
                elem.put("type", file.getName().substring(file.getName().indexOf(".") + 1));
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

    public static String deleteFolder(String path) throws IOException {
        if (Files.exists(Paths.get(path))) {
            Files.delete(Paths.get(path));
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
                return "File with name"+ fileItem.getName() +"already exist";
            file = new File(filePath);
            fileItem.write(file);
            return "Upload successful";
        }
        return "Something wrong, contact administrator";
    }
}
