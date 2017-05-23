package com.minimatash.persistence;

import org.apache.commons.fileupload.FileItem;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileWorkPersistence {

    void createUserFolder(String login) throws IOException;

    Boolean checkExistence(String url);

    List<Map<String, String>> getFileTree(String url) throws IOException;

    String createFolder(String path) throws IOException;

    String deleteElement(String path) throws IOException;

    String uploadFile(List<FileItem> items, String path) throws Exception;

    String moveElement(String sourceFolder, String destinationFolder, String fileName);
}
