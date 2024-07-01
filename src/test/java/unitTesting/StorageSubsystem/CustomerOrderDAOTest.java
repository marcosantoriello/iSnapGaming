package unitTesting.StorageSubsystem;

import com.isnapgaming.OrderManagement.CustomerOrder;
import com.isnapgaming.OrderManagement.OrderProduct;
import com.isnapgaming.ProductManagement.Product;
import com.isnapgaming.StorageManagement.DAO.CustomerOrderDAO;
import com.isnapgaming.UserManagement.Customer;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static testing.SQLScript.executeSQLScript;


public class CustomerOrderDAOTest {
    private CustomerOrderDAO customerOrderDAO;
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

        customerOrderDAO = new CustomerOrderDAO(ds);
    }
    @AfterEach
    public void tearDown() throws SQLException {
        conn.close();
    }

    @Test
    void doSave_O1() throws SQLException, IllegalArgumentException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/CustomerOrderDAO/doSave_O1.sql", conn);

        Product prod = new Product();
        prod.setProdCode(252);
        List<OrderProduct> prods = new ArrayList<>();
        prods.add(new OrderProduct(1, 1, 3, 60));
        Customer customer = new Customer();
        customer.setId(1);

        CustomerOrder customerOrder = CustomerOrder.makeCustomerOrder(1, "Via Roma", LocalDate.now(), prods);
        assertEquals(1, customerOrderDAO.doSave(customerOrder));
    }

    @Test
    void doSave_O2() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->customerOrderDAO.doSave(null));
        assertEquals("CustomerOrder cannot be null", ex.getMessage());
    }

    @Test
    void updateStatus_O1_S1_PD1() throws SQLException, IllegalArgumentException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/CustomerOrderDAO/updateStatus_O1_S1_PD1.sql", conn);

        customerOrderDAO.updateStatus(1, CustomerOrder.Status.valueOf("UNDER_PREPARATION"));
        assertEquals("UNDER_PREPARATION", customerOrderDAO.findByKey(1).getStatus().toString());
    }

    @Test
    void updateStatus_O2_S1_PD2() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->customerOrderDAO.updateStatus(-3, CustomerOrder.Status.valueOf("UNDER_PREPARATION")));
        assertEquals("Id cannot be negative", ex.getMessage());
    }

    @Test
    void updateStatus_O1_S2_PD1() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->customerOrderDAO.updateStatus(1, null));
        assertEquals("Status cannot be null", ex.getMessage());
    }

    @Test
    void updateStatus_O1_S2_PD2() throws SQLException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        SQLException ex = assertThrows(SQLException.class, ()->customerOrderDAO.updateStatus(5, CustomerOrder.Status.valueOf("UNDER_PREPARATION")));
        assertEquals("No CustomerOrder found with the given id", ex.getMessage());
    }

    @Test
    void findByKey_I1_PD1() throws SQLException, IllegalArgumentException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/CustomerOrderDAO/findByKey_I1_PD1.sql", conn);
        assertEquals("TO_BE_MANAGED", customerOrderDAO.findByKey(1).getStatus().toString());
    }

    @Test
    void findByKey_I2_PD2() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->customerOrderDAO.findByKey(-3));
        assertEquals("id cannot be negative", ex.getMessage());
    }

    @Test
    void findByKey_I1_PD2() throws SQLException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        SQLException ex = assertThrows(SQLException.class, ()->customerOrderDAO.findByKey(1));
        assertEquals("No CustomerOrder found with the given id", ex.getMessage());
    }

    @Test
    void doRetrieveAll_PC1() throws SQLException, IllegalArgumentException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        assertEquals(0, customerOrderDAO.doRetrieveAll().size());
    }

    @Test
    void doRetrieveAll_PC2() throws SQLException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/CustomerOrderDAO/doRetrieveAll_PC2.sql", conn);
        assertEquals(1, customerOrderDAO.doRetrieveAll().size());
    }

}
