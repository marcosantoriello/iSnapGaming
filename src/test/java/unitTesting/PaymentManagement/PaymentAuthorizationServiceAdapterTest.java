package unitTesting.PaymentManagement;

import com.isnapgaming.PaymentManagement.PaymentAuthorizationServiceAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class PaymentAuthorizationServiceAdapterTest {
    private PaymentAuthorizationServiceAdapter pa;

    @BeforeEach
    void setup() {
        pa = new PaymentAuthorizationServiceAdapter();
    }

    // checkPaymentData
    @Test
    void checkPaymentData_A1_B1_C1_D1() {
        String cardNumber = "1234-1234-1234-1234";
        LocalDate expiration = LocalDate.of(2030, 1, 1);
        String cvv = "123";
        int price = 30;
        assertTrue(pa.checkPaymentData(cardNumber, expiration, cvv, price));
    }
    @Test
    void checkPaymentData_A2_B1_C1_D1() {
        String cardNumber = "1234-1234";
        LocalDate expiration = LocalDate.of(2030, 1, 1);
        String cvv = "123";
        int price = 30;
        assertFalse(pa.checkPaymentData(cardNumber, expiration, cvv, price));
    }
    @Test
    void checkPaymentData_A1_B2_C1_D1() {
        String cardNumber = "1234-1234-1234-1234";
        LocalDate expiration = LocalDate.of(2017, 1, 1);
        String cvv = "123";
        int price = 30;
        assertFalse(pa.checkPaymentData(cardNumber, expiration, cvv, price));
    }
    @Test
    void checkPaymentData_A1_B1_C2_D1() {
        String cardNumber = "1234-1234-1234-1234";
        LocalDate expiration = LocalDate.of(2030, 1, 1);
        String cvv = "AB";
        int price = 30;
        assertFalse(pa.checkPaymentData(cardNumber, expiration, cvv, price));
    }
    @Test
    void checkPaymentData_A1_B1_C1_D2() {
        String cardNumber = "1234-1234-1234-1234";
        LocalDate expiration = LocalDate.of(2030, 1, 1);
        String cvv = "123";
        int price = -10;
        assertFalse(pa.checkPaymentData(cardNumber, expiration, cvv, price));
    }
}
