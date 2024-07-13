package integrationTesting.UserManagement;

import com.isnapgaming.OrderManagement.CustomerOrder;
import com.isnapgaming.StorageManagement.DAO.CustomerOrderDAO;
import com.isnapgaming.UserManagement.OrderManager;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static testing.SQLScript.executeSQLScript;

public class OrderManagerTest {
    private Connection conn;
    private DataSource ds;
    private OrderManager orderManager;

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
        orderManager = new OrderManager();
    }
    @AfterEach
    public void tearDown() throws SQLException {
        conn.close();
    }
    @Test
    void checkProduct_O1() throws SQLException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/UserManagement/OrderManager/checkProduct_O1.sql", conn);
        CustomerOrder order = new CustomerOrder();
        CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO(ds);
        order.setId(1);
        orderManager.checkProduct(order, ds);
        assertEquals("UNDER_PREPARATION", customerOrderDAO.findByKey(1).getStatus().toString());
    }

    @Test
    void checkProduct_O2() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->orderManager.checkProduct(null, ds));
        assertEquals("Order cannot be null", ex.getMessage());
    }

    @Test
    void packProduct_O1() throws SQLException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/UserManagement/OrderManager/packProduct_O1.sql", conn);
        CustomerOrder order = new CustomerOrder();
        CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO(ds);
        order.setId(1);
        orderManager.packProduct(order, ds);
        assertEquals("READY_FOR_SENDING", customerOrderDAO.findByKey(1).getStatus().toString());
    }

    @Test
    void packProduct_O2() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->orderManager.packProduct(null, ds));
        assertEquals("Order cannot be null", ex.getMessage());
    }

    @Test
    void replaceProduct_O1() throws SQLException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/UserManagement/OrderManager/replaceProduct_O1.sql", conn);
        CustomerOrder order = new CustomerOrder();
        CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO(ds);
        order.setId(1);
        orderManager.replaceProduct(order, ds);
        assertEquals("UNDER_PREPARATION", customerOrderDAO.findByKey(1).getStatus().toString());
    }

    @Test
    void replaceProduct_O2() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->orderManager.replaceProduct(null, ds));
        assertEquals("Order cannot be null", ex.getMessage());
    }

    @Test
    void contactCourier_O1() throws SQLException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/UserManagement/OrderManager/contactCourier_O1.sql", conn);
        CustomerOrder order = new CustomerOrder();
        CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO(ds);
        order.setId(1);
        orderManager.contactCourier(order, ds);
        assertEquals("SHIPPED", customerOrderDAO.findByKey(1).getStatus().toString());
    }

    @Test
    void contactCourier_O2() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->orderManager.contactCourier(null, ds));
        assertEquals("Order cannot be null", ex.getMessage());
    }

    @Test
    void restoreOrder_O1() throws SQLException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/UserManagement/OrderManager/restoreOrder_O1.sql", conn);
        CustomerOrder order = new CustomerOrder();
        CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO(ds);
        order.setId(1);
        orderManager.restoreOrder(order, ds);
        assertEquals("UNDER_PREPARATION", customerOrderDAO.findByKey(1).getStatus().toString());
    }

    @Test
    void restoreOrder_O2() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->orderManager.restoreOrder(null, ds));
        assertEquals("Order cannot be null", ex.getMessage());
    }

    @Test
    void confirmDelivery_O1() throws SQLException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/UserManagement/OrderManager/confirmDelivery_O1.sql", conn);
        CustomerOrder order = new CustomerOrder();
        CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO(ds);
        order.setId(1);
        orderManager.confirmDelivery(order, ds);
        assertEquals("DELIVERED", customerOrderDAO.findByKey(1).getStatus().toString());
    }

    @Test
    void confirmDelivery_O2() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->orderManager.confirmDelivery(null, ds));
        assertEquals("Order cannot be null", ex.getMessage());
    }
}
