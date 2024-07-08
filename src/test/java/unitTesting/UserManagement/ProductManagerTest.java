package unitTesting.UserManagement;

import com.isnapgaming.ProductManagement.Product;
import com.isnapgaming.UserManagement.ProductManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import testing.RetrieveCredentials;

import static org.mockito.Mockito.when;
import static testing.SQLScript.executeSQLScript;
import javax.sql.DataSource;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
public class ProductManagerTest {
    private ProductManager productManager;
    private Connection conn;
    private DataSource ds;
    private Product product;

    @BeforeEach
    void setUp() throws ClassNotFoundException, SQLException, InvalidParameterException {
        productManager = new ProductManager();


        String[] crendentials = RetrieveCredentials.retrieveCredentials("src/test/credentials.xml");

        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", crendentials[0], crendentials[1]);

        executeSQLScript("src/test/db/init.sql", conn);
        conn.setCatalog("iSnapGaming");

        ds = Mockito.mock(DataSource.class);
        Answer<Connection> getConnection = new Answer<Connection>() {
            @Override
            public Connection answer(InvocationOnMock invocationOnMock) throws Throwable {
                String[] crendentials = RetrieveCredentials.retrieveCredentials("src/test/credentials.xml");

                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iSnapGaming", crendentials[0], crendentials[1]);
                conn.setCatalog("iSnapGaming");

                return conn;
            }
        };
        when(ds.getConnection()).thenAnswer(getConnection);


    }
    @AfterEach
    public void tearDown() throws SQLException {
        conn.close();
    }
    @Test
    void addProduct_A1() throws SQLException {
        product=Mockito.mock(Product.class);
        Mockito.when(product.getProdCode()).thenReturn(635);
        Mockito.when(product.getId()).thenReturn(1);
        Mockito.when(product.getCategory()).thenReturn(Product.Category.ADVENTURE);
        Mockito.when(product.getPlatform()).thenReturn(Product.Platform.PS4);
        Mockito.when(product.getPegi()).thenReturn(Product.Pegi.PEGI3);
        Mockito.when(product.getName()).thenReturn("fifa");
        Mockito.when(product.getSoftwareHouse()).thenReturn("Chiara");
        Mockito.when(product.getPrice()).thenReturn(20);
        Mockito.when(product.getQuantity()).thenReturn(3);
        Mockito.when(product.getReleaseYear()).thenReturn(2020);
        Mockito.when(product.getImagePath()).thenReturn("cioa");
        Mockito.when(product.isAvailable()).thenReturn(true);
        executeSQLScript("src/test/db/createDbForTest.sql", conn);

        productManager.addProduct(product,ds);
        Product retrieveProduct=productManager.getProductByProdCode(635,ds);
        assertEquals(product.getProdCode(),retrieveProduct.getProdCode());
        assertEquals(product.getId(),retrieveProduct.getId());
        assertEquals(product.getCategory(),retrieveProduct.getCategory());
        assertEquals(product.getPlatform(),retrieveProduct.getPlatform());
        assertEquals(product.getPegi(),retrieveProduct.getPegi());
        assertEquals(product.getName(),retrieveProduct.getName());
        assertEquals(product.getSoftwareHouse(),retrieveProduct.getSoftwareHouse());
        assertEquals(product.getPrice(),retrieveProduct.getPrice());
        assertEquals(product.getQuantity(),retrieveProduct.getQuantity());
        assertEquals(product.getReleaseYear(),retrieveProduct.getReleaseYear());
        assertEquals(product.getImagePath(),retrieveProduct.getImagePath());
        assertEquals(product.isAvailable(),retrieveProduct.isAvailable());


    }
    @Test
    void addProduct_A2(){
        try {
            assertThrows(Exception.class,()-> productManager.addProduct(null,ds));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void removeProduct_A1() throws SQLException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/UserManagement/removeProduct_A1.sql", conn);

        product=Mockito.mock(Product.class);
        Mockito.when(product.getProdCode()).thenReturn(751);

        product = productManager.getProductByProdCode(751, ds);
        productManager.removeProduct(product,ds);
        assertFalse(productManager.getProductByProdCode(751,ds).isAvailable());
    }
    @Test
    void removeProduct_A2(){
        try {
            assertThrows(Exception.class,()-> productManager.removeProduct(null,ds));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void updateProduct_A1() throws SQLException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/UserManagement/updateProduct_A1.sql", conn);

        product=Mockito.mock(Product.class);
        Mockito.when(product.getProdCode()).thenReturn(635);
        Mockito.when(product.getId()).thenReturn(1);
        Mockito.when(product.getCategory()).thenReturn(Product.Category.SPORTS);
        Mockito.when(product.getPlatform()).thenReturn(Product.Platform.PC);
        Mockito.when(product.getPegi()).thenReturn(Product.Pegi.PEGI3);
        Mockito.when(product.getName()).thenReturn("Fifa23");
        Mockito.when(product.getSoftwareHouse()).thenReturn("EA Games");
        Mockito.when(product.getPrice()).thenReturn(55);
        Mockito.when(product.getQuantity()).thenReturn(10);
        Mockito.when(product.getReleaseYear()).thenReturn(2023);
        Mockito.when(product.isAvailable()).thenReturn(true);

        // Field to update
        Mockito.when(product.getPrice()).thenReturn(7);
        Mockito.when(product.isAvailable()).thenReturn(false);

        productManager.updateProduct(product,ds);
        Product retrieveProduct=productManager.getProductByProdCode(635,ds);
        assertEquals(7, retrieveProduct.getPrice());
        assertFalse(retrieveProduct.isAvailable());
    }
    @Test
    void updateProduct_A2(){
        try {
            assertThrows(Exception.class,()-> productManager.updateProduct(null,ds));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void makeProductAvailable_A1()throws SQLException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/UserManagement/makeProductAvailable_A1.sql", conn);

        product=Mockito.mock(Product.class);
        Mockito.when(product.getProdCode()).thenReturn(444);
        Mockito.when(product.getId()).thenReturn(1);
        Mockito.when(product.getCategory()).thenReturn(Product.Category.SPORTS);
        Mockito.when(product.getPlatform()).thenReturn(Product.Platform.PC);
        Mockito.when(product.getPegi()).thenReturn(Product.Pegi.PEGI3);
        Mockito.when(product.getName()).thenReturn("Fifa23");
        Mockito.when(product.getSoftwareHouse()).thenReturn("EA Games");
        Mockito.when(product.getPrice()).thenReturn(55);
        Mockito.when(product.getQuantity()).thenReturn(10);
        Mockito.when(product.getReleaseYear()).thenReturn(2023);

        // Field to update
        Mockito.when(product.isAvailable()).thenReturn(true);

        productManager.makeProductAvailable(product,ds);
        assertEquals(product.isAvailable(), productManager.getProductByProdCode(444,ds).isAvailable());
    }

    @Test
    void makeProductAvailable_A2(){
        Product product=null;
        try {
            assertThrows(Exception.class,()-> productManager.makeProductAvailable(product,ds));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getProductByProdCode_A1()throws SQLException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/UserManagement/getProductByProdCode_A1.sql", conn);

        product=productManager.getProductByProdCode(222,ds);
        productManager.getProductByProdCode(222,ds);
        assertEquals(product.getProdCode(), productManager.getProductByProdCode(222,ds).getProdCode());
    }

    @Test
    void getProductByProdCode_A2(){
        Product product=null;
        try {
            assertThrows(Exception.class,()-> productManager.getProductByProdCode(0,ds));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
