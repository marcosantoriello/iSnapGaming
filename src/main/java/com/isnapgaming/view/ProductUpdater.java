package com.isnapgaming.view;

import java.io.*;
import java.sql.SQLException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import com.isnapgaming.UserManagement.ProductManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;

import com.isnapgaming.StorageManagement.DAO.ProductDAO;
import com.isnapgaming.ProductManagement.Product;

@WebServlet(name = "ProductUpdater", value = "/ProductUpdater")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class ProductUpdater extends HttpServlet {

    private static final String UPLOAD_DIR = "products";

    private boolean isImageFile(Part filePart) {
        String contentType = filePart.getContentType();
        return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png")
                || contentType.equals("image/gif") || contentType.equals("image/jpg"));
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DataSource ds = (DataSource) request.getServletContext().getAttribute("DataSource");
        ProductManager productManager = new ProductManager();

        int productId = Integer.parseInt(request.getParameter("productId"));
        int productCode = Integer.parseInt(request.getParameter("productCode"));
        String nameProduct = request.getParameter("nameProduct");
        String softwareHouseProduct = request.getParameter("softwareHouseProduct");
        String platformProduct = request.getParameter("platformProduct");
        String categoryProduct = request.getParameter("categoryProduct");
        String pegiProduct = request.getParameter("pegiProduct");
        int releaseYearProduct = Integer.parseInt(request.getParameter("releaseYearProduct"));
        int quantityProduct = Integer.parseInt(request.getParameter("quantityProduct"));
        int priceProduct = Integer.parseInt(request.getParameter("priceProduct"));
        String currentImage = request.getParameter("currentImage");

        Part filePart = request.getPart("imageProduct");
        String relativeFileName = currentImage;


        if (filePart != null && filePart.getSize() > 0) {
            if (!isImageFile(filePart)) {
                request.setAttribute("error", "Please upload a valid image file (jpg, jpeg, png, gif)");
                request.getRequestDispatcher("/updateProduct").forward(request, response);
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
                relativeFileName = sanitizedFileName;
            } catch (IOException e) {
                request.setAttribute("error", "Error loading image");
                request.getRequestDispatcher("/updateProduct").forward(request, response);
                return;
            }
        }






        Product product = new Product();
        product.setId(productId);
        product.setProdCode(productCode);
        product.setName(nameProduct);
        product.setSoftwareHouse(softwareHouseProduct);
        product.setPlatform(Product.Platform.valueOf(platformProduct));
        product.setCategory(Product.Category.valueOf(categoryProduct));
        product.setPegi(Product.Pegi.valueOf(pegiProduct));
        product.setReleaseYear(releaseYearProduct);
        product.setQuantity(quantityProduct);
        product.setPrice(priceProduct);
        product.setImagePath(relativeFileName);
        product.setAvailable(true);

        try {
            productManager.updateProduct(product,ds);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        request.setAttribute("product", product);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/productManagerDashboard.jsp");
        dispatcher.forward(request, response);
    }
}