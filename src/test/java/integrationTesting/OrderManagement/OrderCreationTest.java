package integrationTesting.OrderManagement;

import com.isnapgaming.OrderManagement.Cart;
import com.isnapgaming.OrderManagement.CustomerOrder;
import com.isnapgaming.OrderManagement.OrderCreation;
import com.isnapgaming.UserManagement.Customer;
import com.isnapgaming.UserManagement.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderCreationTest {
    Cart cart;
    User customer;

    @BeforeEach
    void setUp() throws InvalidParameterException {
        cart = new Cart();
        customer = new Customer();
    }

    @Test
    void makeOrder_CA1_CU1_A1() {
        String address = "Via Roma, Salerno, 84084";
        CustomerOrder order = null;
        try {
            order = OrderCreation.makeOrder(cart, customer, address);
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
        assertEquals("TO_BE_MANAGED", order.getStatus().toString());
        assertEquals(address, order.getAddress());
    }

    @Test
    void makeOrder_CA2_CU1_A1() {
        String address = "Via Roma, Salerno, 84084";
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> OrderCreation.makeOrder(null, customer, address));
        assertEquals("There was an error in creating the order.", ex.getMessage());
    }

    @Test
    void makeOrder_CA1_CU2_A1() {
        String address = "Via Roma, Salerno, 84084";
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> OrderCreation.makeOrder(cart, null, address));
        assertEquals("There was an error in creating the order.", ex.getMessage());
    }

    @Test
    void makeOrder_CA1_CU1_A2() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> OrderCreation.makeOrder(cart, customer, null));
        assertEquals("There was an error in creating the order.", ex.getMessage());
    }
}