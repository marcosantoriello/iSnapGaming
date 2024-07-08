package unitTesting.StorageSubsystem;


import com.isnapgaming.StorageManagement.DAO.CustomerDAO;
import com.isnapgaming.StorageManagement.DAO.UserDAO;
import com.isnapgaming.UserManagement.Address;
import com.isnapgaming.UserManagement.Customer;
import com.isnapgaming.UserManagement.User;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static testing.SQLScript.executeSQLScript;

public class CustomerDAOTest {
    private CustomerDAO customerDAO;
    private Address address;
    private Customer customer;
    private UserDAO userDAO;
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

        address = Mockito.mock(Address.class);
        Mockito.when(address.getCustomerId()).thenReturn(1);
        Mockito.when(address.getStreet()).thenReturn("Via Roma");
        Mockito.when(address.getCity()).thenReturn("Salerno");
        Mockito.when(address.getPostalCode()).thenReturn(84124);

        userDAO = Mockito.mock(UserDAO.class);
        Mockito.when(userDAO.doSave(Mockito.any(User.class))).thenReturn(1);
        customer = Mockito.mock(Customer.class);

        customerDAO = new CustomerDAO(ds);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        conn.close();
    }
    // doSave
    @Test
    void doSave_C1()throws SQLException, IllegalArgumentException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        List<Address> addresses=new ArrayList<>();
        addresses.add(address);
        Mockito.when(customer.getUsername()).thenReturn("pio@email.com");
        Mockito.when(customer.getPassword()).thenReturn("PAEgtord123");
        Mockito.when(customer.getFirstName()).thenReturn("Mario");
        Mockito.when(customer.getLastName()).thenReturn("Rossi");
        Mockito.when(customer.getDateOfBirth()).thenReturn(LocalDate.of(2000,1,1));
        Mockito.when(customer.getId()).thenReturn(1);
        Mockito.when(customer.getAddresses()).thenReturn(addresses);

        assertEquals(1, customerDAO.doSave(customer));
    }

    @Test
    void doSave_C2() throws SQLException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->customerDAO.doSave(null));
        assertEquals("Customer cannot be null", ex.getMessage());
    }
    @Test
    void findByKey_C1_CD1() throws SQLException, IllegalArgumentException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/CustomerDAO/findByKey_C1_CD1.sql", conn);
        Customer customer=customerDAO.findByKey(1);
        assertEquals(1, customer.getId());
    }
    @Test
    void findByKey_C2_CD1() throws SQLException, IllegalArgumentException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->customerDAO.findByKey(-3));
        assertEquals("Customer ID cannot be negative", ex.getMessage());
    }

    @Test
    void findByKey_C1_CD2() throws SQLException, IllegalArgumentException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);

        SQLException ex = assertThrows(SQLException.class, ()->customerDAO.findByKey(5));
        assertEquals("No Customer found with the given id", ex.getMessage());
    }

    @Test
    void findAddressByCustomerId_C1_CD1() throws SQLException, IllegalArgumentException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/CustomerDAO/findAddressByCustomerId_C1_CD1.sql", conn);

        assertEquals(1, customerDAO.findAddressesByCustomerId(1).get(0).getCustomerId());
    }

    @Test
    void findAddressByCustomerId_C2_CD1() throws SQLException, IllegalArgumentException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->customerDAO.findAddressesByCustomerId(-3));
        assertEquals("Customer ID cannot be negative", ex.getMessage());

    }
    @Test
    void findAddressByCustomerId_C1_CD2() throws SQLException, IllegalArgumentException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        assertEquals(0, customerDAO.findAddressesByCustomerId(5).size());

    }


}
