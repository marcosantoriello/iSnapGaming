package com.isnapgaming.view;

import java.io.*;
import java.sql.SQLException;

import java.nio.file.Files;
import java.util.UUID;

import com.isnapgaming.UserManagement.ProductManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.Part;
import java.nio.file.Paths;

import  com.isnapgaming.ProductManagement.Product;
import com.isnapgaming.StorageManagement.DAO.ProductDAO;

import javax.sql.DataSource;

@WebServlet(name = "AddProduct", value = "/AddProduct")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class AddProduct extends HttpServlet {
    private static final String UPLOAD_DIR = "products";

    private boolean isImageFile(Part filePart) {
        String contentType = filePart.getContentType();
        return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png")
                || contentType.equals("image/gif") || contentType.equals("image/jpg"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataSource ds = (DataSource) request.getServletContext().getAttribute("DataSource");

        int productCode = Integer.parseInt(request.getParameter("productCode"));
        String nameProduct = request.getParameter("nameProduct");
        String softwareHouseProduct = request.getParameter("softwareHouseProduct");
        Product.Platform platformProduct = Product.Platform.valueOf(request.getParameter("platformProduct"));
        Product.Category categoryProduct = Product.Category.valueOf(request.getParameter("categoryProduct"));
        String pegiProduct = request.getParameter("pegiProduct");
        int releaseYearProduct = Integer.parseInt(request.getParameter("releaseYearProduct"));
        int quantityProduct = Integer.parseInt(request.getParameter("quantityProduct"));
        int priceProduct = Integer.parseInt(request.getParameter("priceProduct"));

        Part filePart = request.getPart("imageProduct");
        if (filePart == null || !isImageFile(filePart)) {
            request.setAttribute("error", "Please upload a valid image file (jpg, jpeg, png, gif)");
            request.getRequestDispatcher("/addProduct.jsp").forward(request, response);
            return;
        }

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String applicationPath = getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

        File uploadDir = new File(uploadFilePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String sanitizedFileName = fileName.replaceAll("\\s+", "_");
        String filePath = uploadFilePath + File.separator + sanitizedFileName;
        File file = new File(filePath);
        while (file.exists()) {
            String uniqueID = UUID.randomUUID().toString();
            sanitizedFileName = uniqueID + "_" + sanitizedFileName;
            filePath = uploadFilePath + File.separator + sanitizedFileName;
            file = new File(filePath);
        }

        try {
            Files.copy(filePart.getInputStream(), Paths.get(filePath));
        } catch (IOException e) {
            request.setAttribute("errorMessage", "Error loading image");
            request.getRequestDispatcher("/addProduct.jsp").forward(request, response);
            return;
        }

        String relativeFilePath = UPLOAD_DIR + File.separator + sanitizedFileName;


        Product product = Product.makeProduct(productCode, nameProduct, softwareHouseProduct, platformProduct, priceProduct, quantityProduct, categoryProduct, Product.Pegi.valueOf(pegiProduct),
                releaseYearProduct, relativeFilePath, true);

        ProductDAO productDAO = null;
        boolean exists = false;

        try {
            productDAO = new ProductDAO(ds);
            Product tempProduct = productDAO.findByProdCode(product.getProdCode());

            if (tempProduct != null && product.getProdCode() == tempProduct.getProdCode()) {
                exists = true;
            }
        } catch (SQLException e) {
            // Do nothing
        }


        if(!exists) {
            ProductManager productManager = new ProductManager();

            try {
                productManager.addProduct(product, ds);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            request.setAttribute("product", product);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product.jsp");
            dispatcher.forward(request, response);
        }else {
            request.setAttribute("error", "That product with this code already exists.");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/addProduct.jsp");
            dispatcher.forward(request, response);
        }
    }
}