package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HourlyEmployeeTest {

    private HourlyEmployee hourlyEmployee;

    @BeforeEach
    void setup() {
        hourlyEmployee = new HourlyEmployee("John Doe", "123", 20.0, 5000.0, 1000.0, 100.0);
    }

    @Test
    void testGetEmployeeType() {
        assertEquals("HOURLY", hourlyEmployee.getEmployeeType());
    }

    @Test
    void testCalculateGrossPay_RegularHours() {
        double grossPay = hourlyEmployee.calculateGrossPay(40);
        assertEquals(800.0, grossPay, 0.01); // 40 * 20
    }

    @Test
    void testCalculateGrossPay_Overtime() {
        double grossPay = hourlyEmployee.calculateGrossPay(45);
        assertEquals(950.0, grossPay, 0.01); // (40 * 20) + (5 * 30)
    }

    @Test
    void testCalculateGrossPay_NoHours() {
        double grossPay = hourlyEmployee.calculateGrossPay(0);
        assertEquals(0.0, grossPay, 0.01);
    }

    @Test
    void testRunPayroll_PositiveHours() {
        IPayStub payStub = hourlyEmployee.runPayroll(45);
        assertNotNull(payStub);
        assertEquals(950.0, payStub.getPay(), 0.01);
    }

    @Test
    void testRunPayroll_ZeroHours() {
        IPayStub payStub = hourlyEmployee.runPayroll(0);
        assertNotNull(payStub);
        assertEquals(0.0, payStub.getPay(), 0.01);
    }

    @Test
    void testRunPayroll_NegativeHours() {
        IPayStub payStub = hourlyEmployee.runPayroll(-5);
        assertNull(payStub);
    }

    @Test
    void testToCSV() {
        String expectedCSV = "HOURLY,John Doe,123,20.00,100.00,5000.00,1000.00";
        assertEquals(expectedCSV, hourlyEmployee.toCSV());
    }
}
