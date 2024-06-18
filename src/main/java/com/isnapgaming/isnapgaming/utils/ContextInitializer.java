package com.isnapgaming.isnapgaming.utils;

import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;

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
        DataSource dataSource = null;

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
