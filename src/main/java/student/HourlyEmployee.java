package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents an hourly employee.
 * This class extends Employee and implements hourly-based payroll calculations,
 * including regular and overtime pay.
 */
public class HourlyEmployee extends Employee {

    /**
     * Constructs an HourlyEmployee object.
     *
     * @param name Employee's name
     * @param id Employee's unique ID
     * @param payRate Hourly pay rate
     * @param ytdEarnings Employee's year-to-date earnings
     * @param ytdTaxesPaid Employee's year-to-date taxes paid
     * @param pretaxDeductions Employee's pre-tax deductions
     */
    public HourlyEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        super(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
    }

    /**
     * Returns the type of employee as "HOURLY".
     *
     * @return String representing the employee type.
     */
    @Override
    public String getEmployeeType() {
        return "HOURLY";
    }

    /**
     * Calculates the gross pay for the employee based on hours worked.
     * - Regular hours (≤ 40) are paid at the standard rate.
     * - Overtime hours (> 40) are paid at **1.5x** the standard rate.
     *
     * @param hoursWorked Number of hours worked in the pay period.
     * @return Gross pay amount before deductions and taxes.
     */
    @Override
    protected double calculateGrossPay(double hoursWorked) {
        if (hoursWorked <= 40) {
            return round(hoursWorked * getPayRate()); // Regular pay calculation
        } else {
            double regularPay = 40 * getPayRate(); // Pay for first 40 hours
            double overtimePay = (hoursWorked - 40) * getPayRate() * 1.5; // Overtime pay
            return round(regularPay + overtimePay);
        }
    }

    /**
     * Rounds a value to two decimal places using HALF_UP rounding mode.
     * This ensures precision and avoids floating-point rounding errors.
     *
     * @param value Value to be rounded.
     * @return Rounded value to two decimal places.
     */
    private double round(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
