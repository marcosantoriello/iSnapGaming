package unitTesting.StorageSubsystem;

import com.isnapgaming.ProductManagement.Product;
import com.isnapgaming.StorageManagement.DAO.ProductDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import testing.RetrieveCredentials;

import javax.sql.DataSource;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static testing.SQLScript.executeSQLScript;

public class ProductDAOTest {
    private ProductDAO productDAO;
    private Connection conn;
    private DataSource ds;

    @BeforeEach
    void setUp() throws ClassNotFoundException, SQLException, InvalidParameterException {
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

        productDAO = new ProductDAO(ds);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        conn.close();
    }

    // doSave
    @Test
    void doSave_A1() throws SQLException, IllegalArgumentException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);

        Product product = new Product();
        product.setProdCode(252);
        product.setName("Call of Duty WWII");
        product.setSoftwareHouse("Activision");
        product.setPlatform(Product.Platform.PC);
        product.setPrice(50);
        product.setQuantity(100);
        product.setCategory(Product.Category.SHOOTER);
        product.setPegi(Product.Pegi.PEGI18);
        product.setReleaseYear(2017);
        product.setAvailable(true);
        assertEquals(1, productDAO.doSave(product));
    }

    @Test
    void doSave_A2() throws SQLException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->productDAO.doSave(null));
        assertEquals("Product cannot be null", ex.getMessage());
    }

    // doUpdate
    @Test
    void doUpdate_A1() throws SQLException, IllegalArgumentException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/ProductDAO/doUpdate_A1.sql", conn);
        Product product = productDAO.findByProdCode(252);
        product.setQuantity(150);

        productDAO.doUpdate(product);
        assertEquals(150, productDAO.findByProdCode(252).getQuantity());
    }

    @Test
    void doUpdate_A2() throws SQLException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->productDAO.doUpdate(null));
        assertEquals("Product cannot be null", ex.getMessage());
    }

   // findByKey
    @Test
    void findByKey_P1_PD1() throws SQLException, IllegalArgumentException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/ProductDAO/findByKey_P1_PD1.sql", conn);

        Product product = productDAO.findByKey(1);
        assertEquals(1, product.getId());
    }

    @Test
    void findByKey_P2_PD1() throws SQLException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->productDAO.findByKey(-3));
        assertEquals("Id cannot be negative", ex.getMessage());
    }

    @Test
    void findByKey_P1_PD2() throws SQLException, IllegalArgumentException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/ProductDAO/findByKey_P1_PD2.sql", conn);

        SQLException ex = assertThrows(SQLException.class, ()->productDAO.findByKey(5));
        assertEquals("No Product found with the given id.", ex.getMessage());
    }

    // findByProdCode
    @Test
    void findByProdKey_P1_PD1() throws SQLException, IllegalArgumentException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/ProductDAO/findByProdCode_P1_PD1.sql", conn);

        Product product = productDAO.findByProdCode(252);
        assertEquals("CoD WWII", product.getName());
    }

    @Test
    void findByProdKey_P2_PD1() throws SQLException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->productDAO.findByProdCode(-3));
        assertEquals("prodCode cannot be negative", ex.getMessage());
    }

    @Test
    void findByProdKey_P1_PD2() throws SQLException, IllegalArgumentException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/ProductDAO/findByProdCode_P1_PD2.sql", conn);

        SQLException ex = assertThrows(SQLException.class, ()->productDAO.findByProdCode(5));
        assertEquals("No Product found with the given prodCode.", ex.getMessage());
    }

    // findByCategory
    @Test
    void findByCategory_C1_CC1() throws SQLException, IllegalArgumentException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);

        assertEquals(0, productDAO.findByCategory(Product.Category.valueOf("RPG")).size());
    }

    @Test
    void findByCategory_C2_CC1() throws SQLException, IllegalArgumentException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->productDAO.findByCategory(null));
        assertEquals("Category cannot be null", ex.getMessage());
    }

    @Test
    void findByCategory_C1_CC2() throws SQLException, IllegalArgumentException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/ProductDAO/findByCategory_C1_CC2.sql", conn);

        assertEquals("CoD WWII", productDAO.findByCategory(Product.Category.valueOf("SHOOTER")).get(0).getName());
    }
}
