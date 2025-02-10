package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Abstract class representing an Employee.
 * Provides common functionality for all employees, including pay rate,
 * year-to-date (YTD) earnings, taxes, and payroll processing.
 */
public abstract class Employee implements IEmployee {
    /** Name of the employee. */
    private String name;
    /** Unique employee ID. */
    private String id;
    /** Pay rate (hourly or annual salary). */
    private double payRate;
    /** Year-to-date earnings. */
    private double ytdEarnings;
    /** Year-to-date taxes paid. */
    private double ytdTaxesPaid;
    /** Pre-tax deductions for the employee. */
    private double pretaxDeductions;

    /**
     * Constructor for Employee.
     *
     * @param name Employee's name
     * @param id Employee's unique ID
     * @param payRate Pay rate (hourly for HourlyEmployee, annual salary for SalaryEmployee)
     * @param ytdEarnings Employee's earnings so far in the year
     * @param ytdTaxesPaid Total taxes paid by the employee in the year
     * @param pretaxDeductions Employee's pre-tax deductions
     */
    public Employee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid,
                    double pretaxDeductions) {
        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.pretaxDeductions = pretaxDeductions;
    }

    /** @return Employee's name */
    @Override
    public String getName() {
        return name;
    }

    /** @return Employee's ID */
    @Override
    public String getID() {
        return id;
    }

    /** @return Employee's pay rate (hourly or salary) */
    @Override
    public double getPayRate() {
        return payRate;
    }

    /** @return Employee's total earnings for the year */
    @Override
    public double getYTDEarnings() {
        return ytdEarnings;
    }

    /** @return Employee's total taxes paid for the year */
    @Override
    public double getYTDTaxesPaid() {
        return ytdTaxesPaid;
    }

    /** @return Employee's pre-tax deductions */
    @Override
    public double getPretaxDeductions() {
        return pretaxDeductions;
    }

    /**
     * Abstract method to be implemented by subclasses (HourlyEmployee, SalaryEmployee).
     * It calculates the gross pay based on the type of employee and hours worked.
     *
     * @param hoursWorked Hours worked during the pay period
     * @return Gross pay amount
     */
    protected abstract double calculateGrossPay(double hoursWorked);

    /**
     * Processes payroll for the current pay period.
     * Calculates the gross pay, applies deductions and taxes, and updates YTD earnings.
     *
     * @param hoursWorked Hours worked during the pay period
     * @return PayStub containing the payroll details, or null if hoursWorked < 0
     */
    @Override
    public IPayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) {
            return null; // Skip payroll processing if negative hours
        }

        // Special case: If an hourly employee worked 0 hours, return a PayStub with no pay.
        if (hoursWorked == 0 && getEmployeeType().equals("HOURLY")) {
            return new PayStub(this, 0.0, 0.0, getYTDEarnings(), getYTDTaxesPaid());
        }

        // Calculate gross pay based on employee type
        double grossPay = calculateGrossPay(hoursWorked);

        // Compute net pay after pretax deductions
        BigDecimal netPay = BigDecimal.valueOf(grossPay)
                .subtract(BigDecimal.valueOf(pretaxDeductions))
                .setScale(2, RoundingMode.HALF_UP);

        // Calculate tax amount (22.65% total tax rate)
        BigDecimal taxAmount = netPay.multiply(BigDecimal.valueOf(0.2265))
                .setScale(2, RoundingMode.HALF_UP);

        // Deduct taxes from net pay
        netPay = netPay.subtract(taxAmount);

        // Update YTD earnings and taxes
        ytdEarnings += netPay.doubleValue();
        ytdTaxesPaid += taxAmount.doubleValue();

        // Return a new PayStub containing the payroll details
        return new PayStub(this, netPay.doubleValue(), taxAmount.doubleValue(), ytdEarnings, ytdTaxesPaid);
    }

    /**
     * Converts the Employee object to a CSV-formatted string.
     * Format: employee_type,name,ID,payRate,pretaxDeductions,YTDEarnings,YTDTaxesPaid
     *
     * @return Employee information as a CSV string
     */
    @Override
    public String toCSV() {
        return String.format("%s,%s,%s,%.2f,%.2f,%.2f,%.2f",
                getEmployeeType(), name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }

    /**
     * Rounds a value to two decimal places using HALF_UP rounding mode.
     * Helps in avoiding floating-point precision errors.
     *
     * @param value Value to be rounded
     * @return Rounded value to two decimal places
     */
    private double round(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
