package com.minimatash;

import com.minimatash.servlets.MainServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StartupPlain implements ServletContextListener {

    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        if(!Files.exists(Paths.get(MainServlet.dropboxPath))){
            try {
                Files.createDirectories(Paths.get(MainServlet.dropboxPath));
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
        }
        logger.info("Starting up!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Shutting down!");
    }
}