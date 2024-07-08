package integrationTesting.StorageSubsystem;

import com.isnapgaming.StorageManagement.DAO.AddressDAO;
import com.isnapgaming.UserManagement.Address;
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

public class AddressDAOTest {
    private AddressDAO addressDAO;
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
        addressDAO=new AddressDAO(ds);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        conn.close();
    }

    @Test
    void doSave_VA1() throws SQLException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/AddressDAO/doSave_VA1.sql", conn);
        Address address=new Address();
        address.setCustomerId(1);
        address.setStreet("statale");
        address.setCity("Roma");
        address.setPostalCode(10345);
        assertEquals(1,addressDAO.doSave(address));

    }

    @Test
    void doSave_VA2() throws SQLException {
        Address address=null;
        try {
            assertThrows(Exception.class,()-> addressDAO.doSave(address));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}




