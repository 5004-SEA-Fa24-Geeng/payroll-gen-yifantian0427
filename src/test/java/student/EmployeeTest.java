package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    private HourlyEmployee hourlyEmployee;
    private SalaryEmployee salaryEmployee;

    @BeforeEach
    void setup() {
        hourlyEmployee = new HourlyEmployee("John Doe", "123", 20.0, 5000.0, 1000.0, 100.0);
        salaryEmployee = new SalaryEmployee("Jane Smith", "456", 60000.0, 20000.0, 4000.0, 200.0);
    }

    @Test
    void testHourlyEmployeePayroll() {
        IPayStub payStub = hourlyEmployee.runPayroll(45);
        assertNotNull(payStub);
        assertEquals(950.0, payStub.getPay(), 0.01); // 40 * 20 + 5 * 30 (overtime)
    }

    @Test
    void testSalaryEmployeePayroll() {
        IPayStub payStub = salaryEmployee.runPayroll(0);
        assertNotNull(payStub);
        assertEquals(2500.0, payStub.getPay(), 0.01); // 60000 / 24
    }

    @Test
    void testNegativeHoursPayroll() {
        IPayStub payStub = hourlyEmployee.runPayroll(-5);
        assertNull(payStub);
    }

    @Test
    void testToCSV() {
        String expected = "HOURLY,John Doe,123,20.00,100.00,5000.00,1000.00";
        assertEquals(expected, hourlyEmployee.toCSV());
    }
}
