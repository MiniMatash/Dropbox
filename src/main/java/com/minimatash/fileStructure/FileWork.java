package com.minimatash.fileStructure;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileWork {
    public static void createUserFolder(String login) throws IOException {
        Path userPath = Paths.get("/home/"+System.getProperty("user.name")+"/dropbox/"+login);
        Files.createDirectory(userPath);
        Path firstFilePath = Paths.get("/home/"+System.getProperty("user.name")+"/dropbox/"+login+"/README.txt");
        Files.createFile(firstFilePath);
    }

    public static List<Map<String,String>> getFileTree(String url) throws IOException {
        /*String result = "{\"files\": [";
        File dir = new File(url);
        File[] files = dir.listFiles();
        for (File file : files) {
            result+="{\"name\": \""+file.getName()+"\", ";
            if(file.getName().contains(".")) {
                result+="\"type\": \""+file.getName().substring(file.getName().indexOf("."))+"\", ";
            }else{
                result+="\"type\": \"directory\", ";
            }
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            result+="\"modificationDate\": \""+sdf.format(file.lastModified())+"\"},";
        }
        result=result.substring(0,result.lastIndexOf(","));
        result+="]}";*/
        List<Map<String,String>> result = new ArrayList<Map<String, String>>();
        File dir = new File(url);
        File[] files = dir.listFiles();
        for (File file : files) {
            Map<String,String> elem = new HashMap<String, String>();
            elem.put("name",file.getName());
            if(file.getName().contains(".")) {
                elem.put("type",file.getName().substring(file.getName().indexOf(".")));
            }else{
                elem.put("type","directory");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            elem.put("modificationDate",sdf.format(file.lastModified()));
            result.add(elem);
        }
        return result;
    }
}
