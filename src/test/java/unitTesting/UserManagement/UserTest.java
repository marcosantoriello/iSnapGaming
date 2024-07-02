package unitTesting.UserManagement;

import com.isnapgaming.ProductManagement.Product;
import com.isnapgaming.UserManagement.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {
    @Test
    void makeUser_U1_P1_F1_L1_B1() {
        String username="MariRos";
        String password= "password1";
        String firstName= "Mario";
        String lastName="Rossi";
        LocalDate dateOfBirth=LocalDate.of(2000,12,10);
        User user;

        try {
            user=User.makeUser(username,password,firstName,lastName,dateOfBirth);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(username,user.getUsername());
        assertEquals(password,user.getPassword());
        assertEquals(firstName,user.getFirstName());
        assertEquals(lastName,user.getLastName());
        assertEquals(dateOfBirth,user.getDateOfBirth());
    }
    @Test
    void makeUser_U1_P1_F1_L1_B2()throws IllegalArgumentException {
        String username="MariRos";
        String password= "password1";
        String firstName= "Mario";
        String lastName="Rossi";
        LocalDate dateOfBirth=null;
        User user;
        assertThrows(Exception.class,()->User.makeUser(username,password,firstName,lastName,dateOfBirth));

    }

    @Test
    void makeUser_U1_P1_F1_L2_B1()throws IllegalArgumentException  {
        String username="MariRos";
        String password= "password1";
        String firstName= "Mario";
        String lastName=null;
        LocalDate dateOfBirth=LocalDate.of(2000,12,10);
        User user;
        assertThrows(Exception.class,()->User.makeUser(username,password,firstName,lastName,dateOfBirth));
    }
    @Test
    void makeUser_U1_P1_F2_L1_B1()throws IllegalArgumentException  {
        String username="MariRos";
        String password= "password1";
        String firstName= null;
        String lastName="Rossi";
        LocalDate dateOfBirth=LocalDate.of(2000,12,10);
        User user;
        assertThrows(Exception.class,()->User.makeUser(username,password,firstName,lastName,dateOfBirth));
    }
    @Test
    void makeUser_U1_P2_F1_L1_B1()throws IllegalArgumentException  {
        String username="MariRos";
        String password= null;
        String firstName= "Mario";
        String lastName="Rossi";
        LocalDate dateOfBirth=LocalDate.of(2000,12,10);
        User user;
        assertThrows(Exception.class,()->User.makeUser(username,password,firstName,lastName,dateOfBirth));
    }

    @Test
    void makeUser_U2_P1_F1_L1_B1()throws IllegalArgumentException  {
        String username=null;
        String password= "password1";
        String firstName= "Mario";
        String lastName="Rossi";
        LocalDate dateOfBirth=LocalDate.of(2000,12,10);
        User user;
        assertThrows(Exception.class,()->User.makeUser(username,password,firstName,lastName,dateOfBirth));
    }



}
