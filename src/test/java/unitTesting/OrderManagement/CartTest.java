package unitTesting.OrderManagement;

import com.isnapgaming.OrderManagement.Cart;
import com.isnapgaming.ProductManagement.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.security.InvalidParameterException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {
    private Cart cart;
    private Product product;

    @BeforeEach
    void setUp() throws ClassNotFoundException, SQLException, InvalidParameterException {
        cart = new Cart();
        product = Mockito.mock(Product.class);
        product.setPrice(55);
    }

    // addToCart
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

    // removeFromCart
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

    // decreaseQuantityCart
    @Test
    void decreaseQuantityCart_A1_B1_C1() throws IllegalArgumentException, RuntimeException {
        Product prod2 = new Product();
        prod2.setProdCode(352);
        cart.addToCart(prod2, 5);
        cart.decreaseQuantityCart(prod2, 2);
        assertEquals(3, cart.getItems().get(0).getQuantity());
    }

    @Test
    void decreaseQuantityCart_A2_B1_C1(){
        cart.addToCart(product, 5);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->cart.decreaseQuantityCart(null, 2));
        assertEquals(ex.getMessage(), "Product cannot be null");
    }

    @Test
    void decreaseQuantityCart_A1_B2_C4(){
        RuntimeException ex = assertThrows(RuntimeException.class, ()->cart.decreaseQuantityCart(product, 2));
        assertEquals(ex.getMessage(), "No such product found in cart.");
    }

    @Test
    void decreaseQuantityCart_A1_B1_C2(){
        cart.addToCart(product, 5);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->cart.decreaseQuantityCart(product, -3));
        assertEquals(ex.getMessage(), "Quantity must be greater than 0.");
    }

    @Test
    void decreaseQuantityCart_A1_B1_C4(){
        cart.addToCart(product, 3);
        RuntimeException ex = assertThrows(RuntimeException.class, ()->cart.decreaseQuantityCart(product, 5));
        assertEquals(ex.getMessage(), "Invalid quantity");
    }

    @Test
    void decreaseQuantityCart_A1_B1_C3(){
        cart.addToCart(product, 3);
        cart.decreaseQuantityCart(product, 3);
        assertEquals(0, cart.getItems().size());
    }
}
