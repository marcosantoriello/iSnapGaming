package com.isnapgaming.isnapgaming.utils;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;

import com.isnapgaming.isnapgaming.OrderManagement.CustomerOrder;
import com.isnapgaming.isnapgaming.ProductManagement.Product;
import com.isnapgaming.isnapgaming.StorageManagement.DAO.AddressDAO;
import com.isnapgaming.isnapgaming.StorageManagement.DAO.ProductDAO;
import com.isnapgaming.isnapgaming.StorageManagement.DAO.UserDAO;
import com.isnapgaming.isnapgaming.UserManagement.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;

@WebServlet(name = "TestPopolamento", value = "/TestPopolamento")
public class TestPopolamento extends HttpServlet {

    public TestPopolamento() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");

        PrintWriter out = response.getWriter();
        out.println("<p> Inizio del popolamento di prova... </p>");

        // Creazione DataSource necessari
        ProductDAO productDAO = new ProductDAO(ds);
        UserDAO userDAO = new UserDAO(ds);
        AddressDAO addressDAO = new AddressDAO(ds);
        CustomerOrder customerOrder = new CustomerOrder();

        try {
            User user1 = new User();
            user1.setUsername("user1@gmail.com");
            user1.setPassword("password1");
            user1.setFirstName("Marco");
            user1.setLastName("Rossi");
            user1.setDateOfBirth(LocalDate.of(2000, 1, 1));

            User user2 = new User();
            user2.setUsername("user2@gmail.com");
            user2.setPassword("password2");
            user2.setFirstName("Mario");
            user2.setLastName("Bianchi");
            user2.setDateOfBirth(LocalDate.of(2000, 1, 1));

            // Salvataggio delle istanze di User nel database
            int id1 = userDAO.doSave(user1);
            int id2 = userDAO.doSave(user2);

            // Recupero delle istanze di User dal database
            User retrievedUser1 = userDAO.findByKey(id1);
            User retrievedUser2 = userDAO.findByKey(id2);

            // Stampa delle informazioni degli utenti recuperati per verificare che siano stati salvati correttamente
            out.println("<p> User 1: " + retrievedUser1.getUsername() + "</p>");
            out.println("<p> User 2: " + retrievedUser2.getUsername() + "</p>");


            // Prodotti
            Product product1 = new Product();
            product1.setName("Fortnite");
            product1.setSoftwareHouse("Epic Games");
            product1.setPlatform(Product.Platform.PC);
            product1.setPrice(30);
            product1.setQuantity(100);
            product1.setCategory(Product.Category.ACTION);
            product1.setPegi(Product.Pegi.PEGI12);
            product1.setReleaseYear(2017);
            product1.setImagePath("N/A");

            Product product2 = new Product();
            product2.setName("Hell Let Loose");
            product2.setSoftwareHouse("Team17 Digital Ltd");
            product2.setPlatform(Product.Platform.PC);
            product2.setPrice(50);
            product2.setQuantity(100);
            product2.setCategory(Product.Category.ACTION);
            product2.setPegi(Product.Pegi.PEGI16);
            product2.setReleaseYear(2019);
            product2.setImagePath("N/A");

            ProductDAO prodDAO = new ProductDAO(ds);
            int prod1Id = prodDAO.doSave(product1);
            int prod2Id = prodDAO.doSave(product2);

            System.out.println("Product 1 ID: " + prod1Id);
            System.out.println("Product 2 ID: " + prod2Id);

            // Ordini





        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
            out.println("<p> Errore SQL: " + e.getMessage() + "</p>");
        } catch (IllegalArgumentException e) {
            System.out.println("ARGUMENT ERROR: " + e.getMessage());
            out.println("<p> Errore Argomento: " + e.getMessage() + "</p>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}