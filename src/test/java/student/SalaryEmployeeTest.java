package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SalaryEmployeeTest {

    private SalaryEmployee salaryEmployee;

    @BeforeEach
    void setup() {
        salaryEmployee = new SalaryEmployee("Jane Smith", "456", 60000.0, 20000.0, 4000.0, 200.0);
    }

    @Test
    void testGetEmployeeType() {
        assertEquals("SALARY", salaryEmployee.getEmployeeType());
    }

    @Test
    void testCalculateGrossPay() {
        double grossPay = salaryEmployee.calculateGrossPay(0);
        assertEquals(2500.0, grossPay, 0.01); // 60000 / 24
    }

    @Test
    void testRunPayroll_PositiveHours() {
        IPayStub payStub = salaryEmployee.runPayroll(80);
        assertNotNull(payStub);
        assertEquals(2500.0, payStub.getPay(), 0.01);
    }

    @Test
    void testRunPayroll_ZeroHours() {
        IPayStub payStub = salaryEmployee.runPayroll(0);
        assertNotNull(payStub);
        assertEquals(2500.0, payStub.getPay(), 0.01);
    }

    @Test
    void testRunPayroll_NegativeHours() {
        IPayStub payStub = salaryEmployee.runPayroll(-10);
        assertNull(payStub);
    }

    @Test
    void testToCSV() {
        String expectedCSV = "SALARY,Jane Smith,456,60000.00,200.00,20000.00,4000.00";
        assertEquals(expectedCSV, salaryEmployee.toCSV());
    }
}
