package com.minimatash.service.impl;

import com.minimatash.persistence.FileWorkPersistence;
import com.minimatash.persistence.impl.FileWorkPersistenceImpl;
import com.minimatash.service.FileWorkService;
import org.apache.commons.fileupload.FileItem;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileWorkServiceImpl implements FileWorkService {

    private FileWorkPersistence fileWorkPersistence = new FileWorkPersistenceImpl();

    @Override
    public void createUserFolder(String login) throws IOException {
        fileWorkPersistence.createUserFolder(login);
    }

    @Override
    public List<Map<String, String>> getFileTree(String url) throws IOException {
        return fileWorkPersistence.getFileTree(url);
    }

    @Override
    public String createFolder(String path) throws IOException {
        return fileWorkPersistence.createFolder(path);
    }

    @Override
    public String deleteElement(String path) throws IOException {
        return fileWorkPersistence.deleteElement(path);
    }

    @Override
    public String uploadFile(List<FileItem> items, String path) throws Exception {
        return fileWorkPersistence.uploadFile(items,path);
    }

    @Override
    public String moveElement(String sourceFolder, String destinationFolder, String fileName) {
        return fileWorkPersistence.moveElement(sourceFolder, destinationFolder, fileName);
    }
}
