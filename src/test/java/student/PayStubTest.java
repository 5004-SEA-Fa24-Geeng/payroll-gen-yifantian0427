package student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PayStubTest {

    @Test
    void testPayStubCreation() {
        IEmployee employee = new HourlyEmployee("John Doe", "123", 20.0, 5000.0, 1000.0, 100.0);
        PayStub payStub = new PayStub(employee, 950.0, 215.48, 5950.0, 1215.48);

        assertNotNull(payStub);
        assertEquals(950.0, payStub.getPay(), 0.01);
        assertEquals(215.48, payStub.getTaxesPaid(), 0.01);
    }

    @Test
    void testPayStubToCSV() {
        IEmployee employee = new HourlyEmployee("John Doe", "123", 20.0, 5000.0, 1000.0, 100.0);
        PayStub payStub = new PayStub(employee, 950.0, 215.48, 5950.0, 1215.48);

        String expectedCSV = "John Doe,950.00,215.48,5950.00,1215.48";
        assertEquals(expectedCSV, payStub.toCSV());
    }
}
