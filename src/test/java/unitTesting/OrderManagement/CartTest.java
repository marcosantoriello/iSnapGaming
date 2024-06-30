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
        product.setPrice(55);
    }

    @AfterEach
    public void tearDown() {}

    // Add to cart
    @Test
    void addToCart_A1_B1_C1() throws IllegalArgumentException{
        int quantity = 1;

        cart.addToCart(product, quantity);
        assertEquals(1, cart.getItems().size());
    }

    @Test
    void addToCart_A2_B1_C1() {
        int quantity = 1;

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->cart.addToCart(null, quantity));
        assertEquals(ex.getMessage(), "Product cannot be null");
    }

    @Test
    void addToCart_A1_B2_C1()  {
        int quantity = -3;

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->cart.addToCart(product, quantity));
        assertEquals(ex.getMessage(), "Quantity must be greater than 0.");
    }

    @Test
    void addToCart_A1_B1_C2() throws IllegalArgumentException {
        cart.addToCart(product, 3);

        cart.addToCart(product, 1);
        assertEquals(4, cart.getItems().get(0).getQuantity());
    }

    // Remove From Cart
    @Test
    void removeFromCart_A1_B1() throws IllegalArgumentException, Exception{
        cart.addToCart(product, 1);

        cart.removeFromCart(product);
        assertEquals(0, cart.getItems().size());
    }
    @Test
    void removeFromCart_A2_B1() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->cart.removeFromCart(null));
        assertEquals(ex.getMessage(), "Product cannot be null");
    }

    @Test
    void removeFromCart_A1_B2() {
        RuntimeException ex = assertThrows(RuntimeException.class, ()->cart.removeFromCart(product));
        assertEquals(ex.getMessage(), "No such product found in cart.");
    }
}
