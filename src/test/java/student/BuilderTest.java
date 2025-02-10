package student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BuilderTest {

    @Test
    void testBuildHourlyEmployeeFromCSV() {
        String csv = "HOURLY,John Doe,123,20.0,100.0,5000.0,1000.0";
        IEmployee employee = Builder.buildEmployeeFromCSV(csv);
        assertNotNull(employee);
        assertTrue(employee instanceof HourlyEmployee);
        assertEquals("John Doe", employee.getName());
        assertEquals("123", employee.getID());
        assertEquals(20.0, employee.getPayRate());
    }

    @Test
    void testBuildSalaryEmployeeFromCSV() {
        String csv = "SALARY,Jane Smith,456,60000.0,200.0,20000.0,4000.0";
        IEmployee employee = Builder.buildEmployeeFromCSV(csv);
        assertNotNull(employee);
        assertTrue(employee instanceof SalaryEmployee);
        assertEquals("Jane Smith", employee.getName());
        assertEquals("456", employee.getID());
        assertEquals(60000.0, employee.getPayRate());
    }

    @Test
    void testInvalidCSV() {
        String csv = "INVALID,John Doe,123";
        IEmployee employee = Builder.buildEmployeeFromCSV(csv);
        assertNull(employee);
    }

    @Test
    void testBuildTimeCardFromCSV() {
        String csv = "123,40";
        ITimeCard timeCard = Builder.buildTimeCardFromCSV(csv);
        assertNotNull(timeCard);
        assertEquals("123", timeCard.getEmployeeID());
        assertEquals(40, timeCard.getHoursWorked());
    }

    @Test
    void testInvalidTimeCardCSV() {
        String csv = "123";
        ITimeCard timeCard = Builder.buildTimeCardFromCSV(csv);
        assertNull(timeCard);
    }
}
