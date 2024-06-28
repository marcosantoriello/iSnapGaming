package UserManagement;

import com.isnapgaming.isnapgaming.ProductManagement.Product;
import com.isnapgaming.isnapgaming.UserManagement.ProductManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import testing.RetrieveCredentials;
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
            Mockito.when(ds.getConnection()).thenAnswer(getConnection);

        }
    @AfterEach
    public void tearDown() throws SQLException {
        conn.close();
    }
    @Test
    void addProduct_A1() throws SQLException {
        Product product=new Product();
        product.setProdCode(1);
        product.setId(1);
        product.setCategory(Product.Category.ADVENTURE);
        product.setPlatform(Product.Platform.PS4);
        product.setPegi(Product.Pegi.PEGI3);
        product.setName("fifa");
        product.setSoftwareHouse("chiara");
        product.setPrice(20);
        product.setQuantity(3);
        product.setReleaseYear(2020);
        product.setImagePath("cioa");
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        productManager.addProduct(product,ds);
        assertEquals(product, productManager.getProductByProdCode(1,ds));

    }
    @Test
    void addProduct_A2(){
        Product product=null;
        try {
            assertThrows(Exception.class,()-> productManager.addProduct(product,ds));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void removeProduct_A1() throws SQLException {
    Product product= new Product();
    int id=1;

    product.setId(1);
    executeSQLScript("src/test/db/createDbForTest.sql", conn);
    productManager.removeProduct(product,ds);

    }
    @Test
    void removeProduct_A2(){
        Product product=null;
        try {
            assertThrows(Exception.class,()-> productManager.addProduct(product,ds));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
