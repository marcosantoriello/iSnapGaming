package com.isnapgaming.utils;

import com.isnapgaming.StorageManagement.DAO.ImageDAO;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;

import java.io.File;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;


@WebListener
public class ContextInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("Initializing Context and DataSource...");

        ServletContext context = event.getServletContext();
        String tomcatRootDir = context.getRealPath("/");
        DataSource dataSource = null;

        // Setting Images directory
        try {
            ImageDAO.setImagesDirectory(tomcatRootDir + "..\\Images");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        File file = new File(ImageDAO.getImagesDirectory());
        System.out.println(ImageDAO.getImagesDirectory());
        if(!file.exists() || !file.isDirectory()) {
            boolean r = file.mkdir();
            if(! r)
                throw new RuntimeException("Warning: Images directory not created!");
        }

        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/iSnapGaming");

        } catch (NamingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        context.setAttribute("DataSource", dataSource);
        System.out.println("Done. DataSource: " + dataSource);

    }
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("Destroying Context and DataSource...");

        ServletContext context = event.getServletContext();
        DataSource dataSource = (DataSource) context.getAttribute("DataSource");

        try {
            dataSource.getConnection().close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Done.");
    }
}
