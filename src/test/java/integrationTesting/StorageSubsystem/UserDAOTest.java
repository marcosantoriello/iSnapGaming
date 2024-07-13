package integrationTesting.StorageSubsystem;

import com.isnapgaming.StorageManagement.DAO.UserDAO;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static testing.SQLScript.executeSQLScript;

public class UserDAOTest {
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

        userDAO = new UserDAO(ds);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        conn.close();
    }

    // doSave
    @Test
    void doSave_U1() throws SQLException, IllegalArgumentException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);

        User user = User.makeUser("mrossi@isnapgaming.com", "hvweU48gkereflsll3",
                "Mario", "Rossi", LocalDate.of(1990, 1, 1));

        assertEquals(1, userDAO.doSave(user));
    }

    @Test
    void doSave_U2() throws SQLException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->userDAO.doSave(null));
        assertEquals("User cannot be null", ex.getMessage());
    }

}
