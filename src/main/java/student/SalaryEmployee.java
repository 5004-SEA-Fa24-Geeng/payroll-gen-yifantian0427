package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents a salaried employee.
 * This class extends Employee and implements payroll calculations for salary-based pay.
 */
public class SalaryEmployee extends Employee {

    /**
     * Constructs a SalaryEmployee object.
     *
     * @param name Employee's name
     * @param id Employee's unique ID
     * @param payRate Annual salary amount
     * @param ytdEarnings Employee's year-to-date earnings
     * @param ytdTaxesPaid Employee's year-to-date taxes paid
     * @param pretaxDeductions Employee's pre-tax deductions
     */
    public SalaryEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid,
                          double pretaxDeductions) {
        super(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
    }

    /**
     * Returns the type of employee as "SALARY".
     *
     * @return String representing the employee type.
     */
    @Override
    public String getEmployeeType() {
        return "SALARY";
    }

    /**
     * Calculates the gross pay for a salaried employee.
     * Salary employees receive a fixed bi-monthly paycheck (dividing annual salary by 24).
     *
     * @param hoursWorked This parameter is ignored for salary employees.
     * @return Gross pay amount before deductions and taxes.
     */
    @Override
    protected double calculateGrossPay(double hoursWorked) {
        return round(getPayRate() / 24); // Salary is paid twice per month (24 pay periods annually)
    }

    /**
     * Rounds a value to two decimal places using HALF_UP rounding mode.
     * Ensures precision and avoids floating-point rounding errors.
     *
     * @param value Value to be rounded.
     * @return Rounded value to two decimal places.
     */
    private double round(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
