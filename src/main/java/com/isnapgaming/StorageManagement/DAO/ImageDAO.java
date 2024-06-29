package com.isnapgaming.StorageManagement.DAO;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ImageDAO {
    private static String imagesDirectory;
    private static boolean isSet = false;

    /*
    *   This method creates a directory in the specified path. In particular, I'm using this method to create the images directory.
    */
    public static void createDirectory(Path path, String directoryName) throws Exception {

        System.out.println("Creating images directory...");
        String imagePath = path.resolve(directoryName).toString();
        System.out.println(imagePath);
        File file = new File(imagePath);

        if(file.exists())
            throw new Exception("The directory already exists");

        Files.createDirectory(Path.of(imagePath));
    }

    /*
    * This method persists an image in the specified path. In particular, I'm using this method to save the images of the products.
    */
    public static void persistImage(Path path, String fileName, InputStream content) throws Exception {

        String imagePath = path.resolve(fileName).toString();
        File file = new File(imagePath);

        if (file.exists())
            throw new Exception("The image " + imagePath + " already exists");

        Files.copy(content, Path.of(imagePath));
    }

    /*
    *  This method sets the root directory of the tomcat server.
    */
    public static void setImagesDirectory(String rootDirectory) throws Exception {
        if(isSet)
            throw new Exception("Root directory of tomcat already set");

        isSet = true;
        imagesDirectory = rootDirectory;
    }

    /*
    * This method returns the root directory of the tomcat server.
     */
    public static String getImagesDirectory() {
        return imagesDirectory;
    }
}
