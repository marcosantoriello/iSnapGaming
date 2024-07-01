package unitTesting.StorageSubsystem;


import com.isnapgaming.ProductManagement.Product;
import com.isnapgaming.StorageManagement.DAO.CustomerDAO;
import com.isnapgaming.StorageManagement.DAO.ProductDAO;
import com.isnapgaming.UserManagement.Address;
import com.isnapgaming.UserManagement.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
    private CustomerDAO CustomerDAO;
    private Connection conn;
    private DataSource ds;
    @Mock
    Address address;
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
        CustomerDAO=new CustomerDAO(ds);

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
        Customer customer= new Customer();
        customer.setUsername("pio");
        customer.setPassword("password1");
        customer.setFirstName("mario");
        customer.setLastName("Rossi");
        customer.setDateOfBirth(LocalDate.of(2000,1,1));
        customer.setId(1);
        customer.setAddresses(addresses);
        assertEquals(1, CustomerDAO.doSave(customer));
    }

    @Test
    void doSave_C2() throws SQLException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->CustomerDAO.doSave(null));
        assertEquals("Customer cannot be null", ex.getMessage());
    }


}
