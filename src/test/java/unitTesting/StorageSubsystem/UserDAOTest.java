package unitTesting.StorageSubsystem;

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
import static org.junit.jupiter.api.Assertions.*;
import static testing.SQLScript.executeSQLScript;

public class UserDAOTest {
    private UserDAO userDAO;
    private Connection conn;
    private DataSource ds;
    private User user;

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

        user = Mockito.mock(User.class);
        Mockito.when(user.getUsername()).thenReturn("mrossi@isnapgaming.com");
        Mockito.when(user.getPassword()).thenReturn("hvweU48gkereflsll3");
        Mockito.when(user.getFirstName()).thenReturn("Mario");
        Mockito.when(user.getLastName()).thenReturn("Rossi");
        Mockito.when(user.getDateOfBirth()).thenReturn(LocalDate.of(1990, 1, 1));
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

        assertEquals(1, userDAO.doSave(user));
    }

    @Test
    void doSave_U2() throws SQLException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->userDAO.doSave(null));
        assertEquals("User cannot be null", ex.getMessage());
    }

    //getByUsernameAndPassword
    @Test
    void getByUsernameAndPassword_U1_P1_CD1() throws SQLException, IllegalArgumentException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/UserDAO/getByUsernameAndPassword_U1_P1_CD1.sql", conn);

        assertEquals("mrossi@isnapgaming.com", userDAO.getUserByUsernameAndPassword("mrossi@isnapgaming.com", "hvweU48gkereflsll3").getUsername());
    }

    @Test
    void getByUsernameAndPassword_U2_P1_CD2() throws SQLException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->userDAO.getUserByUsernameAndPassword(null, "hvweU48gkereflsll3"));
        assertEquals("Cannot have empty username or password", ex.getMessage());
    }

    @Test
    void getByUsernameAndPassword_U1_P2_CD1() throws SQLException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->userDAO.getUserByUsernameAndPassword("mrossi@isnapgaming.com", null));
        assertEquals("Cannot have empty username or password", ex.getMessage());
    }

    @Test
    void getByUsernameAndPassword_U1_P1_CD2() throws SQLException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/UserDAO/getByUsernameAndPassword_U1_P1_CD2.sql", conn);
        SQLException ex = assertThrows(SQLException.class, ()->userDAO.getUserByUsernameAndPassword("pluto@isnapgaming.com", "tuiyui4y5Ebrbrbr"));
        assertEquals("No User found with the given credentials", ex.getMessage());
    }

    // getUserRoles
    @Test
    void getUserRoles_U1_UD1_NR1() throws SQLException, IllegalArgumentException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/UserDAO/getUserRoles_U1_UD1_NR1.sql", conn);
        assertEquals(1, userDAO.getUserRoles(1).size());
    }

    @Test
    void getUserRoles_U1_UD1_NR2() throws SQLException, IllegalArgumentException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/UserDAO/getUserRoles_U1_UD1_NR2.sql", conn);
        assertEquals(3, userDAO.getUserRoles(1).size());
    }

    @Test
    void getUserRoles_U2_UD2() throws SQLException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->userDAO.getUserRoles(-3));
        assertEquals("Id cannot be negative", ex.getMessage());
    }
    @Test
    void getUserRoles_U1_UD2() throws SQLException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/UserDAO/getUserRoles_U1_UD2.sql", conn);
        SQLException ex = assertThrows(SQLException.class, ()->userDAO.getUserRoles(10));
        assertEquals("No User found with the given id", ex.getMessage());
    }

    //findByKey
    @Test
    void findByKey_U1_UD1() throws SQLException, IllegalArgumentException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/UserDAO/findByKey_U1_UD1.sql", conn);
        assertEquals("mrossi@isnapgaming.com", userDAO.findByKey(1).getUsername());
    }

    @Test
    void findByKey_U2_UD2() throws SQLException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->userDAO.findByKey(-3));
        assertEquals("Id cannot be negative", ex.getMessage());

    }

    @Test
    void findByKey_U1_UD2() throws SQLException{
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/UserDAO/findByKey_U1_UD2.sql", conn);
        SQLException ex = assertThrows(SQLException.class, ()->userDAO.findByKey(10));
        assertEquals("No User found with the given id", ex.getMessage());
    }

    // findByUsername
    @Test
    void findByUsername_U1_UD1() throws SQLException, IllegalArgumentException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/UserDAO/findByUsername_U1_UD1.sql", conn);
        assertEquals(1, userDAO.findByUsername("mrossi@isnapgaming.com").getId());
    }

    @Test
    void findByUsername_U2_UD2() throws SQLException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->userDAO.findByUsername(null));
        assertEquals("Username cannot be null", ex.getMessage());
    }

    @Test
    void findByUsername_U1_UD2() throws  SQLException {
        executeSQLScript("src/test/db/createDbForTest.sql", conn);
        executeSQLScript("src/test/db/StorageManagement/UserDAO/findByUsername_U1_UD2.sql", conn);
        SQLException ex = assertThrows(SQLException.class, ()->userDAO.findByUsername("pluto@isnapgaming.com"));
        assertEquals("No User found with the given username", ex.getMessage());
    }

}
