package unitTesting.UserManagement;

import com.isnapgaming.UserManagement.Address;
import com.isnapgaming.UserManagement.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressTest {
    @Test
    void makeAddress_C1_S1_CT1_P1() {
        int customerId=1;
        String street= "Via Roma";
        String city= "Roma";
        int postalCode=34562;
        Address address;

        try {
            address=Address.makeAddress(customerId,street,city,postalCode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(customerId,address.getCustomerId());
        assertEquals(street,address.getStreet());
        assertEquals(city,address.getCity());
        assertEquals(postalCode,address.getPostalCode());
    }
    @Test
    void makeAddress_C1_S1_CT1_P2() {
        int customerId=1;
        String street= "Via Roma";
        String city= "Roma";
        int postalCode=-34562;
        Address address;
        assertThrows(Exception.class,()-> Address.makeAddress(customerId,street,city,postalCode));
    }
    @Test
    void makeAddress_C1_S1_CT2_P1() {
        int customerId=1;
        String street= "Via Roma";
        String city=null;
        int postalCode=-34562;
        Address address;
        assertThrows(Exception.class,()-> Address.makeAddress(customerId,street,city,postalCode));
    }

    @Test
    void makeAddress_C1_S2_CT1_P1() {
        int customerId=1;
        String street=null;
        String city="Roma";
        int postalCode=-34562;
        Address address;
        assertThrows(Exception.class,()-> Address.makeAddress(customerId,street,city,postalCode));
    }
    @Test
    void makeAddress_C2_S1_CT1_P1() {
        int customerId=-3;
        String street="Via Roma";
        String city="Roma";
        int postalCode=-34562;
        Address address;
        assertThrows(Exception.class,()-> Address.makeAddress(customerId,street,city,postalCode));
    }

}
