package unitTesting.OrderManagement;

import com.isnapgaming.OrderManagement.CustomerOrder;
import com.isnapgaming.OrderManagement.OrderProduct;
import com.isnapgaming.UserManagement.OrderManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.sql.DataSource;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerOrderTest {
    private CustomerOrder customerOrder;
    private CustomerOrder customerOrder2;
    private Connection conn;
    private DataSource ds;
    private OrderProduct product1;
    private OrderProduct product2;

    @BeforeEach
    void setUp() throws ClassNotFoundException, SQLException, InvalidParameterException {
        product1 = Mockito.mock(OrderProduct.class);
        product2 = Mockito.mock(OrderProduct.class);
        customerOrder2 = new CustomerOrder();
    }

    @Test
    public void makeCustomerOrder_C1_A1_O1_P1() {
        int customerId = 2;
        String address = "Via Roma";
        LocalDate orderDate = LocalDate.of(2020, 10, 12);
        List<OrderProduct> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        CustomerOrder customerOrder = CustomerOrder.makeCustomerOrder(customerId, address, orderDate, products);
        assertEquals(2, customerOrder.getCustomerId());
        assertEquals("Via Roma", customerOrder.getAddress());
        assertEquals(2, customerOrder.getProducts().size());
    }

    @Test
    public void makeCustomerOrder_C1_A1_O1_P2() {
        int customerId = 2;
        String address = "Via Roma";
        LocalDate orderDate = LocalDate.of(2020, 10, 12);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->CustomerOrder.makeCustomerOrder(customerId, address, orderDate, null));
        assertEquals("Cannot have an order with no products in it.", ex.getMessage());
    }

    @Test
    public void makeCustomerOrder_C1_A1_O2_P1() {
        int customerId = 2;
        String address = "Via Roma";
        List<OrderProduct> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->CustomerOrder.makeCustomerOrder(customerId, address, null, products));
        assertEquals("OrderDate cannot be null", ex.getMessage());
    }

    @Test
    public void makeCustomerOrder_C1_A2_O1_P1() {
        int customerId = 2;
        LocalDate orderDate = LocalDate.of(2020, 10, 12);
        List<OrderProduct> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->CustomerOrder.makeCustomerOrder(customerId, null, orderDate, products));
        assertEquals("Address cannot be null", ex.getMessage());
    }

    @Test
    public void makeCustomerOrder_C2_A1_O1_P1() {
        int customerId = -1;
        String address = "Via Roma";
        LocalDate orderDate = LocalDate.of(2020, 10, 12);
        List<OrderProduct> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->CustomerOrder.makeCustomerOrder(customerId, address, orderDate, products));
        assertEquals("CustomerId cannot be negative", ex.getMessage());
    }

    @Test
    public void addProduct_P1() {
        List<OrderProduct> products = new ArrayList<>();
        products.add(product1);
        customerOrder2.setProducts(products);

        customerOrder2.addProduct(product2);
        assertEquals(2, customerOrder2.getProducts().size());
    }

    @Test
    public void addProduct_P2() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->customerOrder2.addProduct(null));
        assertEquals("Product cannot be null", ex.getMessage());
    }

}
