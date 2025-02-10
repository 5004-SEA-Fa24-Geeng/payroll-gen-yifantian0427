package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents a pay stub generated after processing an employee's payroll.
 * This class stores details such as net pay, taxes paid, and year-to-date (YTD) earnings.
 */
public class PayStub implements IPayStub {
    /** Reference to the employee for whom the pay stub is generated. */
    private IEmployee employee;
    /** Net pay after deductions and taxes. */
    private double netPay;
    /** Taxes deducted for the current pay period. */
    private double taxesPaid;
    /** Employee's year-to-date earnings. */
    private double ytdEarnings;
    /** Employee's year-to-date taxes paid. */
    private double ytdTaxesPaid;

    /**
     * Constructs a PayStub object with payroll details.
     *
     * @param employee     The employee for whom the pay stub is generated.
     * @param netPay      The final take-home pay after deductions and taxes.
     * @param taxesPaid   The amount of taxes paid in the current pay period.
     * @param ytdEarnings The total earnings of the employee so far in the year.
     * @param ytdTaxesPaid The total taxes paid by the employee so far in the year.
     */
    public PayStub(IEmployee employee, double netPay, double taxesPaid, double ytdEarnings, double ytdTaxesPaid) {
        this.employee = employee;
        this.netPay = round(netPay);
        this.taxesPaid = round(taxesPaid);
        this.ytdEarnings = round(ytdEarnings);
        this.ytdTaxesPaid = round(ytdTaxesPaid);
    }

    /**
     * Gets the net pay for the employee after taxes and deductions.
     *
     * @return The net pay amount.
     */
    @Override
    public double getPay() {
        return netPay;
    }

    /**
     * Gets the taxes paid in the current pay period.
     *
     * @return The amount of taxes deducted.
     */
    @Override
    public double getTaxesPaid() {
        return taxesPaid;
    }

    /**
     * Converts the PayStub object to a CSV-formatted string.
     * Format: "employee_name, net_pay, taxes, ytd_earnings, ytd_taxes_paid"
     *
     * @return A CSV representation of the pay stub.
     */
    @Override
    public String toCSV() {
        return String.format("%s,%.2f,%.2f,%.2f,%.2f",
                employee.getName(), netPay, taxesPaid, ytdEarnings, ytdTaxesPaid);
    }

    /**
     * Rounds a value to two decimal places using HALF_UP rounding mode.
     * Ensures accurate financial calculations without floating-point precision issues.
     *
     * @param value The value to round.
     * @return The rounded value to two decimal places.
     */
    private double round(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
