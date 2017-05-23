package com.minimatash;

import com.minimatash.servlets.MainServlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StartupPlain implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        if(!Files.exists(Paths.get(MainServlet.dropboxPath))){
            try {
                Files.createDirectories(Paths.get(MainServlet.dropboxPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Starting up!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Shutting down!");
    }
}