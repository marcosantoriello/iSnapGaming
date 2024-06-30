package unitTesting.OrderManagement;

import com.isnapgaming.OrderManagement.Cart;
import com.isnapgaming.ProductManagement.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.security.InvalidParameterException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static testing.SQLScript.executeSQLScript;

public class CartTest {
    private Cart cart;

    @Mock
    Product product;

    @BeforeEach
    void setUp() throws ClassNotFoundException, SQLException, InvalidParameterException {
        cart = new Cart();
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() {}

    @Test
    void addToCart_A1_B1() throws IllegalArgumentException{
        int quantity = 1;

        cart.addToCart(product, quantity);
        assertEquals(1, cart.getItems().size());
    }

    @Test
    void addToCart_A2_B1() {
        int quantity = 1;

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->cart.addToCart(null, quantity));
        assertEquals(ex.getMessage(), "Product cannot be null");
    }

    @Test
    void addToCart_A1_B2()  {
        int quantity = -3;

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->cart.addToCart(product, quantity));
        assertEquals(ex.getMessage(), "Quantity must be greater than 0.");
    }
}
