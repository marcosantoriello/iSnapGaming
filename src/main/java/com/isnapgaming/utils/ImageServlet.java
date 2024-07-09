package com.isnapgaming.utils;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "ImageServlet", value = "/ImageServlet")
public class ImageServlet extends HttpServlet {

    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameImg = request.getParameter("image");
        String path = getServletContext().getRealPath("/"+"app_imgs"+"/"+"products"+File.separator+nameImg);
        response.setContentType("image/*");
        File file = new File(path);
        if(!file.exists()) {
            response.sendError(404);
        }else {
            FileInputStream is = new FileInputStream(file);
            response.getOutputStream().write(is.readAllBytes());
            is.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}